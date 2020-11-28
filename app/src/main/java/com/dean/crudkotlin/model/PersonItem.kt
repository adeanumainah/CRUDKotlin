package com.dean.crudkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PersonItem {
    @SerializedName("image")
    @Expose
    private var image: String? = null

    @SerializedName("price")
    @Expose
    private var price: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("id")
    @Expose
    private var id = 0

    @SerializedName("desc")
    @Expose
    private var desc: String? = null

    fun PersonItem() {}

    fun PersonItem(
        id: Int,
        name: String?,
        price: String?,
        desc: String?,
        image: String?
    ) {
        this.id = id
        this.name = name
        this.price = price
        this.image = image
        this.desc = desc
    }

    fun setImage(image: String?) {
        this.image = image
    }

    fun getImage(): String? {
        return image
    }

    fun setPrice(price: String?) {
        this.price = price
    }

    fun getPrice(): String? {
        return price
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getName(): String? {
        return name
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int {
        return id
    }

    fun setDesc(desc: String?) {
        this.desc = desc
    }

    fun getDesc(): String? {
        return desc
    }
}