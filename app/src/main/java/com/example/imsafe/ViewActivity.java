package com.example.imsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    EditText editNumber;

    Button delete, save;

    private String number;
    private int ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mDatabaseHelper = new DatabaseHelper(this);

        editNumber = findViewById(R.id.edit_number);
        delete = findViewById(R.id.delete);
        save = findViewById(R.id.save);

        Intent getData = getIntent();
        number = getData.getStringExtra("phone_number");
        ID = getData.getIntExtra("ID", -1);

        editNumber.setText(number);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newNumber = save.getText().toString();
                if(!newNumber.equals("")){
                    mDatabaseHelper.updateName(newNumber, ID, number);
                }
                toastMessage("Öncelikle Bir Numara Girmelisinz");
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("aga "+ number);
                mDatabaseHelper.deleteName(ID, editNumber.getText().toString());
                editNumber.setText("");
                toastMessage("Numara Başarı ile Silindi");
            }
        });





    }

    private void toastMessage(String message) {
        Toast.makeText(ViewActivity.this, message, Toast.LENGTH_LONG);
    }

//    private void define() {
//        editNumber = findViewById(R.id.edit_number);
//        delete = findViewById(R.id.delete);
//        save = findViewById(R.id.save);
//    }
}