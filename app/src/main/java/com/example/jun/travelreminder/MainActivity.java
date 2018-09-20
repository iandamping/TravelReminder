package com.example.jun.travelreminder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jun.travelreminder.fitur.news.NewsFragment;
import com.example.jun.travelreminder.fitur.output.OutputFragment;
import com.example.jun.travelreminder.helper.PreferenceHelper;
import com.example.jun.travelreminder.network.RequestObserver;

public class MainActivity extends AppCompatActivity {
    PreferenceHelper pref;
    boolean doubleBackToExitPressedOnce = false;
    private BottomNavigationView bottomNavigation;
    private String states = RequestObserver.HAS_PULL;
    private int condition;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    fragment = WeatherFragment.newInstance(condition);
//                    break;
                case R.id.navigation_input:
//                    InputActivity.start(MainActivity.this);
                    fragment = OutputFragment.newInstance();
                    break;
                case R.id.navigation_news:
                    fragment = NewsFragment.newInstance();
                    break;
//                case R.id.navigation_blog:
//                    fragment = BlogFragment.newInstance();
//                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flContent, fragment).commit();
            return true;
        }
    };

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("SAVEDSTATES", states + "savedstates");
        outState.putString("states", RequestObserver.HAS_PULL);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            states = savedInstanceState.getString("states");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = new PreferenceHelper(this);
        condition = pref.getInt("fragment");
        initBottomNav();

    }

    public void initBottomNav() {
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, OutputFragment.newInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
