package com.example.jrodri.testtddanduiapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.jrodri.testtddanduiapplication.adapters.CountryRecyclerAdapter;
import com.example.jrodri.testtddanduiapplication.R;
import com.example.jrodri.testtddanduiapplication.handlers.CountryHandler;
import com.example.jrodri.testtddanduiapplication.handlers.HandlerCallback;
import com.example.jrodri.testtddanduiapplication.models.Country;
import com.example.jrodri.testtddanduiapplication.services.VolleyService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jrodri on 7/7/17.
 */

public class CountryActivity extends AppCompatActivity {

    @BindView(R.id.countriesRV)
    protected RecyclerView countryRV;

    private List<Country> countries;

    private CountryHandler countryHandler;

    private CountryRecyclerAdapter countryRecyclerAdapter;

    private RequestQueue requestQueue;

    private VolleyService volleyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        ButterKnife.bind(this);
        countries = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        volleyService = VolleyService.getInstance(requestQueue);
        countryHandler = new CountryHandler(this,volleyService);
        countryHandler.searchCountries(searchCountries);
    }

    private void initializeAdapter() {
        countryRV.setLayoutManager(new LinearLayoutManager(this));
        countryRecyclerAdapter = new CountryRecyclerAdapter(this, countries,countryRV);
        countryRecyclerAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Country selectedCountry = (Country) v.getTag();
                if(selectedCountry!=null) {
                    String messageText = buildMessage(selectedCountry);
                    Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_LONG).show();
                }
            }
        });
        countryRV.setAdapter(countryRecyclerAdapter);
    }

    private String buildMessage(Country selectedCountry) {
        return (selectedCountry.getLargestCity()!=null && !selectedCountry.getLargestCity().isEmpty()) ?
                "Capital: " + selectedCountry.getCapital() + " Largest City:" + selectedCountry.getLargestCity() +
                        " Country:" + selectedCountry.getCountry():
                                "Capital: " + selectedCountry.getCapital() +
                                        " Country:" + selectedCountry.getCountry();
    }

    public HandlerCallback.listener searchCountries = new HandlerCallback.listener() {
        @Override
        public void onResponse(int status, String message) {
            countries = countryHandler.getCountries();
            initializeAdapter();
            countryRecyclerAdapter.notifyDataSetChanged();
        }
    };

}
