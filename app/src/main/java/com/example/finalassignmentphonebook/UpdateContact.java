package com.example.finalassignmentphonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UpdateContact extends AppCompatActivity {
    EditText editTextFirstName, editTextLastName, editTextAddress, editTextPhoneNumber;
    Button updateContact;
    SQLiteOpenHelperClass mDatabase = new SQLiteOpenHelperClass(this);

    List<PhoneBook> phoneBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        editTextFirstName = findViewById(R.id.editTextFirstNameinupdate);
        editTextLastName = findViewById(R.id.editTextLastNameinupdate);
        editTextAddress = findViewById(R.id.editTextAddressinupdate);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumberinupdate);
        phoneBookList = new ArrayList<>();


        Intent myintent = getIntent();
        final PhoneBook phoneBook = myintent.getParcelableExtra("update");
        editTextFirstName.setText(phoneBook.getFname());
        editTextLastName.setText(phoneBook.getLname());

        editTextAddress.setText(phoneBook.getAddress());

        editTextPhoneNumber.setText(String.valueOf(phoneBook.getPhoneNumber()));


        updateContact = findViewById(R.id.btnupdateinupdate);

        updateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.updateContacts(phoneBook.getId(), editTextFirstName.getText().toString().trim(), editTextLastName.getText().toString().trim(),
                        editTextAddress.getText().toString().trim(), editTextPhoneNumber.getText().toString());
                loadphonebook();
                finish();
            }
        });


    }

    private void loadphonebook() {
        mDatabase = new SQLiteOpenHelperClass(this);

        Cursor cursor = mDatabase.getAllContacts();

        if (cursor.moveToFirst()) {
            do {
                phoneBookList.add(new PhoneBook(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)



                ));
            } while (cursor.moveToNext());
            cursor.close();

            // show items in a listView
            // we use a custom adapter to show employees


        }
    }
}