package com.example.sqldb;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, contact, dob;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.DOB);

        insert = findViewById(R.id.btn_insert);
        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);
        view = findViewById(R.id.btn_view);

        DB = new DBHelper(this);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Txt_name = name.getText().toString();
                String Txt_contact = contact.getText().toString();
                String Txt_dob =dob.getText().toString();

                Boolean check_insert_data = DB.insert_user_data(Txt_name, Txt_contact, Txt_dob);

                if (check_insert_data==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Insertion unsuccessful!", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Txt_name = name.getText().toString();
                String Txt_contact = contact.getText().toString();
                String Txt_dob =dob.getText().toString();

                Boolean check_update_data = DB.update_user_data(Txt_name, Txt_contact, Txt_dob);

                if (check_update_data==true)
                    Toast.makeText(MainActivity.this, "Entry Updated Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Update unsuccessful!", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Txt_name = name.getText().toString();

                Boolean check_delete_data = DB.delete_user_data(Txt_name);

                if (check_delete_data==true)
                    Toast.makeText(MainActivity.this, "Entry Deleted Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Deletion unsuccessful!", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.get_user_data();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No existing data in the Database", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Contact :"+res.getString(1)+"\n");
                    buffer.append("Date of Birth :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


    }
}