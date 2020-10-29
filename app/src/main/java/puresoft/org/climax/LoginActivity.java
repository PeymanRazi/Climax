package puresoft.org.climax;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements GeneralCallback {

    Activity activity;
    EditText email, password;
    Button loginBtn;
    CheckBox saveCheck;
    SharedPreference_Login sharedPreference_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.climaxlogo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Login();
        RememberDataLogin();

    }


    //this interface callback the result to main activity
    @Override
    public void VolleyResponse(String data) {


   try {
       if (data.equals("time out")) {
           Toast.makeText(getApplicationContext(), data+" check Internet connection!", Toast.LENGTH_LONG).show();
           email.getText().clear();
           password.getText().clear();
           saveCheck.setChecked(false);

       } else {
           new SharedPreference_Auth(getApplicationContext()).saveDetail(data);
           Intent intent = new Intent(getApplicationContext(), MainActivity.class);
           startActivity(intent);
           finish();
       }
   }catch (Exception e){
       e.printStackTrace();
   }
    }


    private void Login() {

        activity = this;
        email = findViewById(R.id.emailTxt);
        password = findViewById(R.id.passwordTxt);
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!(password.getText().toString().equals(null) || password.getText().toString().equals("")) &&
                        !(email.getText().toString().equals(null) || email.getText().toString().equals(""))) {


                    //check the auth data saved
                    if (new SharedPreference_Auth(getApplicationContext()).IsExpired()) {

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

//                   SharedPreference_Auth sharedPreference_auth=new SharedPreference_Auth(getApplicationContext());
//                   Toast.makeText(getApplicationContext(), sharedPreference_auth.getAuth(), Toast.LENGTH_LONG).show();


                    } else {
                        JsonReceiver jsonReceiver = new JsonReceiver(activity, "https://puresoftware.org/user/en/oauth2/access/token.json");
                        Map<String, String> parser = new HashMap<String, String>();
                        parser.put("grant_type", "password");
                        parser.put("username", email.getText().toString());
                        parser.put("password", password.getText().toString());
                        parser.put("scope", "climax");
                        jsonReceiver.post(parser);
                    }

                } else
                    Toast.makeText(getApplicationContext(), "please fill text boxes", Toast.LENGTH_LONG).show();


            }
        });


    }

    private void RememberDataLogin() {
        saveCheck = findViewById(R.id.checkSaveLogin);
        sharedPreference_login = new SharedPreference_Login(this);

        if (sharedPreference_login.IsSaved()) {
            email.setText(sharedPreference_login.email());
            password.setText(sharedPreference_login.password());
            saveCheck.setChecked(true);
        }

        saveCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedPreference_login.saveDetail(email.getText().toString(), password.getText().toString());

                } else {
                    if (sharedPreference_login.IsSaved()) {
                        sharedPreference_login.removeDetails();
                    }
                }
            }
        });

    }

}