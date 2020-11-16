package com.example.imsafe.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.imsafe.DatabaseHelper;
import com.example.imsafe.ListDataActivity;
import com.example.imsafe.MainActivity;
import com.example.imsafe.R;

import java.util.ArrayList;
import java.util.Objects;


public class MainFragment extends Fragment {

    DatabaseHelper mDatabaseHelper;

    EditText number;

    Button add, view;

    ImageView imageView;

    public MainFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        imageView = viewGroup.findViewById(R.id.image_view);
        number = viewGroup.findViewById(R.id.add_number);
        add = viewGroup.findViewById(R.id.add);
        view = viewGroup.findViewById(R.id.view);
        mDatabaseHelper = new DatabaseHelper(this.getContext());




        ActivityCompat.requestPermissions(Objects.requireNonNull(MainFragment.this.getActivity()), new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        Cursor data = mDatabaseHelper.getData();
        final ArrayList<String> numbers = new ArrayList<>();
        while(data.moveToNext()){
            //get the data from COL2 and append it to the array.
            numbers.add(data.getString(1));
        }

        //sending messages to the numbers.
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager mySmsManager = SmsManager.getDefault();
                for(int i = 0;i < numbers.size();i++){
                    mySmsManager.sendTextMessage(numbers.get(i),null, "test ", null, null);
                }
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = "+90" + number.getText().toString();

                if(number.length() != 0){
                    addData(newEntry);
                    number.setText("");
                }else{
                    toastMessage("Numara girmeden ekleme işlemi yapılamaz!");
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainFragment.this.getContext(), ListDataActivity.class);
                startActivity(intent);
            }
        });








        return viewGroup;
    }

    /**
     * The string that we want to insert
     * @param newEntry
     */

    public void addData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if(insertData){
            toastMessage("Numara başarı ile eklendi.");
        }else{
            toastMessage("Numara eklenirken hata oluştu. Lütfen tekrar deneyiniz!");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this.getContext(),message, Toast.LENGTH_SHORT).show();
    }
}