package puresoft.org.climax.ServerConnection;


import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;



public class JsonReceiver {
    private RequestQueue queue;
    private StringRequest request;
    private Context context;
    private String url;
    private ProgressDialog progressBar;

    public JsonReceiver(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    //this method used for post the data to the server
    public void post(final Map<String, String> map, LambdaMethod myInterFace) {


        //show progress until to load data form server
        show_progress();
        queue = Volley.newRequestQueue(context);
        request = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.dismiss();

                myInterFace.processJson(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();
                String errors = "";

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    errors = "time out";
                } else if (error instanceof AuthFailureError) {
                    errors = "AuthFailureError";
                } else if (error instanceof ServerError) {
                    errors = "ServerError";
                } else if (error instanceof NetworkError) {
                    errors = "NetworkError";
                } else if (error instanceof ParseError) {
                    errors = "ParseError";
                }
                myInterFace.processJson(errors);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return map;
            }
        };
        queue.add(request);


    }

    //this method used for get the data from the server
    public void get(final Map<String, String> map,LambdaMethod myInterFace) {


        //show progress until to load data form server
        show_progress();
        queue = Volley.newRequestQueue(context);
        request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.dismiss();

                myInterFace.processJson(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.dismiss();
                String errors = "";

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    errors = "time out";
                } else if (error instanceof AuthFailureError) {
                    errors = "user name or password is not correct!!!";
                } else if (error instanceof ServerError) {
                    errors = "ServerError";
                } else if (error instanceof NetworkError) {
                    errors = "please check internet connection!!!";
                } else if (error instanceof ParseError) {
                    errors = "ParseError";
                }
                myInterFace.processJson(errors);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return map;
            }
        };
        queue.add(request);


    }

    //show progress until to load data form server
    private void show_progress() {
        try {
            progressBar = new ProgressDialog(context);
            progressBar.setTitle("please waite...");
            progressBar.setMessage("catching data");
            progressBar.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}