package test.udacity.com.contentanim.views.interfaces

import test.udacity.com.contentanim.models.PhotoModel


/**
 * Created by bernatgomez on 20/7/17.
 */
interface OnItemClick {
    fun onClick(data : PhotoModel)
}