package puresoft.org.climax;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements GeneralCallback {

    Button button;
    TextView textView;
    Activity activity;
    RequestQueue queue;
    StringRequest request;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //shared preference of splash
        Splash();



        GetDataApi();


    }


    @Override
    public void VolleyResponse(String data) {

        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
    }


    //shared preference of splash
    private void Splash() {
        SharedPreferences_Splash prefManager = new SharedPreferences_Splash(getApplicationContext());
        if (prefManager.isFirstTimeLaunch()) {
            prefManager.setFirstTimeLaunch(false);
            startActivity(new Intent(MainActivity.this, Splash_Welcome.class));
            finish();
        }
    }

    //get data api
    private void GetDataApi() {
        button = findViewById(R.id.button3);
        textView = findViewById(R.id.textView);
        activity = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    JsonReceiver jsonReceiver = new JsonReceiver(activity, "https://puresoftware.org/climax/en/api-v1/my-accesses.json");
                    Map<String, String> parser = new HashMap<String,String>();
                    String auth= new SharedPreference_Auth(getApplicationContext()).getAuth();
                    auth="Bearer "+auth;
                    parser.put("Authorization",auth);
                    jsonReceiver.get(parser);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //authorizing
    private void AuthorizingApi() {
        queue = Volley.newRequestQueue(getApplicationContext());
        request = new StringRequest(StringRequest.Method.POST, "https://puresoftware.org/user/en/oauth2/access/token.json.", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    token = jsonObject.getString("access_token");
                  textView.setText(token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> parser = new HashMap<String, String>();
                parser.put("grant_type", "password");
                parser.put("username", "peyman19.razi@gmail.com");
                parser.put("password", "peyman124");
                parser.put("scope", "climax");
                return parser;
            }
        };
        queue.add(request);
    }
}
