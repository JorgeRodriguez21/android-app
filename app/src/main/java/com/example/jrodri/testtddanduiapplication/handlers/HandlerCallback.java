package com.example.jrodri.testtddanduiapplication.handlers;

/**
 * Created by jrodri on 10/7/17.
 */

public class HandlerCallback {

        private int status;
        public static final int OK = 1;
        public static final int FAIL = 2;

        public interface listener {
            void onResponse(int status, String message);
        }
}
