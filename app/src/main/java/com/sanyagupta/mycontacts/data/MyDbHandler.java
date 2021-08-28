package com.sanyagupta.mycontacts.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sanyagupta.mycontacts.model.Contact;
import com.sanyagupta.mycontacts.parameters.Parameters;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context){
        super(context, Parameters.DB_Name, null, Parameters.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE "+Parameters.TABLE_NAME+"("
                        +Parameters.KEY_ID+" INTEGER PRIMARY KEY, "
                        +Parameters.KEY_NAME+" TEXT, "+Parameters.KEY_PHONE+" TEXT)";
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Parameters.KEY_NAME, contact.getName());
        values.put(Parameters.KEY_PHONE, contact.getPhoneNumber());
        db.insert(Parameters.TABLE_NAME, null, values);
        db.close();
    }

    public List<Contact> getAllContacts(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Contact> contactlist= new ArrayList<>();
        String select = "SELECT * FROM "+Parameters.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactlist.add(contact);
            }while(cursor.moveToNext());
        }
        return contactlist;
    }

    public int updateContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Parameters.KEY_NAME, contact.getName());
        values.put(Parameters.KEY_PHONE, contact.getPhoneNumber());

        return db.update(Parameters.TABLE_NAME, values, Parameters.KEY_ID+ "=?",
                            new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Parameters.TABLE_NAME, Parameters.KEY_ID+"=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+Parameters.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }
}
