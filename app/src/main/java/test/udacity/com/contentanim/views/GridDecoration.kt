package test.udacity.com.contentanim.views


import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * List decorator
 *
 * Created by bernatgomez on 20/7/17.
 */
class GridDecoration constructor(val pad : Int) : RecyclerView.ItemDecoration() {

    /**
     *
     */
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect!!.bottom = pad
        outRect!!.top = pad
        outRect!!.left = pad
        outRect!!.right = pad
    }
}