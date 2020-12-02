package com.rohit.yantrik.utils.splash.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.iserve.retrofitdemo.models.KovidResponceModel
import com.rohit.companyprofile.utils.CommonApiInterface
import com.rohit.yantrik.utils.ApiClient
import com.rohit.yantrik.utils.AppConfig
import com.rohit.yantrik.utils.ServiceCallBack
import okhttp3.ResponseBody
import retrofit2.adapter.rxjava.HttpException
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 ************************************************************
 * Project : Yan3k
 * Created By : Rohit Ingulkar
 ************************************************************
 **/

open class SplashViewModel (application: Application) : AndroidViewModel(application) {

    fun callApiGetEmployeeDetails(_activity: Activity, serviceCallBack: ServiceCallBack, serviceAPI: Int){


        val observable = (ApiClient.getApiClient(AppConfig.mProURL).create(CommonApiInterface::class.java) as CommonApiInterface)
                .callRequestAPI(AppConfig.mProURL+"summary")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        observable.subscribe(object : Observer<KovidResponceModel> {

            var kovidResponceModel: KovidResponceModel? =null

            override fun onError(e: Throwable?) {
                try {
                    if (e is HttpException) {
                        var errorHTTP: HttpException = e as HttpException
                        var errorBody = errorHTTP.response()!!.errorBody()!!.string()
                        serviceCallBack.onError(errorBody,serviceAPI)

                    }
                } catch (e: Exception) {

                    e.printStackTrace()
                }
                e!!.printStackTrace()
            }

            override fun onNext(t: KovidResponceModel) {

                kovidResponceModel  = t
            }

            override fun onCompleted() {
                serviceCallBack.onRequestComplete(kovidResponceModel!!, serviceAPI)

            }

        })


    }
}