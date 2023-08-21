package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private NavigationView navigationViewDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        Toolbar
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Drawer view

        mDrawer = findViewById(R.id.myDrawerLayout);

//        Navigation View
        navigationViewDrawer = findViewById(R.id.nav_view);
        
        setUpDrawerContent(navigationViewDrawer);
    }


    private void setUpDrawerContent(NavigationView nvDrawer) {

        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                selectDrawerItem(item);
                return false;
            }
        });

    }

    private void selectDrawerItem(MenuItem item) {

        Fragment fragment = null;
        Class fragmentClass = null;

        int itemId = item.getItemId();
        if (itemId == R.id.first_fragment) {
            fragmentClass = FirstFragment.class;
        } else if (itemId == R.id.second_fragment) {
            fragmentClass = SecondFragment.class;
        } else if (itemId == R.id.third_fragment) {
            fragmentClass = ThirdFragment.class;
        }


        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_content, fragment).commit();

        item.setChecked(true);

        setTitle(item.getTitle());

        mDrawer.closeDrawers();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            mDrawer.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

