package com.damian.moviedb.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.damian.moviedb.R;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class MovieBaseActivity extends DaggerAppCompatActivity {

    protected final void addFragmentToContainer(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
