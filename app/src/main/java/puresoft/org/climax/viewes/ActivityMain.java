package puresoft.org.climax.viewes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import puresoft.org.climax.R;


public class ActivityMain extends AppCompatActivity   {

    ViewPager viewPager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.climaxlogo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        //viewpager set up
        ViewPagerOperation();


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



