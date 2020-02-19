package com.example.finalassignmentphonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateContact extends AppCompatActivity {
    EditText editTextFirstName, editTextLastName,editTextAddress,editTextPhoneNumber;
    Button updateContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        editTextFirstName = findViewById(R.id.editTextFirstNameinupdate);
        editTextLastName = findViewById(R.id.editTextLastNameinupdate);
        editTextAddress = findViewById(R.id.editTextAddressinupdate);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumberinupdate);

        Intent myintent = getIntent();
        final PhoneBook phoneBook =  myintent.getParcelableExtra("update");
        editTextFirstName.setText(phoneBook.getFname());
        editTextLastName.setText(phoneBook.getLname());

        editTextAddress.setText(phoneBook.getAddress());

        editTextPhoneNumber.setText(String.valueOf(phoneBook.getPhoneNumber()));



        final SQLiteOpenHelperClass mDatabase = new SQLiteOpenHelperClass(this);


        updateContact =  findViewById(R.id.btnUpdateContact);

        updateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.updateContacts(phoneBook.getId(),editTextFirstName.getText().toString().trim(),editTextLastName.getText().toString().trim(),
                        editTextAddress.getText().toString().trim(),Integer.valueOf(editTextPhoneNumber.getText().toString()));
            }
        });



    }
}
