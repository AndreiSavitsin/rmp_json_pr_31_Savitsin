package com.example.json_pr_31_savitsin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.json_pr_31_savitsin.Product
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var product: EditText
    lateinit var price: EditText
    lateinit var tag: EditText

    lateinit var tvNameProduct: TextView
    lateinit var tvPriceProduct: TextView
    lateinit var tvTagsProduct: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        product = findViewById(R.id.productEdit)
        price = findViewById(R.id.priceEdit)
        tag = findViewById(R.id.tagEdit)

        tvNameProduct = findViewById(R.id.tvName)
        tvPriceProduct = findViewById(R.id.tvPrice)
        tvTagsProduct = findViewById(R.id.tvTags)
    }

    fun btnSave(view: View)
    {
        var list: MutableList<String> = mutableListOf()
        var count: Int = 0
        var item: String = ""
        for (i in 1 .. tag.length())
        {
            if (tag.text[i] == ',')
            {
                list.add(item)
                count++
                item = ""
            }
            else
            {
                item += tag.text[i]
            }
        }

        try {
            price.text.toString().toDouble()
            val prod: Product = Product(product.toString(), price.text.toString().toDouble(), list)

            tvNameProduct.text = product.toString()
            tvPriceProduct.text = price.text.toString()


            var prodJSON: String = Gson().toJson(prod)
        }
        catch (e: Exception)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
