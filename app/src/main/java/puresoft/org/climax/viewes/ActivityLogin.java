package puresoft.org.climax.viewes;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;




import puresoft.org.climax.R;
import puresoft.org.climax.model.SharedPreference_Auth;
import puresoft.org.climax.model.SharedPreference_Login;
import puresoft.org.climax.model.SharedPreferences_Splash;
import puresoft.org.climax.view_model.Login;
import puresoft.org.climax.databinding.ActivityLoginBinding;


public class ActivityLogin extends AppCompatActivity   {

    Context context;
    Login loginVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_login);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.climaxlogo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //shared preference of splash
        Splash();


        //set activity with xml layout views to dada binding
        DataBinding();


    }




    //shared preference of splash
    private void Splash() {
        SharedPreferences_Splash prefManager = new SharedPreferences_Splash(getApplicationContext());
        if (prefManager.isFirstTimeLaunch()) {
            prefManager.setFirstTimeLaunch(false);
            startActivity(new Intent(ActivityLogin.this, ActivityWelcome.class));
            finish();
        }
    }

    //set activity with xml layout views to dada binding
    private void DataBinding() {
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        loginVM = new Login(this);

        activityLoginBinding.setLoginViewModel(loginVM);
    }






}