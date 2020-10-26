package puresoft.org.climax;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements GeneralCallback {

    Activity activity;
    EditText email, password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login();
    }

    //this interface callback the result to main activity
    @Override
    public void VolleyResponse(String data) {
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
    }

    private void Login() {

            activity = this;
            email = (EditText) findViewById(R.id.emailTxt);
            password = (EditText) findViewById(R.id.passwordTxt);
            loginBtn=(Button)findViewById(R.id.loginBtn);
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    JsonReceiver jsonReceiver = new JsonReceiver(activity, "https://puresoftware.org/user/en/oauth2/access/token.json");
                    if (!(password.getText().toString().equals(null) || password.getText().toString().equals("")) &&
                            !(email.getText().toString().equals(null) || email.getText().toString().equals(""))) {
                        Map<String, String> parser = new HashMap<String, String>();
                        parser.put("grant_type", "password");
                        parser.put("username", email.getText().toString());
                        parser.put("password", password.getText().toString());
                        parser.put("scope", "climax");
                        jsonReceiver.post(parser, Request.Method.POST);
                    } else
                        Toast.makeText(getApplicationContext(), "please fill text boxes", Toast.LENGTH_LONG).show();


                }
            });


    }
}