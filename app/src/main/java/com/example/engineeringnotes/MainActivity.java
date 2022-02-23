package com.example.engineeringnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActionBar Bar = getSupportActionBar();
//        Bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        FrameLayout frameLayout = findViewById(R.id.frame_layout);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,new StartFragment(this,this))
                .commit();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.NavigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.menu_Open,R.string.menu_close);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_FE:
                        Log.i("MENU_DRAWER_TAG","first year clicked");
                        Toast.makeText(MainActivity.this, "Clicked on FE", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_SE:
                        Log.i("MENU_DRAWER_TAG","second year clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_TE:
                        Log.i("MENU_DRAWER_TAG","third year clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_BE:
                        Log.i("MENU_DRAWER_TAG","last year clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_settings:
                        Log.i("MENU_DRAWER_TAG","settings clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_review:
                        Log.i("MENU_DRAWER_TAG","review clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });

    }
}