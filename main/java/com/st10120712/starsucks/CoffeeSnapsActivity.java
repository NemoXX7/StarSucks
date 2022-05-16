package com.st10120712.starsucks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class CoffeeSnapsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fab;
    private ImageView imgCameraImage;
    private static final int REQUEST_IMAGE_CAPTURE=0;
    private static final int REQUEST_IMAGE_CAPTURE_PERMISSION=100;

    //Adding Toolbar elements
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggleOnOff;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_snaps);

        fab = findViewById(R.id.fabPhoto);
        imgCameraImage = findViewById(R.id.img_cameraImage);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if we have camera permission
                if (ActivityCompat.checkSelfPermission(CoffeeSnapsActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    final String[] permissions = {Manifest.permission.CAMERA};
                    //request permission -this is asynchronous
                    ActivityCompat.requestPermissions(CoffeeSnapsActivity.this,
                            permissions,REQUEST_IMAGE_CAPTURE_PERMISSION);
                }
                else
                {
                    takePhoto();
                }
            }
        });

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


    //Requests permission to use the camera
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_IMAGE_CAPTURE_PERMISSION &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_GRANTED) {
            //Permission granted so take the photo
            takePhoto();
        }
    }
    //Captures the image
    public void takePhoto(){
        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,REQUEST_IMAGE_CAPTURE);
    }

    //Displays the taken photo in the StarSucks App
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_IMAGE_CAPTURE && data!=null){
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            imgCameraImage.setImageBitmap(bitmap);
        }
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
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        //Returning true marks the item as selected
        return true;
    }
}