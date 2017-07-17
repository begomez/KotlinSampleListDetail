package test.udacity.com.contentanim.controllers


import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import test.udacity.com.contentanim.Api
import test.udacity.com.contentanim.dependencies.AppModule
import test.udacity.com.contentanim.dependencies.DaggerAppComponent
import test.udacity.com.contentanim.models.ErrorModel
import test.udacity.com.contentanim.models.PhotoModel
import javax.inject.Inject


/**
 * Created by bernatgomez on 16/7/17.
 */
class ListController @Inject constructor() {

    @Inject
    lateinit var api : Api

    @Inject
    lateinit var bus : EventBus

    companion object {
        val LIST_URL = "https://unsplash.it/"
        val LIST_ITEM_PATH = "?image="
    }

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }


    /**
     *
     */
    fun getPhotos() {
        var request = this.api.getImages()

        var callback : Callback<List<PhotoModel>> =
            object : Callback<List<PhotoModel>> {
                override fun onResponse(call: Call<List<PhotoModel>>?, response: Response<List<PhotoModel>>?) {
                    bus.post(response?.body())
                }

                override fun onFailure(call: Call<List<PhotoModel>>?, t: Throwable?) {
                    bus.post(ErrorModel("No images"))
                }
            }

        request.enqueue(callback)
    }
}