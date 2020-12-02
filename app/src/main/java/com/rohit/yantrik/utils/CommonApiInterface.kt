package com.rohit.companyprofile.utils

import com.iserve.retrofitdemo.models.KovidResponceModel
import retrofit2.http.GET
import retrofit2.http.Url
import rx.Observable

/**
 ************************************************************
 * Project : Yan3k
 * Created By : Rohit Ingulkar
 ************************************************************
 **/
interface CommonApiInterface {


    @GET
    fun callRequestAPI(@Url paramString: String): Observable<KovidResponceModel>


}