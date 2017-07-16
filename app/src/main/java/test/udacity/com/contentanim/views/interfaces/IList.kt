package test.udacity.com.contentanim.views.interfaces

import test.udacity.com.contentanim.models.ImgModel


/**
 * Created by bernatgomez on 15/7/17.
 */
interface IList {
    fun onDataReceived(data : ArrayList<ImgModel>) : Unit
}