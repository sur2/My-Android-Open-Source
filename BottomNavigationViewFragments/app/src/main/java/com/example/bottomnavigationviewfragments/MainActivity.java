package com.example.bottomnavigationviewfragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "로그";

    private Fragment homeFragment;
    private Fragment rankingFragment;
    private Fragment profileFragment;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity - onCrate() called");

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        homeFragment = HomeFragment.newInstance();
        this.getSupportFragmentManager().beginTransaction().add(R.id.fragment_frame, homeFragment).commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.menu_home:
                Log.d(TAG, "MainActivity - 홈버튼 클릭");
                homeFragment = HomeFragment.newInstance();
                this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, homeFragment).commit();
                break;

            case R.id.menu_ranking:
                Log.d(TAG, "MainActivity - 랭킹버튼 클릭");
                rankingFragment = RankingFragment.newInstance();
                this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, rankingFragment).commit();
                break;

            case R.id.menu_profile:
                Log.d(TAG, "MainActivity - 프로필버튼 클릭");
                profileFragment = ProfileFragment.newInstance();
                this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, profileFragment).commit();
                break;

            default:
                break;
        }
        return true;
    };

}