package com.bit2020.retrofitapi.adapters

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bit2020.retrofitapi.R
import com.bit2020.retrofitapi.models.Photo
import com.squareup.picasso.Picasso
import javax.security.auth.callback.Callback

class PhotoAdapter (

    private val photoList : List<Photo>,
    val context: Callback<List<Photo>>,
    private val onItemClicked: (position: Int) -> Unit

): RecyclerView.Adapter<PhotoAdapter.ViewHolder>(), Parcelable {

    class ViewHolder(
    ItemView:View,
    private val onItemIsClicked: (position: Int) -> Unit

    ):RecyclerView.ViewHolder(ItemView), View.OnClickListener {

        val imgView = itemView.findViewById<ImageView>(R.id.imageView)
        val textTitle = itemView.findViewById<TextView>(R.id.textView_title)
        val textUrl = itemView.findViewById<TextView>(R.id.textView_url)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            onItemIsClicked(position)
        }

    }

    constructor(parcel: Parcel) : this(

    ) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view, onItemClicked)
    }

    override fun getItemCount(): Int {
        return photoList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemViewModel = photoList[position]

        Picasso.get()
            .load(itemViewModel.thumbnailUrl)
            .into(holder.imgView)
        holder.textTitle.text = itemViewModel.title
        holder.textUrl.text = itemViewModel.url

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PhotoAdapter> {
        override fun createFromParcel(parcel: Parcel): PhotoAdapter {
            return PhotoAdapter(parcel)
        }

        override fun newArray(size: Int): Array<PhotoAdapter?> {
            return arrayOfNulls(size)
        }
    }


}