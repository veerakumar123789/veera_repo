package com.example.myapp.modal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.adapter.ContactAdapter;

import java.util.ArrayList;
public class Customer_enquiries extends AppCompatActivity {
    private SqliteDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_enquiries);
        RecyclerView contactView = findViewById(R.id.myContactList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contactView.setLayoutManager(linearLayoutManager);
        contactView.setHasFixedSize(true);
        mDatabase = new SqliteDatabase(this);
        ArrayList<Contacts> allContacts = mDatabase.listContacts();
        if (allContacts.size() > 0) {
            contactView.setVisibility(View.VISIBLE);
            ContactAdapter mAdapter = new ContactAdapter(this, allContacts);
            contactView.setAdapter(mAdapter);
        }
        else {
            contactView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no contact in the database. Start adding now", Toast.LENGTH_LONG).show();
        }
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTaskDialog();
            }
        });
    }
    private void addTaskDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_contacts, null);

         EditText nameField = subView.findViewById(R.id.customer_name);
        EditText nameField1 = subView.findViewById(R.id.Company_Name);
        EditText nameField2 = subView.findViewById(R.id.Loction);
        EditText nameField3 = subView.findViewById(R.id.number);
        EditText nameField4 = subView.findViewById(R.id.Email_id);
        EditText nameField5 = subView.findViewById(R.id.quantity2);
       // EditText nameField6 = subView.findViewById(R.id.customer_name);



//        final EditText nameField = subView.findViewById(R.id.enterName);
//        final EditText noField = subView.findViewById(R.id.enterPhoneNum);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add new CONTACT");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("ADD CONTACT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String name1 =nameField.getText().toString();
                String companyname1 =nameField1.getText().toString();
                String loction1 =nameField2.getText().toString();
                String number1 =nameField3.getText().toString();
                String email1 =nameField4.getText().toString();
                String quantity1 =nameField5.getText().toString();

//                final String name = nameField.getText().toString();
//                final String ph_no = noField.getText().toString();
                if (TextUtils.isEmpty(name1) && TextUtils.isEmpty(companyname1) && TextUtils.isEmpty(loction1) && TextUtils.isEmpty(number1)
                        && TextUtils.isEmpty(email1) && TextUtils.isEmpty(quantity1)) {
                    Toast.makeText(Customer_enquiries.this, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else {
                    Contacts newContact = new Contacts(name1,companyname1,loction1,number1,email1,quantity1);
                    mDatabase.addContacts(newContact);
                    Log.d("validate", "onClick: " + newContact);
                    //finish();
                    startActivity(getIntent());
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Customer_enquiries.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDatabase != null) {
            mDatabase.close();
        }
    }
}