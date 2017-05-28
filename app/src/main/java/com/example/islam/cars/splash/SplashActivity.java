package com.example.islam.cars.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.islam.cars.BaseActivity;
import com.example.islam.cars.R;
import com.example.islam.cars.realestaeslist.view.AvailableCarsListActivity;


/**
 * Created by islam on 28/05/17.
 * actually splash screen has no business so i didn't implement neither  business class nor presenter class
 */
public class SplashActivity extends BaseActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
          /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, AvailableCarsListActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
