package com.example.cocktaildb.ui.main.models

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.cocktaildb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cocktail_type_item.view.*

@EpoxyModelClass(layout = R.layout.cocktail_type_item)
abstract class TypeModel :EpoxyModelWithHolder<TypeModel.Holder>(){

    @EpoxyAttribute lateinit var type : String
    @EpoxyAttribute lateinit var title: String
    @EpoxyAttribute lateinit var imageUrl: String


    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.type.text = type
        holder.titleView.text = title
        Picasso.get().load(imageUrl).into(holder.image)
    }

    class Holder : EpoxyHolder(){
        lateinit var type : TextView
        lateinit var titleView: TextView
        lateinit var image: ImageView
        override fun bindView(itemView: View) {
            type = itemView.type
            titleView = itemView.title
            image = itemView.image
        }
    }
}