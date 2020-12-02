package com.rohit.yantrik.utils.homePage.ui.activity

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iserve.retrofitdemo.models.CountriesResponceModel
import com.iserve.retrofitdemo.models.KovidResponceModel
import com.rohit.yantrik.MainActivity
import com.rohit.yantrik.R
import com.rohit.yantrik.utils.homePage.ui.adapter.HomePageAdapter
import com.rohit.yantrik.utils.homePage.viewmodel.HomePageViewModel
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePage :
    MainActivity(),
    HomePageAdapter.OnItemClickListener {

    lateinit var rv_covid : RecyclerView
    lateinit var txt_new_confirmed: TextView
    lateinit var txt_total_confirmed: TextView
    lateinit var txt_new_deaths: TextView
    lateinit var txt_total_deaths: TextView
    lateinit var txt_new_recovered: TextView
    lateinit var txt_total_recovered: TextView

    lateinit var viewModel: HomePageViewModel
    lateinit var animation: LayoutAnimationController
    lateinit var homePageAdapter: HomePageAdapter
    lateinit var _kovidResponceModel: KovidResponceModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        inint()
        clickListener()
        getDataFromIntent()
        listenObservers()
    }

    fun inint(){
        rv_covid = findViewById(R.id.rv_covid)
        txt_new_confirmed= findViewById(R.id.txt_new_confirmed)
        txt_total_confirmed= findViewById(R.id.txt_total_confirmed)
        txt_new_deaths= findViewById(R.id.txt_new_deaths)
        txt_total_deaths= findViewById(R.id.txt_total_deaths)
        txt_new_recovered= findViewById(R.id.txt_new_recovered)
        txt_total_recovered= findViewById(R.id.txt_total_recovered)

        viewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)


        rv_covid.layoutManager = LinearLayoutManager(this)
        rv_covid.itemAnimator = null //DefaultItemAnimator()

        val resId: Int = R.anim.layout_animation_down_to_up
        animation = AnimationUtils.loadLayoutAnimation(this, resId)
        rv_covid.setLayoutAnimation(animation)


    }

    fun clickListener(){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //Performs search when user hit the search button on the keyboard
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                //Start filtering the list as user start entering the characters
                homePageAdapter.getFilter()?.filter(p0)
                return false
            }

        })
    }

    fun getDataFromIntent() {
        viewModel.getListAfteUpdate(true, intent)

    }

    fun listenObservers() {

        viewModel.listMutable.observe(this, object : Observer<KovidResponceModel> {
            override fun onChanged(kovidResponceModel: KovidResponceModel) {
                _kovidResponceModel = kovidResponceModel

                setData()

            }
        })

    }

    fun setData(){

        homePageAdapter = HomePageAdapter(_kovidResponceModel.countries!!,this, this)
        rv_covid.adapter = homePageAdapter

        txt_new_confirmed.text = resources.getString(R.string.new_confirmed)+_kovidResponceModel.global!!.NewConfirmed
        txt_total_confirmed.text = resources.getString(R.string.total_confirmed)+_kovidResponceModel.global!!.TotalConfirmed
        txt_new_deaths.text = resources.getString(R.string.new_deaths)+_kovidResponceModel.global!!.NewDeaths
        txt_total_deaths.text = resources.getString(R.string.total_deaths)+_kovidResponceModel.global!!.TotalDeaths
        txt_new_recovered.text = resources.getString(R.string.new_recovered)+_kovidResponceModel.global!!.NewRecovered
        txt_total_recovered.text = resources.getString(R.string.total_recovered)+_kovidResponceModel.global!!.TotalRecovered

        homePageAdapter.submitList(_kovidResponceModel.countries)

    }

    //adapter click listener
    override fun onItemClicked(
        employeeDetailsModel: CountriesResponceModel,
        resourceId: Int,
        position: Int
    ) {
    }
}