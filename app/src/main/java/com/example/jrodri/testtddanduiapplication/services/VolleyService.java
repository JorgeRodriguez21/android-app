package com.example.jrodri.testtddanduiapplication.services;

import com.android.volley.RequestQueue;

/**
 * Created by jrodri on 10/7/17.
 */

public class VolleyService {

        private static VolleyService volleyService = null;

        private RequestQueue mRequestQueue;

        private VolleyService(RequestQueue mRequestQueue) {
            this.mRequestQueue = mRequestQueue;
        }

        public static VolleyService getInstance(RequestQueue requestQueue) {
            if (volleyService == null) {
                volleyService = new VolleyService(requestQueue);
            }
            return volleyService;
        }

        public RequestQueue getRequestQueue() {
            return mRequestQueue;
        }

}
