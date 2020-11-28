package com.dean.crudkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dean.crudkotlin.R
import com.dean.crudkotlin.model.PersonItem
import com.dean.crudkotlin.remote.APIUtils
import com.dean.crudkotlin.remote.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class ProductActivity : AppCompatActivity() {

    var productService: ProductService? = null
    lateinit var etName: EditText
    lateinit var etPrice:EditText
    lateinit var etDesc:EditText
    lateinit var etId:EditText
    lateinit var btnSave: Button
    lateinit var btnDel: Button
    lateinit var tvId: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        etName = findViewById(R.id.et_name)
        etPrice = findViewById(R.id.et_price)
        etDesc = findViewById(R.id.et_desc)
        btnSave = findViewById(R.id.btn_save)
        btnDel = findViewById(R.id.btn_delete)
        etId = findViewById(R.id.et_id)
        tvId = findViewById(R.id.tv_id)

        productService = APIUtils.getProductService()

        val extras = intent.extras
        val productName = extras!!.getString("name")
        val productPrice = extras!!.getString("price")
        val productDesc = extras!!.getString("desc")
        val productID = extras!!.getString("id")

        etId.setText(productID)
        etName.setText(productName)
        etPrice.setText(productPrice)
        etDesc.setText(productDesc)

        if (productID != null && productID.trim { it <= ' ' }.length > 0) {
            etId.setFocusable(false)
        } else {
            tvId.setVisibility(View.INVISIBLE)
            etId.setVisibility(View.INVISIBLE)
            btnDel.setVisibility(View.INVISIBLE)
        }

        btnSave.setOnClickListener(View.OnClickListener { view: View? ->
            val name = etName.getText().toString()
            val price = etPrice.getText().toString()
            val desc = etDesc.getText().toString()
            if (productID != null && productID.trim { it <= ' ' }.length > 0) {
                updateProduct(productID.toInt(), name, price, desc)
            } else {
                addProduct(name, price, desc)
            }
        })

        btnDel.setOnClickListener(View.OnClickListener {
            deleteProduct(productID!!.toInt())
            val intent = Intent(
                this@ProductActivity,
                MainActivity::class.java
            )
            startActivity(intent)
        })
    }

    fun addProduct(
        name: String?,
        price: String?,
        desc: String?
    ) {
        val call: Call<PersonItem?>? = productService!!.addProduct(name, price, desc)
        call!!.enqueue(object : Callback<PersonItem?> {
            override fun onResponse(
                call: Call<PersonItem?>,
                response: Response<PersonItem?>
            ) {
                if (response.isSuccessful()) {
                    Toast.makeText(
                        this@ProductActivity, "product added",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@ProductActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(
                call: Call<PersonItem?>,
                t: Throwable
            ) {
                Log.e("ERROR: ", t.message!!)
            }
        })
    }

    private fun updateProduct(
        id: Int,
        name: String,
        price: String,
        desc: String
    ) {
        val call = productService!!.updateProduct(id, name, price, desc)
        call!!.enqueue(object : Callback<PersonItem?> {
            override fun onResponse(
                call: Call<PersonItem?>,
                response: Response<PersonItem?>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ProductActivity, "Product Updated", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@ProductActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(
                call: Call<PersonItem?>,
                t: Throwable
            ) {
                Log.e("ERROR: ", t.message!!)
            }
        })
    }

    private fun deleteProduct(id: Int) {
        val call = productService!!.deleteProduct(id)
        call!!.enqueue(object : Callback<PersonItem?> {
            override fun onResponse(
                call: Call<PersonItem?>,
                response: Response<PersonItem?>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@ProductActivity, "Product deleted",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(
                call: Call<PersonItem?>,
                t: Throwable
            ) {
                Log.e("ERROR: ", t.message!!)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}