package com.example.engineeringnotes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    //navdrawer created
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.NavigationView);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        boolean flag = sharedPreferences.getBoolean("isFirstTime",true);
        String year = sharedPreferences.getString("year","First Year");

        if(flag){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout,new StartFragment(this,this))
                    .commit();
        }
        else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout,new HomeFragment(this,this,year))
                    .commit();
        }

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.menu_Open,R.string.menu_close);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            navigationDrawer(item);
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void navigationDrawer(MenuItem item){
        switch (item.getItemId()){
            case R.id.nav_FE:
                openFragment(new HomeFragment(this,this,"First Year"));
                Toast.makeText(MainActivity.this, "First Year Selected !", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_SE:
                openFragment(new HomeFragment(this,this,"Second Year"));
                Toast.makeText(MainActivity.this, "Second Year Selected !", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_TE:
                openFragment(new HomeFragment(this,this,"Third Year"));
                Toast.makeText(MainActivity.this, "Final Year Selected !", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_BE:
                openFragment(new HomeFragment(this,this,"Final Year"));
                Toast.makeText(MainActivity.this, "Final Year Selected !", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.saved_notes:
                openFragment(new SavedNotesFragment(this,"Saved Notes"));
                Toast.makeText(MainActivity.this, "Saved Notes", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_settings:
                openFragment(new SettingsFragment(this, "Settings"));
                Toast.makeText(MainActivity.this, "Change Setting", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_submit:
                openWebPage("https://forms.gle/pVgZGkQonrrVdVzv8");
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
    }

    private void openFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        try{
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
        }
    }
}