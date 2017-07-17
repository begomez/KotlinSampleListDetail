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
 * Created by bernatgomez on 15/7/17.
 */
class ListAdapter
    constructor(val cntxt : Context, val list : List<PhotoModel>) : RecyclerView.Adapter<ListAdapter.ListHolder>() {

    /**
     *
     */
    override fun getItemCount(): Int {
        return this.list.size //To change body of created functions use File | Settings | File Templates.
    }

    /**
     *
     */
    override fun onBindViewHolder(holder: ListHolder?, position: Int) {
        holder?.bind(this.list[position])
    }

    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListHolder {
        val v =  LayoutInflater.from(this.cntxt).inflate(R.layout.item_list, parent, false)

        return ListHolder(v)
    }

    /**
     *
     */
    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         *
         */
        fun bind(photo: PhotoModel) {
            val urlPhoto =
                ListController.LIST_URL +
                this.itemView.context.getResources().getDisplayMetrics().widthPixels +
                ListController.LIST_ITEM_PATH + photo.id

            Picasso.with(this.itemView.context).load(urlPhoto).into(this.itemView.photo)
        }
    }

}
