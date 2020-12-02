package com.rohit.yantrik.utils.homePage.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.iserve.retrofitdemo.models.CountriesResponceModel
import com.iserve.retrofitdemo.models.KovidResponceModel
import com.rohit.companyprofile.utils.CommonApiInterface
import com.rohit.yantrik.MainActivity
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

open class HomePageViewModel (application: Application) : AndroidViewModel(application) {


    // from Intent
    lateinit var _intent : Intent
    val _fetchlist = MutableLiveData<Boolean>()
    val listMutable : LiveData<KovidResponceModel> = Transformations
        .switchMap(_fetchlist){
            getDataCountryDetails()
        }

    fun getListAfteUpdate(shouldFetch: Boolean,intent: Intent) {
        if (shouldFetch) {
            _fetchlist.value = shouldFetch
            _intent = intent
        }
    }

    val _kovidResponceModel = MutableLiveData<KovidResponceModel>()
    fun getDataCountryDetails(): MutableLiveData<KovidResponceModel>{
        val bundle: Bundle? = _intent.getExtras()
        val kovidResponceModel: KovidResponceModel = bundle?.getSerializable(
            MainActivity.kovidData) as KovidResponceModel
        _kovidResponceModel.value = kovidResponceModel
        return _kovidResponceModel
    }
}