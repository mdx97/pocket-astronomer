package com.example.astronomer;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AstronomerApp extends Application {
	private RequestQueue requestQueue;

	@Override
	public void onCreate() {
		super.onCreate();
		requestQueue = Volley.newRequestQueue(this);
	}

	public RequestQueue getRequestQueue() {
		return requestQueue;
	}
}
