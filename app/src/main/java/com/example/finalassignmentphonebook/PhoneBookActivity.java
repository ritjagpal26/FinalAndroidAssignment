package com.example.finalassignmentphonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookActivity extends AppCompatActivity {

    private static final String TAG = "EmployeeActivity";
    //    SQLiteDatabase mDatabase;
    SQLiteOpenHelperClass mDatabase;
    List<PhoneBook> phoneBookList;
    ListView listView;
    TextView tvcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);


        listView = findViewById(R.id.lvContacts);
        tvcount = findViewById(R.id.countnote);
        phoneBookList = new ArrayList<>();
        mDatabase = new SQLiteOpenHelperClass(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhoneBook phoneBook = phoneBookList.get(position);

                Intent myintent = new Intent(PhoneBookActivity.this, ViewContacts.class);
                myintent.putExtra("contact",phoneBook);
                startActivity(myintent);

            }
        });
        loadphonebook();
//        .setText("hello");
        tvcount.setText(String.valueOf(mDatabase.getProfilesCount()));
    }

    private void loadphonebook() {

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

            PhonebookAdapter phonebookAdapter = new PhonebookAdapter(this, R.layout.list_layout_of_contacts, phoneBookList, mDatabase);
            listView.setAdapter(phonebookAdapter);
            phonebookAdapter.notifyDataSetChanged();


        }
    }
}

