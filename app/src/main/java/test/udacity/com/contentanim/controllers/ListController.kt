package test.udacity.com.contentanim.controllers

import retrofit2.Call
import test.udacity.com.contentanim.Api
import test.udacity.com.contentanim.MyApplication
import test.udacity.com.contentanim.dependencies.AppModule
import test.udacity.com.contentanim.dependencies.DaggerAppComponent
import test.udacity.com.contentanim.models.ImgModel
import javax.inject.Inject

/**
 * Created by bernatgomez on 16/7/17.
 */
class ListController @Inject constructor() {

    @Inject
    lateinit var api : Api

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    fun getPhotos(url : String) {
        print("Done")
        //api.getImages()
    }
}