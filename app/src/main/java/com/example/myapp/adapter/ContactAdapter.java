package com.example.myapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Objects;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.modal.ContactViewHolder;
import com.example.myapp.modal.Contacts;
import com.example.myapp.modal.SqliteDatabase;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>
        implements Filterable {
    private Context context;
    private ArrayList<Contacts> listContacts;
    private ArrayList<Contacts> mArrayList;
    private SqliteDatabase mDatabase;
    public ContactAdapter(Context context, ArrayList<Contacts> listContacts) {
        this.context = context;
        this.listContacts = listContacts;
        this.mArrayList = listContacts;
        mDatabase = new SqliteDatabase(context);
    }
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list, parent, false);
        return new ContactViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        final Contacts contacts = listContacts.get(position);
       holder.name.setText(contacts.getName1());
       holder.company.setText(contacts.getCompany1());
       holder.location.setText(contacts.getLoction1());
       holder.number.setText(contacts.getName1());


//        holder..setText(contacts.getName());
//        holder.tvPhoneNum.setText(contacts.getPhno());
        holder.editContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(contacts);
            }
        });
        holder.deleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.deleteContact(contacts.getId());
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listContacts = mArrayList;
                }
                else {
                    ArrayList<Contacts> filteredList = new ArrayList<>();
                    for (Contacts contacts : mArrayList) {
                        if (contacts.getName1().toLowerCase().contains(charString)) {
                            filteredList.add(contacts);
                        }
                    }
                    listContacts = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listContacts;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listContacts = (ArrayList<Contacts>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    @Override
    public int getItemCount() {
        return listContacts.size();
    }
    private void editTaskDialog(final Contacts contacts) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_contacts, null);
        final EditText nameField = subView.findViewById(R.id.customer_name);
        final EditText nameField1 = subView.findViewById(R.id.Company_Name);
        final EditText nameField2 = subView.findViewById(R.id.Loction);
        final EditText nameField3 = subView.findViewById(R.id.number1);
        final EditText nameField4 = subView.findViewById(R.id.Email_id1);
      //  final EditText nameField5 = subView.findViewById(R.id.spinner23);
        final EditText nameField6 = subView.findViewById(R.id.quantity);


        if (contacts != null) {
            nameField.setText(contacts.getName1());
            nameField1.setText(contacts.getCompany1());
            nameField2.setText(contacts.getLoction1());
            nameField3.setText(contacts.getName1());
            nameField4.setText(contacts.getEmail1());
           // nameField5.setText(contacts.getName1());
            nameField6.setText(contacts.getQuantity1());


            //contactField.setText(String.valueOf(contacts.getPhno()));
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit contact");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("EDIT CONTACT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name1 = nameField.getText().toString();
                final String companyname1 = nameField1.getText().toString();
                final String loction1 = nameField2.getText().toString();
                final String number1 = nameField3.getText().toString();
                final String email1 = nameField4.getText().toString();
                final  String quantity1 = nameField6.getText().toString();
              //  final String customer1 = nameField5.getText().toString();
             //   final String product1 = nameField6.getText().toString();


                if (TextUtils.isEmpty(name1)) {
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                } else {
                    mDatabase.updateContacts(new
                            Contacts(name1,companyname1,loction1,number1,email1,quantity1));
                    ((Activity) context).finish();
                    context.startActivity(((Activity)
                            context).getIntent());
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled",Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}