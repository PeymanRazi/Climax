package puresoft.org.climax.view_model;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;



import java.util.HashMap;
import java.util.Map;

import puresoft.org.climax.ServerConnection.LambdaMethod;
import puresoft.org.climax.ServerConnection.JsonReceiver;
import puresoft.org.climax.model.SharedPreference_Auth;
import puresoft.org.climax.model.SharedPreference_Login;
import puresoft.org.climax.viewes.ActivityMain;


public class Login {

    SharedPreference_Login sharedPreference_login;
    String email = null, password = null;
    Activity activity;

    public Login(Activity activity) {
        this.activity = activity;
    }


    ////// event methods

    //click button to login
    public void onClicked(View check, View emailTxt, View passwordTxt) {

        try {
            email = ((EditText) emailTxt).getText().toString();
            password = ((EditText) passwordTxt).getText().toString();
            CheckBox checkbox = ((CheckBox) check);


            if (!email.isEmpty() && !password.isEmpty()) {

               SharedPreference_Login login=new  SharedPreference_Login(activity);

                //check the auth data saved
                if (new SharedPreference_Auth(activity).IsExpired()
                && (email.equals(login.emailOriginal())&&password.equals(login.passwordOriginal())))
                {

                    Intent intent = new Intent(activity, ActivityMain.class);
                    activity.startActivity(intent);
                    activity.finish();


                } else {
                    JsonReceiver jsonReceiver = new JsonReceiver(activity, "https://puresoftware.org/user/en/oauth2/access/token.json");
                    Map<String, String> parser = new HashMap<String, String>();
                    parser.put("grant_type", "password");
                    parser.put("username", email);
                    parser.put("password", password);
                    parser.put("scope", "climax");
                    jsonReceiver.post(parser,processor);
                }
            } else {
                checkbox.setChecked(false);
                ((EditText) emailTxt).getText().clear();
                ((EditText) passwordTxt).getText().clear();
                Toast.makeText(activity, "please fill text boxes", Toast.LENGTH_LONG).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //checkbox save login data
    public void onChecked(View check, View emailTxt, View passwordTxt) {

        email = ((EditText) emailTxt).getText().toString();
        password = ((EditText) passwordTxt).getText().toString();
        CheckBox checkbox = ((CheckBox) check);

        if (checkbox.isChecked() && (!email.isEmpty() && !password.isEmpty())) {
            new SharedPreference_Login(activity).saveDetail(email, password);
        } else {
            new SharedPreference_Login(activity).removeDetails();
            checkbox.setChecked(false);
//            Toast.makeText(activity,"please checking arrivals!!!",Toast.LENGTH_LONG).show();
        }


    }


    /////reaction to models methods
    public String getEmail() {

        String dataField = null;
        sharedPreference_login = new SharedPreference_Login(activity);
        if (sharedPreference_login.IsSaved()) {
            dataField = sharedPreference_login.email();
        }


        return dataField;
    }

    public String getPassword() {
        String dataField = null;
        sharedPreference_login = new SharedPreference_Login(activity);
        if (sharedPreference_login.IsSaved()) {
            dataField = sharedPreference_login.password();
        }


        return dataField;
    }

    public boolean getCheck() {
        boolean checked = false;
        sharedPreference_login = new SharedPreference_Login(activity);
        if (sharedPreference_login.IsSaved()) {
            checked = true;
        }

        return checked;
    }

    public LambdaMethod processor = (String json) -> {


        //in continue we should set data received to ui or save them.
        try {


            if (new SharedPreference_Auth(activity).saveDetail(json)) {

                new SharedPreference_Login(activity).saveDetailOriginal(email,password);

                Intent intent = new Intent(activity, ActivityMain.class);
                activity.startActivity(intent);
                activity.finish();


            } else {
                Toast.makeText(activity, json + " check Internet connection!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    };


}
