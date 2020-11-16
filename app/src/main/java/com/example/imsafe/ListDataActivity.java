package com.example.imsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.imsafe.fragment.MainFragment;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    ImageView back;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        define();
        populateListView();

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListDataActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void populateListView() {

        //get data and append to list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> arrayListData = new ArrayList<>();
        while(data.moveToNext()){
            //get the data from COL2 and append it to the array.
            arrayListData.add(data.getString(1));
        }

        //create and set listAdapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayListData);
        mListView.setAdapter(adapter);




        //when item clicked in the list
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String number = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemClick: The ID is: "+ number);

                Cursor data = mDatabaseHelper.getItemID(number);
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);

                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent viewScreen = new Intent(ListDataActivity.this, ViewActivity.class);
                    viewScreen.putExtra("ID", itemID);
                    viewScreen.putExtra("phone_number", number);
                    startActivity(viewScreen);
                }
            }
        });



    }

    private void define() {
        mListView = findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);
    }
}