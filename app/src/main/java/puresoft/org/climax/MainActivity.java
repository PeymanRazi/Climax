package puresoft.org.climax;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.Map;

import puresoft.org.climax.ViewPagerClasses.PagerAdapter;


public class MainActivity extends AppCompatActivity implements GeneralCallback {

    Activity activity;
    String resultApi;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.climaxlogo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        //shared preference of splash
        Splash();

        //viewpager set up
        ViewPagerOperation();


    }

    //this interface method receive the result of JsonReceiver and store it to resultApi variable
    @Override
    public void VolleyResponse(String data) {

        resultApi = data;
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

        activity = this;

        try {
            JsonReceiver jsonReceiver = new JsonReceiver(activity, "https://puresoftware.org/climax/en/api-v1/my-accesses.json");
            Map<String, String> parser = new HashMap<String, String>();
            String auth = new SharedPreference_Auth(getApplicationContext()).getAuth();
            auth = "Bearer " + auth;
            parser.put("Authorization", auth);
            jsonReceiver.get(parser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //viewpager set up
    private void ViewPagerOperation() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.input);
        tabLayout.getTabAt(1).setIcon(R.drawable.out);
        tabLayout.getTabAt(2).setIcon(R.drawable.wharehouse);
        tabLayout.getTabAt(3).setIcon(R.drawable.stuff);
        tabLayout.getTabAt(4).setIcon(R.drawable.user);
        tabLayout.getTabAt(5).setIcon(R.drawable.notifications);

    }

}



