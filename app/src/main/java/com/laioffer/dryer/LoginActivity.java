package com.laioffer.dryer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.laioffer.dryer.R;
import com.laioffer.dryer.ui.login.OnLoginPageAdapter;

public class LoginActivity extends AppCompatActivity {
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewPager = findViewById(R.id.login_viewpager);
        OnLoginPageAdapter onLoginPageAdapter = new OnLoginPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(onLoginPageAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.login_sliding_tab);
        tabLayout.setupWithViewPager(viewPager);
    }
    public void setCurrentPage(int page) {
        viewPager.setCurrentItem(page);
    }
}
