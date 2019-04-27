package com.example.astronomer;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

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

	public static String buildUrl(String format, Object... args) {
		Object[] encodedArgs = new Object[args.length];
		try {
			for (int i = 0; i < args.length; i++)
				encodedArgs[i] = URLEncoder.encode(args[i].toString(), StandardCharsets.UTF_8.name());

			return String.format(Locale.US, format, encodedArgs);
		} catch (UnsupportedEncodingException e) {
			throw new AssertionError(e);
		}
	}
}
