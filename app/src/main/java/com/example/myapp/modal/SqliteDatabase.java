package com.example.myapp.modal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
public class SqliteDatabase extends SQLiteOpenHelper {

    Context context;
    private static final int DATABASE_VERSION = 11;
    private static final String DATABASE_NAME = "Contacts";
    private static final String COLUMN_ID = "id";
    private static final String TABLE_CONTACTS = "Contacts";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_COMPANY = "company";
    private static final String COLUMN_LOCATION = "loction";
    private static final String COLUMN_NUMBER= "number";
    private static final String COLUMN_EMAIL = "email1";
    private static final String COLUMN_QUANTITY = "quantity";
  //  private static final String COLUMN_CUSTOMER = "customer1";
   // private static final String COLUMN_PRODUCT = "product1";
    //private static final String COLUMN_ENQUIRES = "enquiries1";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS +
                "(" + COLUMN_NAME+"TEXT," +
                COLUMN_COMPANY + " TEXT," +
                COLUMN_LOCATION + " TEXT," +
                COLUMN_NUMBER  +" TEXT," +
                COLUMN_EMAIL + " TEXT ," +
                COLUMN_QUANTITY + "TEXT"+")";
              //  + COLUMN_CUSTOMER + "TEXT,"
               // + COLUMN_PRODUCT + "TEXT,"
               // + COLUMN_ENQUIRES + "TEXT"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }
    ArrayList<Contacts> listContacts() {
        String sql = "select * from " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Contacts> storeContacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                String name1 = cursor.getString(0);
                String company1 = cursor.getString(1);
                String loction1 = cursor.getString(2);
                String number1 = cursor.getString(3);
                String email1 = cursor.getString(4);
                String quantity1 = cursor.getString(5);
             //   String customer1 = cursor.getString(6);
               // String product1 = cursor.getString(7);
                storeContacts.add(new Contacts(name1,company1, loction1,number1,email1,quantity1));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacts;
    }
    Boolean addContacts(Contacts contacts) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,contacts.getName1() );
        values.put(COLUMN_COMPANY,contacts.getCompany1() );
        values.put(COLUMN_LOCATION,contacts.getLoction1());
        values.put(COLUMN_NUMBER,contacts.getNumber1());
        values.put(COLUMN_EMAIL,contacts.getEmail1() );
        values.put(COLUMN_QUANTITY,contacts.getQuantity1() );
        //  values.put(COLUMN_CUSTOMER,contacts.getCustomer1());
        // values.put(COLUMN_PRODUCT,contacts.getProduct1());
        //   values.put(COLUMN_ENQUIRES,contacts.getEnquiries1() );

        long ins = db.insert(TABLE_CONTACTS, null, values);

        if(ins == -1) return false;
        else return true;

    }
    public void updateContacts(Contacts contacts) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME,contacts.getName1());
        //values.put(COLUMN_NAME,contacts.getName1() );
        values.put(COLUMN_COMPANY,contacts.getCompany1() );
       values.put(COLUMN_LOCATION,contacts.getLoction1());
        values.put(COLUMN_NUMBER,contacts.getNumber1());
        values.put(COLUMN_EMAIL,contacts.getEmail1() );
        values.put(COLUMN_QUANTITY,contacts.getQuantity1() );
//       // values.put(COLUMN_CUSTOMER,contacts.getCustomer1());
      //  values.put(COLUMN_PRODUCT,contacts.getProduct1());
       // values.put(COLUMN_ENQUIRES,contacts.getEnquiries1() );
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_CONTACTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(contacts.getId())});
    }
    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}