package test.udacity.com.contentanim.views.adapters

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import test.udacity.com.contentanim.R
import test.udacity.com.contentanim.models.PhotoModel
import kotlinx.android.synthetic.main.item_list.view.*;

/**
 * Created by bernatgomez on 15/7/17.
 */
class ListAdapter constructor(var cntxt : Context, var list : List<PhotoModel>) : RecyclerView.Adapter<ListAdapter.ListHolder>() {

    override fun getItemCount(): Int {
        return list.size //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ListHolder?, position: Int) {
        holder?.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListHolder {
        val v =  LayoutInflater.from(cntxt).inflate(R.layout.item_list, parent, false)

        return ListHolder(v)
    }


    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(photo: PhotoModel) {
            val s = "https://unsplash.it/" + itemView.context.getResources().getDisplayMetrics().widthPixels + "?image=" + photo.id

            Picasso.with(itemView.context).load(s).into(itemView.photo)
        }
    }

}
