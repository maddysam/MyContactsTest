package com.test.mycontacts.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.test.mycontacts.model.ContactsModel;

import java.util.ArrayList;
import java.util.Collections;


public class AdminDatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private final static int DATABASE_VERSION = 2;
    // Database Name
    private final static String DATABASE_NAME = "admin";

    String uid;

    private final ArrayList<ContactsModel> Cart_LIST = new ArrayList<ContactsModel>();
    private final ArrayList<ContactsModel> uid_LIST = new ArrayList<ContactsModel>();


    @SuppressLint("SdCardPath")
    public AdminDatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * To Create TABLES inside {@link AdminDatabaseHelper.DATABASE_NAME}
     * DATABASE
     */
    @Override
    public void onCreate(SQLiteDatabase db) {


        String create_contactst_Table = "CREATE TABLE contacts(id INTEGER PRIMARY KEY,name TEXT, isDeleted INTEGER ,uid TEXT not null unique)";
        String create_contactst_second_Table = "CREATE TABLE contacts_Second(id INTEGER PRIMARY KEY,name TEXT, isDeleted INTEGER ,uid TEXT not null unique)";


        String create_uid = "CREATE TABLE uuid(id INTEGER PRIMARY KEY,uuid TEXT)";
        db.execSQL(create_uid);
        db.execSQL(create_contactst_Table);
        db.execSQL(create_contactst_second_Table);

    }

    public void getCatList() {

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        if (newVersion > oldVersion) {
        }// Create tables again
        onCreate(db);
    }

    public void addUid(ContactsModel message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("uuid", message.getUid());
        db.insert("uuid", null, values);

        Log.e("Query admin++++++++++++", "sql" + values);

    }

    public ArrayList<ContactsModel> getuuid() {
        try {
            Cart_LIST.clear();
            ;
            // Select All Query
            String selectQuery = "SELECT  * FROM  uuid";

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {

                    ContactsModel message = new ContactsModel();

                    message.setUid(cursor.getString(cursor
                            .getColumnIndex("uid")));


                    // Adding contact to list

                    uid_LIST.add(message);
                    Collections.reverse(uid_LIST);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();
            return uid_LIST;
        } catch (Exception e) {


        }

        return uid_LIST;
    }

    public void addSecondTable(ContactsModel messagebeans) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", messagebeans.getName());
        values.put("uid", messagebeans.getUid());
        values.put("isDeleted", 1);

        try {
            db.insertOrThrow("contacts_Second", null, values);

        } catch (SQLiteConstraintException se) {

        }


        db.close();


    }


    public ArrayList<ContactsModel> selectContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ContactsModel> contactListData = new ArrayList<ContactsModel>();
        try {
            int i = 0;
            String where = "SELECT * FROM contacts WHERE uid NOT IN (SELECT uid FROM contacts_Second)";
            Cursor cursor = db.rawQuery(where, null);
            Log.e("cursor", "cursor " + cursor.getCount());
            if (cursor != null && cursor.getCount() > 0)
                while (cursor.moveToNext()) {

                    String create_contactst_Table = "CREATE TABLE contacts(id INTEGER PRIMARY KEY,name TEXT, isDeleted INTEGER ,uid TEXT not null unique)";

                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    int isDeleted = cursor.getInt(cursor.getColumnIndex("isDeleted"));
                    String uid = cursor.getString(cursor.getColumnIndex("uid"));
                    int id = cursor.getInt(cursor.getColumnIndex("id"));

                    ContactsModel contactsModel = new ContactsModel();
                    contactsModel.setUid(uid);

                    contactsModel.setId(id);
                    contactsModel.setName(name);
                    contactsModel.setIsDeleted(isDeleted);
                    contactListData.add(  contactsModel);
                    i++;
                }

        } catch (SQLiteConstraintException se) {
            se.printStackTrace();
        }

        db.close();
        return contactListData;
    }


    public void add_cart(ContactsModel messagebeans) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", messagebeans.getName());
        values.put("uid", messagebeans.getUid());
        values.put("isDeleted", 0);
        Log.e("Query admin++++++++++++", "sql" + values);
        try {
            db.insertOrThrow("contacts", null, values);

        } catch (SQLiteConstraintException se) {

        }

        db.close();

    }

    public ArrayList<ContactsModel> getContactsFromDb() {
        try {
            Cart_LIST.clear();

            String selectQuery;
            if (uid_LIST.size() != 0) {

                // selectQuery = "SELECT  * FROM  contacts ";
                selectQuery = "SELECT  * FROM  contacts where uid=" + uid_LIST + " AND isDeleted = " + 0;
                Log.e("uid list", "selectQueryselectQuery" + selectQuery);
            } else {
                selectQuery = "SELECT  * FROM  contacts ";
            }

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {

                    ContactsModel message = new ContactsModel();

                    message.setId(cursor.getInt(cursor
                            .getColumnIndex("id")));
                    message.setName(cursor.getString(cursor
                            .getColumnIndex("name")));
                    message.setUid(cursor.getString(cursor
                            .getColumnIndex("uid")));


                    // Adding contact to list

                    Cart_LIST.add(message);
                    Collections.reverse(Cart_LIST);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();
            return Cart_LIST;
        } catch (Exception e) {


        }

        return Cart_LIST;
    }

    // Deleting single contact
    public void deleteCartValue(ContactsModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        String strFilter = "uid" + " = " + contact.getUid();
        ContentValues args = new ContentValues();
        //  args.put("name", contact.getName());
        //args.put("uid", contact.getUid());
        args.put("isDeleted", contact.getIsDeleted());
        //args.put("name", contact.getName());
        db.update("contacts", args, strFilter, null);
        db.close();
    }

}