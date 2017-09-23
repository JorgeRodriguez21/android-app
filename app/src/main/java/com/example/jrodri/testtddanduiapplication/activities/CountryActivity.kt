package com.example.jrodri.testtddanduiapplication.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.jrodri.testtddanduiapplication.R
import com.example.jrodri.testtddanduiapplication.adapters.CountryRecyclerAdapter
import com.example.jrodri.testtddanduiapplication.handlers.CountryHandler
import com.example.jrodri.testtddanduiapplication.handlers.HandlerCallback
import com.example.jrodri.testtddanduiapplication.models.Country
import com.example.jrodri.testtddanduiapplication.services.VolleyService
import java.util.*

/**
 * Created by jrodri on 7/7/17.
 */

class CountryActivity : AppCompatActivity() {

    @BindView(R.id.countriesRV)
    lateinit var countryRV: RecyclerView

    private lateinit var countries: List<Country>

    private lateinit var countryHandler: CountryHandler

    private lateinit var countryRecyclerAdapter: CountryRecyclerAdapter

    private lateinit var requestQueue: RequestQueue

    private lateinit var volleyService: VolleyService

    private var searchCountries: HandlerCallback.listener = HandlerCallback.listener { _, _ ->
        countries = countryHandler.countries
        initializeAdapter()
        countryRecyclerAdapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        ButterKnife.bind(this)
        countries = ArrayList()
        requestQueue = Volley.newRequestQueue(this)
        volleyService = VolleyService.getInstance(requestQueue)
        countryHandler = CountryHandler(this, volleyService)
        countryHandler.searchCountries(searchCountries)
    }

    private fun initializeAdapter() {
        countryRV.layoutManager = LinearLayoutManager(this)
        countryRecyclerAdapter = CountryRecyclerAdapter(this, countries.filter { x -> (x.largestCity != null && !x.largestCity.isEmpty()) }, countryRV)
        countryRecyclerAdapter.setClickListener { v ->
            val selectedCountry = v.tag as Country
            val messageText = buildMessage(selectedCountry)
            Toast.makeText(applicationContext, messageText, Toast.LENGTH_LONG).show()
        }
        countryRV.adapter = countryRecyclerAdapter
    }


    private fun buildMessage(selectedCountry: Country): String {
        return "Capital: " + selectedCountry.capital + " Largest City:" + selectedCountry.largestCity +
                " Country:" + selectedCountry.country
    }

}
