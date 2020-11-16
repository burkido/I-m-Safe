package com.example.imsafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.imsafe.fragment.EarthquakeFragment;
import com.example.imsafe.fragment.FireFragment;
import com.example.imsafe.fragment.FloodFragment;
import com.example.imsafe.fragment.MainFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper mDatabaseHelper;

    EditText number;

    Button add, view;

    ImageView imageView;

    Toolbar toolbar;

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define();

        //start of drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MainFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_main_menu);
        }



        //end of drawer




//        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
//
//        Cursor data = mDatabaseHelper.getData();
//        final ArrayList<String> numbers = new ArrayList<>();
//        while(data.moveToNext()){
//            //get the data from COL2 and append it to the array.
//            numbers.add(data.getString(1));
//        }
//        for(int i = 0;i < numbers.size();i++){
//            System.out.println((numbers.get(i) + " "));
//        }

        //sending messages to the numbers.
//       imageView.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               SmsManager mySmsManager = SmsManager.getDefault();
//               for(int i = 0;i < 1;i++){
//                   mySmsManager.sendTextMessage(numbers.get(i),null, "test ", null, null);
//               }
//           }
//       });


//       add.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               String newEntry = number.getText().toString();
//               if(number.length() != 0){
//                   addData(newEntry);
//                   number.setText("");
//               }else{
//                   toastMessage("Numara girmeden ekleme işlemi yapılamaz!");
//               }
//           }
//       });
//
//       view.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
//               startActivity(intent);
//           }
//       });

    }
//
//    /**
//     * The string that we want to insert
//     * @param newEntry
//     */
//
//    public void addData(String newEntry){
//        boolean insertData = mDatabaseHelper.addData(newEntry);
//
//        if(insertData){
//            toastMessage("Numara başarı ile eklendi.");
//        }else{
//            toastMessage("Numara eklenirken hata oluştu. Lütfen tekrar deneyiniz!");
//        }
//    }
//
//    /**
//     * customizable toast
//     * @param message
//     */
//    private void toastMessage(String message){
//        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


//    private void define() {
//        imageView = findViewById(R.id.image_view);
//        number = findViewById(R.id.add_number);
//        add = findViewById(R.id.add);
//        view = findViewById(R.id.view);
//        toolbar = findViewById(R.id.toolbar);
//        drawer = findViewById(R.id.drawer_layout);
//        mDatabaseHelper = new DatabaseHelper(this);
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_main_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MainFragment()).commit();
                break;

            case R.id.nav_earthquake:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EarthquakeFragment()).commit();
                break;
            case R.id.nav_fire:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FireFragment()).commit();
                break;
            case R.id.nav_floods:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FloodFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}

