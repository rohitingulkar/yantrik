package com.rohit.yantrik.utils.homePage.ui.adapter


import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iserve.retrofitdemo.models.CountriesResponceModel
import com.rohit.yantrik.R
import java.lang.Exception

/**
 ************************************************************
 * Project : Company Profile
 * Created By : Rohit Ingulkar on 09/Sept/2020
 ************************************************************
 **/
class HomePageAdapter(_countryList: ArrayList<CountriesResponceModel>,
    val _activity: Activity,
    val itemClickListener: OnItemClickListener
) : ListAdapter<CountriesResponceModel, HomePageAdapter.CompanyViewHolder>(DiffCallback()),
    Filterable {


    var sHeader: String = ""
    private var list: ArrayList<CountriesResponceModel>
    private lateinit var listFiltered: List<CountriesResponceModel>


    init {
        this.list = _countryList
        this.listFiltered =  _countryList

    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val item = listFiltered!![position]

        holder.txt_country.text = _activity.resources.getString(R.string.country) + item.Country
        holder.txt_new_confirmed.text =
            _activity.resources.getString(R.string.new_confirmed) + item.NewConfirmed
        holder.txt_total_confirmed.text =
            _activity.resources.getString(R.string.total_confirmed) + item.TotalConfirmed
        holder.txt_new_deaths.text =
            _activity.resources.getString(R.string.new_deaths) + item.NewDeaths
        holder.txt_total_deaths.text =
            _activity.resources.getString(R.string.total_deaths) + item.TotalDeaths
        holder.txt_new_recovered.text =
            _activity.resources.getString(R.string.new_recovered) + item.NewRecovered
        holder.txt_total_recovered.text =
            _activity.resources.getString(R.string.total_recovered) + item.TotalRecovered

        /*holder.button_DepartmentName.setOnClickListener {
            holder.bind(item,itemClickListener,holder.button_DepartmentName,position)
        }*/

    }

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                sHeader = ""
                listFiltered = if (charString.isEmpty()) {
                    list
                } else {
                    val filteredList: MutableList<CountriesResponceModel> = ArrayList()
                    for (row in list!!) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if ( row.Country.toLowerCase()
                                .contains(charString.toLowerCase())
                        ) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = listFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                listFiltered = filterResults.values as ArrayList<CountriesResponceModel>

                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return listFiltered!!.size
    }


    class CompanyViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var txt_country: TextView
        var txt_new_confirmed: TextView
        var txt_total_confirmed: TextView
        var txt_new_deaths: TextView
        var txt_total_deaths: TextView
        var txt_new_recovered: TextView
        var txt_total_recovered: TextView


        init {
            txt_country = row.findViewById(R.id.txt_country)
            txt_new_confirmed = row.findViewById(R.id.txt_new_confirmed)
            txt_total_confirmed = row.findViewById(R.id.txt_total_confirmed)
            txt_new_deaths = row.findViewById(R.id.txt_new_deaths)
            txt_total_deaths = row.findViewById(R.id.txt_total_deaths)
            txt_new_recovered = row.findViewById(R.id.txt_new_recovered)
            txt_total_recovered = row.findViewById(R.id.txt_total_recovered)

        }

        fun bind(
            employeeDetailsModel: CountriesResponceModel,
            clickListener: OnItemClickListener,
            itemViewNew: View,
            position: Int
        ) {
            clickListener.onItemClicked(employeeDetailsModel, itemViewNew.id, position)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.adapter_home_page, parent, false)
        return CompanyViewHolder(v)
    }

    interface OnItemClickListener {
        fun onItemClicked(
            employeeDetailsModel: CountriesResponceModel,
            resourceId: Int,
            position: Int
        )
    }

}

class DiffCallback : DiffUtil.ItemCallback<CountriesResponceModel>() {
    override fun areItemsTheSame(
        oldItem: CountriesResponceModel,
        newItem: CountriesResponceModel
    ): Boolean {
        return oldItem.CountryCode == newItem.CountryCode
    }

    override fun areContentsTheSame(
        oldItem: CountriesResponceModel,
        newItem: CountriesResponceModel
    ): Boolean {
        return oldItem == newItem
    }
}
