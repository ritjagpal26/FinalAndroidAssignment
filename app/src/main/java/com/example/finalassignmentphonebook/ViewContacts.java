package com.example.finalassignmentphonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class ViewContacts extends AppCompatActivity {
    SQLiteOpenHelperClass mdatabase;
    List<PhoneBook> phoneBookList;

    TextView firstname , lastname,phonenumber, address;
    Button updatebtn;
    Button  deletebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);
        Intent myintent = getIntent();
        final PhoneBook phoneBook =  myintent.getParcelableExtra("contact");
        firstname = findViewById(R.id.tvfnameinvc);
        lastname = findViewById(R.id.tvlnameinvc);

        phonenumber = findViewById(R.id.tvphonenumberinvc);

        address = findViewById(R.id.tvaddressinvc);

        firstname.setText(phoneBook.getFname());
        lastname.setText(phoneBook.getLname());

        phonenumber.setText(String.valueOf(phoneBook.getPhoneNumber()));

        address.setText(phoneBook.getAddress());

        deletebtn = findViewById(R.id.btnDeleteContact);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabase.deleteContact(phoneBook.getId());
            }
        });
        updatebtn =  findViewById(R.id.btnUpdateContact);
        updatebtn.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {


               Intent myintent = new Intent(ViewContacts.this, UpdateContact.class);

               myintent.putExtra("update",phoneBook);
               startActivity(myintent);
            }
       });


////            @Override
////            public void onClick(View v) {
////            }
////        });
//
//              .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent myintent = new Intent(ViewContacts.this, UpdateContact.class);
//                myintent.putExtra("update",phoneBook);
//                startActivity(myintent);
//            }
//        });








    }
}
