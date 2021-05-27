

package com.example.myapp.modal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;

import java.text.BreakIterator;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    public View editContact;
    public View deleteContact;

   public TextView name,company,location,number,email,quantity;
   // String name1,company1,loction1,number1,email1,quantity1,customer1,product1,enquiries1;;
    //ImageView deleteContact;
    //ImageView editContact;
    public ContactViewHolder(View itemView) {
        super(itemView);
        name  = itemView.findViewById(R.id.customer_name1);
        company  = itemView.findViewById(R.id.Company_Name1);
        location  = itemView.findViewById(R.id.Loction1);

        number  = itemView.findViewById(R.id.number1);
        email  = itemView.findViewById(R.id.Email_id1);
        quantity  = itemView.findViewById(R.id.customer_name1);


        deleteContact = itemView.findViewById(R.id.deleteContact);
        editContact = itemView.findViewById(R.id.editContact);
    }
}