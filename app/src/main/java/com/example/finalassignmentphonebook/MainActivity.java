package com.example.finalassignmentphonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteOpenHelperClass mDatabase;

    EditText editTextFirstName, editTextLastName,editTextAddress,editTextPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);

        findViewById(R.id.btnAddContact).setOnClickListener(this);
        findViewById(R.id.tvViewContacts).setOnClickListener(this);

        mDatabase = new SQLiteOpenHelperClass(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddContact:
                PhoneBookActivity phoneBookActivity = new PhoneBookActivity();
                mDatabase.addContact(editTextFirstName.getText().toString().trim(),editTextLastName.getText().toString().trim()
                        ,editTextAddress.getText().toString().trim(),editTextPhoneNumber.getText().toString().trim());


                if (editTextFirstName.getText().toString().isEmpty() || editTextLastName.getText().toString().isEmpty() ) {
                    editTextFirstName.setError("Enter Full Name field is mandatory");
                    editTextFirstName.requestFocus();
                    return;
                }

                if (editTextPhoneNumber.getText().toString().trim().isEmpty()) {
                    editTextPhoneNumber.setError("PhoneNumber field cannot be empty");
                    editTextPhoneNumber.requestFocus();
                    return;
                }


                break;
            case R.id.tvViewContacts:
                // start activity to another activity to see the list of employees
                Intent intent = new Intent(MainActivity.this, PhoneBookActivity.class);
                startActivity(intent);
                break;
        }

    }
    @Override
    protected void onRestart() {
        super.onRestart();

        editTextFirstName.setText("");
        editTextLastName.setText("");
        editTextAddress.setText("");
        editTextPhoneNumber.setText("");



    }

}
