package com.damian.moviedb;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public abstract class MovieBaseActivity extends AppCompatActivity {

    protected final void addFragmentToContainer(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
