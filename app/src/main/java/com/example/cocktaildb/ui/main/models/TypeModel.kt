package com.example.cocktaildb.ui.main.models

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.cocktaildb.R
import kotlinx.android.synthetic.main.cocktail_type_item.view.*

@EpoxyModelClass(layout = R.layout.cocktail_type_item)
abstract class TypeModel :EpoxyModelWithHolder<TypeModel.Holder>(){

    @EpoxyAttribute lateinit var type : String


    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.type.text = type
    }

    class Holder : EpoxyHolder(){
        lateinit var type : TextView
        override fun bindView(itemView: View) {
            type = itemView.type
        }
    }
}