package com.example.finalassignmentphonebook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PhonebookAdapter extends ArrayAdapter {


    private static SQLiteOpenHelperClass mdatabase;
    Context mContext;
    int layoutRes;
    List<PhoneBook> phoneBooks;
    SQLiteOpenHelperClass mDatabase;

    public PhonebookAdapter(Context mContext, int layoutRes, List<PhoneBook> phoneBooks, SQLiteOpenHelperClass mDatabase) {
        super(mContext, layoutRes, phoneBooks);
        this.mContext = mContext;
        this.layoutRes = layoutRes;
        this.phoneBooks = phoneBooks;
        this.mDatabase = mDatabase;
        phoneBooks = new ArrayList<>();

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(layoutRes, null);
        TextView tvFName = v.findViewById(R.id.tvname);
        TextView tvlastname = v.findViewById(R.id.tvlastname);
        TextView tvphonenumber = v.findViewById(R.id.tvPhoneNumber);
        TextView tvaddress = v.findViewById(R.id.tvAddress);


        final PhoneBook phoneBook = phoneBooks.get(position);
        tvFName.setText(phoneBook.getFname());
        tvphonenumber.setText(String.valueOf(phoneBook.getPhoneNumber()));
        tvlastname.setText(phoneBook.getLname());
        tvaddress.setText(phoneBook.getAddress());
        loadphonebook();

        return v;
    }

    private void loadphonebook() {

        Cursor cursor = mDatabase.getAllContacts();
        phoneBooks.clear();

        if (cursor.moveToFirst()) {
            do {
                phoneBooks.add(new PhoneBook(
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
           notifyDataSetChanged();

        }
    }

}
