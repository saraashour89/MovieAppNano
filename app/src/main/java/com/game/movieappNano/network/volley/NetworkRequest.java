package com.game.movieappNano.network.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.game.movieappNano.R;
import com.game.movieappNano.models.Movie;
import com.game.movieappNano.network.utilities.Parsing;
import com.game.movieappNano.network.utilities.VolleyResponse;
import com.game.movieappNano.utilities.NetWorkUtility;

import java.util.ArrayList;

/**
 * Created by Sara on 6/3/2017.
 */

public class NetworkRequest {


    public static void requestAPI(Context context, String url, final VolleyResponse volleyResponse) {
        Log.e("url", url);
        if (NetWorkUtility.checkInternetConnection(context)) {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            StringRequest request = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            volleyResponse.onResponse(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    volleyResponse.onError(error.getMessage());
                }
            });
            requestQueue.add(request);
        } else {
            volleyResponse.onError(context.getString(R.string.no_internet));
        }
    }
}
