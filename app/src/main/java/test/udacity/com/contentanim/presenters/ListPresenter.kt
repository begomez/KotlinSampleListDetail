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
 * Created by bernatgomez on 15/7/17.
 */

class ListPresenter @Inject constructor() {

    val TAG = ListPresenter::class.simpleName

    @Inject
    lateinit var controller : ListController

    private lateinit var view : IList

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    fun bindView(view : IList) : Unit {
        this.view = view
    }

    fun start() : Unit {
        EventBus.getDefault().register(this)
    }

    fun stop() : Unit {
        EventBus.getDefault().unregister(this)
    }

    fun getData(url :String) {
        ListController().getPhotos(url)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onDataReceived(data : List<PhotoModel>) : Unit {
        view.onDataReceived(data.subList(data.size - 12, data.size))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onErrorReceived(error : ErrorModel) {
        Log.e(TAG, error.msg)
    }

}
