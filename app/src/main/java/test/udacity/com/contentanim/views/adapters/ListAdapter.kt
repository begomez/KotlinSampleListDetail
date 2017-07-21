package test.udacity.com.contentanim.views.adapters


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*
import test.udacity.com.contentanim.R
import test.udacity.com.contentanim.controllers.ListController
import test.udacity.com.contentanim.models.PhotoModel


/**
 * List adapter
 *
 * Created by bernatgomez on 15/7/17.
 */
class ListAdapter
    constructor(val cntxt: Context, val list: List<PhotoModel>, val listener: (PhotoModel) -> Unit) : RecyclerView.Adapter<ListAdapter.ListHolder>() {


    /**
     *
     */
    override fun getItemCount() : Int {
        return this.list.size
    }

    /**
     *
     */
    override fun onBindViewHolder(holder: ListHolder?, position: Int) {
        holder?.bind(this.list[position], this.listener)
    }

    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) : ListHolder {
        val v =  LayoutInflater.from(this.cntxt).inflate(R.layout.item_list, parent, false)

        return ListHolder(v)
    }

    /**
     * List item holder
     */
    class ListHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         *
         */
        fun bind(photo: PhotoModel, listener : (PhotoModel) -> Unit) {

            with (this.itemView) {
                Picasso.with(this.context).load(getUrl(photo)).into(this.photo)
                this.setOnClickListener {listener(photo)}
            }
        }

        fun getUrl(photo : PhotoModel) : String {
            return ListController.LIST_URL + this.itemView.context.resources.displayMetrics.widthPixels + ListController.LIST_ITEM_PATH + photo.id
        }
    }

}
