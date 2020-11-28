package com.dean.crudkotlin.remote

object APIUtils {
    const val API_URL = "http://192.168.100.26/marketplace/index.php/"
    fun getProductService(): ProductService? {
        return RetrofitClient.getClient(API_URL)?.create(ProductService::class.java)!!
    }
}