package test.udacity.com.contentanim

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import test.udacity.com.contentanim.models.PhotoModel

/**
 * Created by bernatgomez on 16/7/17.
 */
interface Api {
    @GET("/list")
    fun getImages() : Call<List<PhotoModel>>

}