package test.udacity.com.contentanim.presenters


import android.util.Log
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import test.udacity.com.contentanim.controllers.ListController
import test.udacity.com.contentanim.dependencies.AppModule
import test.udacity.com.contentanim.dependencies.DaggerAppComponent
import test.udacity.com.contentanim.models.ErrorModel
import test.udacity.com.contentanim.models.PhotoModel
import test.udacity.com.contentanim.views.interfaces.IList
import javax.inject.Inject


/**
 * List presenter
 * Created by bernatgomez on 15/7/17.
 */
class ListPresenter @Inject constructor() {

    val TAG = ListPresenter::class.simpleName


    @Inject
    protected lateinit var bus : EventBus

    @Inject
    protected lateinit var controller : ListController

    private lateinit var view : IList


//////////////////////////////////////////////////////////////////////////////////////
// CONS
//////////////////////////////////////////////////////////////////////////////////////

    init {
        injectionWithDagger()
    }

    private fun injectionWithDagger() {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

//////////////////////////////////////////////////////////////////////////////////////
// API
//////////////////////////////////////////////////////////////////////////////////////

    fun bindView(view : IList) {
        this.view = view
    }

    fun start() {
        this.bus.register(this)
    }

    fun stop() {
        this.bus.unregister(this)
    }

    fun getData() {
        this.view.showLoading()

        this.controller.getPhotos()
    }

//////////////////////////////////////////////////////////////////////////////////////
// SUBS
//////////////////////////////////////////////////////////////////////////////////////

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onListSuccess(data : List<PhotoModel>) {
        this.view.hideLoading()

        this.view.onDataReceived(data)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onListError(error : ErrorModel) {

        this.view.hideLoading()
        //TODO:
        Log.e(TAG, error.msg)
    }

}
