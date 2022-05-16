package com.st10120712.starsucks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
                                    NavigationView.OnNavigationItemSelectedListener{

    //Declare fields for ImageViews
    private ImageView img_sb1;
    private ImageView img_sb2;
    private ImageView img_sb3;
    private ImageView img_sb4;
    private ImageView img_sb5;
    private ImageView img_sb6;
    //Creating an instance of the Order class
    private Order order;
    //Adding Toolbar elements
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggleOnOff;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_nav_drawer);
        //Instantiating the instance of the Order class
        order = new Order();

        //Set the image files to the fields
        img_sb1 = findViewById(R.id.img_sb1);
        img_sb2 = findViewById(R.id.img_sb2);
        img_sb3 = findViewById(R.id.img_sb3);
        img_sb4 = findViewById(R.id.img_sb4);
        img_sb5 = findViewById(R.id.img_sb5);
        img_sb6 = findViewById(R.id.img_sb6);
        //Adding OnClick Listeners
        img_sb1.setOnClickListener(this);
        img_sb2.setOnClickListener(this);
        img_sb3.setOnClickListener(this);
        img_sb4.setOnClickListener(this);
        img_sb5.setOnClickListener(this);
        img_sb6.setOnClickListener(this);

        //Instantiating the toolbar attributes
        toolbar = findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Adding Listeners to open and close the navigation menu toolbar
        drawerLayout = findViewById(R.id.drawer_layout);
        toggleOnOff = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggleOnOff);
        toggleOnOff.syncState();

        //Set the Listener for the navigation view
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.img_sb1:
                order.setProductName("Soy Latte");
                break;
            case R.id.img_sb2:
                order.setProductName("Chocco Frappe");
                break;
            case R.id.img_sb3:
                order.setProductName("Bottled Americano");
                break;
            case R.id.img_sb4:
                order.setProductName("Rainbow Frappe");
                break;
            case R.id.img_sb5:
                order.setProductName("Caramel Frappe");
                break;
            case R.id.img_sb6:
                order.setProductName("Black Forest Frappe");
        }
        //Printing a toast message
        Toast.makeText(MainActivity.this, "MMM " + order.getProductName(), Toast.LENGTH_SHORT).show();
        //Send the attributes to the next activity
        IntentHelper.openIntent(this,order.getProductName(),OrderDetailsActivity.class);
    }

    @Override
    public void onBackPressed() {
        //If the drawer is open, then close it
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        //Otherwise the Super class will handle it
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_photo:
                //Navigate to the selected activity
                IntentHelper.openIntent(this,"", CoffeeSnapsActivity.class);
                break;
            case R.id.nav_main:
                //Navigate to the selected activity
                IntentHelper.openIntent(this,"", MainActivity.class);
                break;
            case R.id.nav_history:
                //Navigate to the selected activity
                IntentHelper.openIntent(this,"", OrderHistoryActivity.class);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        //Returning true marks the item as selected
        return true;
    }
}