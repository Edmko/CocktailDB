package com.example.cocktaildb.ui.main.models

import android.view.KeyCharacterMap.load
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.example.cocktaildb.R
import kotlinx.android.synthetic.main.drink_item.view.*

@EpoxyModelClass(layout = R.layout.drink_item)
abstract class CocktailModel : EpoxyModelWithHolder<CocktailModel.Holder>() {

    @EpoxyAttribute
    lateinit var imageUrl: String

    @EpoxyAttribute
    lateinit var title: String

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.titleView.text = title
        if (imageUrl.isNotEmpty()) {
            Glide.with(holder.image.context).load(imageUrl).override(200).centerInside().into(holder.image)
        }
    }

    class Holder : EpoxyHolder() {
        lateinit var titleView: TextView
        lateinit var image: ImageView
        override fun bindView(itemView: View) {
            titleView = itemView.title
            image = itemView.image

        }


    }
}

