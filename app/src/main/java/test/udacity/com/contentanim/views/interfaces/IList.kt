package test.udacity.com.contentanim.views.interfaces


import test.udacity.com.contentanim.models.PhotoModel


/**
 * Created by bernatgomez on 15/7/17.
 */
interface IList {

    /**
     *
     */
    fun hideLoading()

    /**
     *
     */
    fun showLoading()

    /**
     *
     */
    fun onDataReceived(data : List<PhotoModel>)
}