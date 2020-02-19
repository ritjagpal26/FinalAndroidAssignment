package com.example.finalassignmentphonebook;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PhonebookAdapter extends ArrayAdapter {


    Context mContext;
    int layoutRes;
    List<PhoneBook> phoneBooks;
    //    SQLiteDatabase mDatabase;
    SQLiteOpenHelperClass mDatabase;

    public PhonebookAdapter(Context mContext, int layoutRes, List<PhoneBook> phoneBooks, SQLiteOpenHelperClass mDatabase) {
        super(mContext, layoutRes, phoneBooks);
        this.mContext = mContext;
        this.layoutRes = layoutRes;
        this.phoneBooks = phoneBooks;
        this.mDatabase = mDatabase;
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
//        TextView tvCount = v.findViewById(R.id.tvcou);


        final PhoneBook phoneBook = phoneBooks.get(position);
        tvFName.setText(phoneBook.getFname());
        tvphonenumber.setText(String.valueOf(phoneBook.getPhoneNumber()));
        tvlastname.setText(phoneBook.getLname());
        tvaddress.setText(phoneBook.getAddress());

        mDatabase.getAllContacts();

        return v;
    }


}
