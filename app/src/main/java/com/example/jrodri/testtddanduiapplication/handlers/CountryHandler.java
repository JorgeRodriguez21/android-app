package com.example.jrodri.testtddanduiapplication.handlers;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jrodri.testtddanduiapplication.R;
import com.example.jrodri.testtddanduiapplication.models.Country;
import com.example.jrodri.testtddanduiapplication.services.VolleyService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodri on 10/7/17.
 */

public class CountryHandler {

    private List<Country> countries;
    private Context context;
    private VolleyService volley;
    private String[] result;

    public CountryHandler(Context context, VolleyService volleyService) {
        this.context = context;
        countries = new ArrayList<>();
        this.volley = volleyService;
    }

    public void searchCountries(final HandlerCallback.listener listener) {
        String url = context.getString(R.string.countries_url);
        JSONObject request = new JSONObject();
        result = new String[]{""};
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                    System.out.println(jsonObject.toString());
                    JSONArray jsonArray = jsonObject.getJSONObject("RestResponse").getJSONArray("result");
                    Country newCountry;
                    for(int i=0; i<jsonArray.length(); i++) {
                        JSONObject o = jsonArray.getJSONObject(i);
                        newCountry = gson.fromJson(o.toString(), Country.class);
                        countries.add(newCountry);
                        }
                        result[0] = jsonObject.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                listener.onResponse(HandlerCallback.OK,getResult());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onResponse(HandlerCallback.FAIL,volleyError.getMessage());
                try {
                    System.out.println(volleyError.getMessage());
                    System.out.println(new String(volleyError.networkResponse.data, "UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        addToQueue(jsonObjectRequest);
    }

    public void addToQueue(Request request) {
        if (request != null) {
            request.setTag(this);
            request.setRetryPolicy(new DefaultRetryPolicy(
                    60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));
            volley.getRequestQueue().add(request);
        }
    }

    public List<Country> getCountries() {
        return countries;
    }

    public String getResult() {
        return result[0];
    }
}
