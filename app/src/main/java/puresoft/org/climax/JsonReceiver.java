package puresoft.org.climax;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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

    JsonReceiver(Activity activity, String url) {
        this.context = activity;
        this.url = url;
    }

    public void post(final Map<String, String> map, int requestMethod) {

        try {
            //show progress until to load data form server
            show_progress();
            queue = Volley.newRequestQueue(context);
            request = new StringRequest(requestMethod, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressBar.dismiss();
                    //this interface callback the result to main activity
                    ((GeneralCallback) context).VolleyResponse(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressBar.dismiss();
                    Toast.makeText(context, "user name or password is not correct!\n"+error.toString(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    return map;
                }
            };
            queue.add(request);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //show progress until to load data form server
    private void show_progress() {
        progressBar = new ProgressDialog(context);
        progressBar.setTitle("please waite...");
        progressBar.setMessage("catching data");
        progressBar.show();
    }
}