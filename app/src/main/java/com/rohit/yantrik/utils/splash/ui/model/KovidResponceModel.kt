package com.iserve.retrofitdemo.models

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 ************************************************************
 * Project : Yan3k
 * Created By : Rohit Ingulkar
 ************************************************************
 **/
class KovidResponceModel : Serializable {

    @SerializedName("Message")
    lateinit var message: String

    @SerializedName("Global")
    var global: GlobalResponceModel? =null

    @SerializedName("Countries")
    var countries: ArrayList<CountriesResponceModel>? =null

    @SerializedName("Date")
    lateinit var date: String



}
