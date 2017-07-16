package test.udacity.com.contentanim

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import test.udacity.com.contentanim.models.ImgModel

/**
 * Created by bernatgomez on 16/7/17.
 */
interface Api {
    @GET("/api")
    fun getImages(@Path("num") num: Int = 12, call: Any?) : Call<ArrayList<ImgModel>>

}