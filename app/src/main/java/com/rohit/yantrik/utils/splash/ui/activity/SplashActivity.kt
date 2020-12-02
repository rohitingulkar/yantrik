package com.rohit.yantrik.utils.splash.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.iserve.retrofitdemo.models.KovidResponceModel
import com.rbddevs.splashy.Splashy
import com.rohit.yantrik.MainActivity
import com.rohit.yantrik.R
import com.rohit.yantrik.utils.ServiceCallBack
import com.rohit.yantrik.utils.homePage.ui.activity.HomePage
import com.rohit.yantrik.utils.splash.viewmodel.SplashViewModel

/**
 ************************************************************
 * Project : Yan3k
 * Created By : Rohit Ingulkar
 ************************************************************
 **/
class SplashActivity : MainActivity(),
    ServiceCallBack {


    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initview()
        setSplashy()
        callApi()

    }

    fun initview(){
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

    }


    // for animation
    fun setSplashy(){
        Splashy(this)
            .setLogo(R.drawable.ic_launcher_background)
            .setTitle("Yan3k")
            .setAnimation(Splashy.Animation.GLOW_LOGO,1000)
            .setTitleColor(R.color.colorPrimaryDark)
            .setSubTitle("Welcome To Yan3k....")
            .setProgressColor(R.color.colorPrimaryDark)
            .setBackgroundResource(R.color.white)
            .setFullScreen(true)
            .show()
    }

    fun callApi(){
        viewModel.callApiGetEmployeeDetails(this,this, ServiceCallBack.API_LOCAL_LEVEL)
    }

    override fun onRequestComplete(paramObject: KovidResponceModel, paramInt: Int) {
        val intent = Intent(this@SplashActivity,HomePage::class.java)
        val bundle = Bundle()
        bundle.putSerializable(MainActivity.kovidData, paramObject)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }

    override fun onRequestCancel(paramString: String, paramInt: Int) {
        Toast.makeText(this, paramString, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onError(paramString: String, paramInt: Int) {
        Toast.makeText(this, paramString, Toast.LENGTH_LONG).show()
        finish()

    }
}