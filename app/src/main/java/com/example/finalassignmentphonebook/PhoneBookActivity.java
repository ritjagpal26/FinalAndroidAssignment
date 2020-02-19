package com.example.finalassignmentphonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.finalassignmentphonebook.ViewContacts.mdatabase;

public class PhoneBookActivity extends AppCompatActivity {

    private static final String TAG = "PhoneBookActivity";
    static SQLiteOpenHelperClass mDatabase;
    List<PhoneBook> phoneBookList;
    ListView listView;
    TextView tvcount;
    EditText search;
PhonebookAdapter phoneadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);
        phoneBookList = new ArrayList<>();


        listView = findViewById(R.id.lvContacts);
        tvcount = findViewById(R.id.countnote);
        phoneBookList = new ArrayList<>();
        mDatabase = new SQLiteOpenHelperClass(this);
        search = findViewById(R.id.editSearch );

        search.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        } );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                PhoneBook phoneBook = phoneBookList.get(position);
                Intent myintent = new Intent(PhoneBookActivity.this, ViewContacts.class);
                myintent.putExtra("contact",phoneBook);
                startActivity(myintent);
                finish();
            }
        });
        loadphonebook();
//        .setText("hello");
        tvcount.setText(String.valueOf(mDatabase.getProfilesCount()));
    }
    private void filter(String s) {
        List<PhoneBook> filteredList = new ArrayList<>();
        for(PhoneBook phoneBook : phoneBookList)
        {
            if(phoneBook.getFname().toLowerCase().contains( s.toLowerCase() )
                    || phoneBook.getLname().toLowerCase().contains( s.toLowerCase() )
                    || phoneBook.getAddress().toLowerCase().contains( s.toLowerCase() )
                    || phoneBook.getPhoneNumber().toLowerCase().contains( s.toLowerCase() )


            )
            {
                filteredList.add( phoneBook );
            }
        }
      phoneadapter.filterList(filteredList);




    }
    public static void reopen(Context context) {
        mDatabase.close();
        mDatabase = null;
        mDatabase = new SQLiteOpenHelperClass(context);
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


        }
    }
}

