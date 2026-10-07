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
        var nameStr = product.text.toString()
        var priceStr = price.text.toString()
        var tagStr = tag.text.toString()

        if (nameStr == "")
        {
            Toast.makeText(this, "Введите название товара", Toast.LENGTH_SHORT).show()
            return
        }

        if (priceStr == "")
        {
            Toast.makeText(this, "Введите цену", Toast.LENGTH_SHORT).show()
            return
        }

        if (priceStr.toDoubleOrNull() == null)
        {
            Toast.makeText(this, "Цена должна быть числом", Toast.LENGTH_SHORT).show()
            return
        }

        if (tagStr == "")
        {
            Toast.makeText(this, "Введите теги", Toast.LENGTH_SHORT).show()
            return
        }

        var list: MutableList<String> = mutableListOf()
        var count: Int = 0
        var item: String = ""
        for (i in 1 .. tagStr.length)
        {
            if (tagStr[i-1] == ',')
            {
                list.add(item)
                count++
                item = ""
            }
            else
            {
                item += tagStr[i-1]
            }
        }
        if (item != "")
        {
            list.add(item)
        }

        try {
            val prod: Product = Product(nameStr, priceStr.toDouble(), list)

            tvNameProduct.text = nameStr
            tvPriceProduct.text = priceStr

            var prodJSON: String = Gson().toJson(prod)

            var prod2: Product = Gson().fromJson(prodJSON, Product::class.java)

            tvNameProduct.text = prod2.name
            tvPriceProduct.text = prod2.price.toString()

            var s: String = ""
            for (i in 0 .. prod2.tags.size - 1)
            {
                s = s + prod2.tags[i] + " "
            }
            tvTagsProduct.text = s
        }
        catch (e: Exception)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}