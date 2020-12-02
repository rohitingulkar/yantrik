package com.rohit.yantrik.utils

import com.iserve.retrofitdemo.models.KovidResponceModel


/**
 ************************************************************
 * Project : Yan3k
 * Created By : Rohit Ingulkar
 ************************************************************
 **/
interface ServiceCallBack {

    fun onRequestComplete(paramObject: KovidResponceModel, paramInt: Int)

    fun onRequestCancel(paramString: String, paramInt: Int)

    fun onError(paramString: String, paramInt: Int)

    companion object {
        val API_LOCAL_LEVEL:Int=1

    }
}