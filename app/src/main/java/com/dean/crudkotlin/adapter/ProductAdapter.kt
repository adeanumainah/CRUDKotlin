package com.dean.crudkotlin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.dean.crudkotlin.R
import com.dean.crudkotlin.activity.ProductActivity
import com.dean.crudkotlin.model.PersonItem
import java.lang.String

class ProductAdapter(
    context: Context,
    resource: Int,
    objects: List<PersonItem>
) :
    ArrayAdapter<PersonItem?>(context, resource, objects) {
    private val personItem: List<PersonItem>
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v: View = inflater.inflate(R.layout.list_item, parent, false)
        val tvIdProduct = v.findViewById<TextView>(R.id.tv_product_id)
        val tvNameProduct = v.findViewById<TextView>(R.id.tv_product_name)
        val tvPriceProduct = v.findViewById<TextView>(R.id.tv_product_price)
        val tvDescProduct = v.findViewById<TextView>(R.id.tv_product_desc)
        tvIdProduct.setText(String.valueOf(personItem[position].getId()))
        tvNameProduct.setText(String.valueOf(personItem[position].getName()))
        tvPriceProduct.setText(String.valueOf(personItem[position].getPrice()))
        tvDescProduct.setText(String.valueOf(personItem[position].getDesc()))

        v.setOnClickListener {
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra("id", String.valueOf(personItem[position].getId()))
            intent.putExtra("name", personItem[position].getName())
            intent.putExtra("price", personItem[position].getPrice())
            intent.putExtra("desc", personItem[position].getDesc())
            context.startActivity(intent)
        }
        return v
    }

    init {
        personItem = objects
    }
}