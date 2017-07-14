package com.example.jrodri.testtddanduiapplication.handlers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.jrodri.testtddanduiapplication.services.VolleyService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by jrodri on 11/7/17.
 */
@RunWith(AndroidJUnit4.class)
public class CountryHandlerTest {

    private CountryHandler countryHandler;

    private HandlerCallback.listener searchCountries;

    @Before
    public void setUp(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        RequestQueue requestQueue = Volley.newRequestQueue(appContext);
        VolleyService volleyService = VolleyService.getInstance(requestQueue);
        countryHandler = new CountryHandler(appContext,volleyService);
    }

    @Test
    public void testStructureOfWebServiceResponse() throws InterruptedException {
        searchCountries = new HandlerCallback.listener() {
            @Override
            public void onResponse(int status, String message) {
                System.out.println(message);
                assertTrue(message.contains("RestResponse\":{"));
                assertTrue(message.contains("{result\":[{"));
            }
        };
        countryHandler.searchCountries(searchCountries);
    }
}