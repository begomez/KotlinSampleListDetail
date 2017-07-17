package test.udacity.com.contentanim.models

/**
 * Created by bernatgomez on 15/7/17.
 */
data class PhotoModel
    constructor(
        val format : String,
        val width : Int, val height : Int,
        val filename : String, val id : Long,
        val author : String, val author_url : String, val author_post : String)
{
}