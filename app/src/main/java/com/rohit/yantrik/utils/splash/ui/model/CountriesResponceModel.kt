package com.iserve.retrofitdemo.models

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 ************************************************************
 * Project : Yan3k
 * Created By : Rohit Ingulkar
 ************************************************************
 **/
class CountriesResponceModel : Serializable {

    @SerializedName("Country")
    lateinit var Country: String

    @SerializedName("CountryCode")
    lateinit var CountryCode: String

    @SerializedName("Slug")
    lateinit var Slug: String

    @SerializedName("NewConfirmed")
    lateinit var NewConfirmed: String

    @SerializedName("TotalConfirmed")
    lateinit var TotalConfirmed: String

    @SerializedName("NewDeaths")
    lateinit var NewDeaths: String

    @SerializedName("TotalDeaths")
    lateinit var TotalDeaths: String

    @SerializedName("NewRecovered")
    lateinit var NewRecovered: String

    @SerializedName("TotalRecovered")
    lateinit var TotalRecovered: String


}
