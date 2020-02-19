package com.example.finalassignmentphonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewContacts extends AppCompatActivity {
    static SQLiteOpenHelperClass mdatabase;
    List<PhoneBook> phoneBookList;

    TextView firstname , lastname,phonenumber, address;
    Button updatebtn;
    Button  deletebtn;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);
        listView = findViewById(R.id.lvContacts);
        phoneBookList = new ArrayList<>();


        mdatabase = new SQLiteOpenHelperClass(this);

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
                System.out.println(" id have " + phoneBook.getId());
                mdatabase.deleteContact(phoneBook.getId());

                loadphonebook();
                finish();



            }
        });
        updatebtn =  findViewById(R.id.btnUpdateContact);
        updatebtn.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {


               Intent myintent = new Intent(ViewContacts.this, UpdateContact.class);

               myintent.putExtra("update",phoneBook);
               startActivity(myintent);
               finish();
               PhonebookAdapter phonebookAdapter = new PhonebookAdapter(ViewContacts.this, R.layout.list_layout_of_contacts, phoneBookList, mdatabase);
               phonebookAdapter.notifyDataSetChanged();
               reopen(ViewContacts.this);
               loadphonebook();

            }
       });



    }
    private void loadphonebook() {
        mdatabase = new SQLiteOpenHelperClass(this);

        Cursor cursor = mdatabase.getAllContacts();

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

            PhonebookAdapter phonebookAdapter = new PhonebookAdapter(this, R.layout.list_layout_of_contacts, phoneBookList, mdatabase);
            listView.setAdapter(phonebookAdapter);
            phonebookAdapter.notifyDataSetChanged();


        }

    }
    public static void reopen(Context context) {
        mdatabase.close();
        mdatabase = null;
        mdatabase = new SQLiteOpenHelperClass(context);
    }
}
