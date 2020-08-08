package com.laioffer.dryer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.laioffer.dryer.ui.login.Config;

public class WasherActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washer);
        BottomNavigationView navView = findViewById(R.id.washer_nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);

        Toolbar headbar = findViewById(R.id.head_bar);
        setSupportActionBar(headbar);
        ActionBar actionBar = getSupportActionBar();
        drawerLayout = findViewById(R.id.washer_layout);
        drawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                    }

                    @Override
                    public void onDrawerOpened(@NonNull View drawerView) {
                        final TextView user_textview = (TextView) drawerView.findViewById(R.id.header_text);
                        if (Config.username == null) {
                            user_textview.setText("");
                        }
                        else {
                            user_textview.setText("Hi, "+Config.username);
                        }

                    }

                    @Override
                    public void onDrawerClosed(@NonNull View drawerView) {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {

                    }
                }
        );
        NavigationView navigationView = findViewById(R.id.left_nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        if (item.getItemId() == R.id.left_logout) {
                            Config.username = null;
                            Config.token = null;
                            finish();
                        }
                        return true;
                    }
                }
        );
//        BottomNavigationView bottomNavigationView = findViewById(R.id.washer_nav_view);
//        bottomNavigationView.setOnNavigationItemSelectedListener(
//                new BottomNavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                        item.setChecked(true);
//                        Fragment fragment = null;
//                        Class fragmentClass = null;
//                        switch (item.getItemId()) {
//                            case R.id.navigation_washers:
//                                fragmentClass = WasherFragment.class;
//                                break;
//                            case R.id.navigation_reservations:
//                                fragmentClass = ReservationFragment.class;
//                                break;
//                            case R.id.navigation_experiment:
//                                fragmentClass = ExperimentFragment.class;
//                                break;
//                            case R.id.navigation_config:
//                                fragmentClass = ConfigFragment.class;
//                                break;
//                            default:
//                                fragmentClass = WasherFragment.class;
//
//                        }
//                        try {
//                            fragment = (Fragment) fragmentClass.newInstance();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        FragmentManager fragmentManager = getSupportFragmentManager();
//                        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_container, fragment).commit();
//                        drawerLayout.closeDrawers();
//                        return true;
//                    }
//                }
//        );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
