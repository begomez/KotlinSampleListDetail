package test.udacity.com.contentanim.presenters

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import test.udacity.com.contentanim.controllers.ListController
import test.udacity.com.contentanim.dependencies.AppModule
import test.udacity.com.contentanim.dependencies.DaggerAppComponent
import test.udacity.com.contentanim.models.ImgModel
import test.udacity.com.contentanim.views.interfaces.IList
import javax.inject.Inject

/**
 * Created by bernatgomez on 15/7/17.
 */

class ListPresenter @Inject constructor() {

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
    fun onDataReceived(data : ArrayList<ImgModel>) : Unit {
        view.onDataReceived(data)
    }

}
