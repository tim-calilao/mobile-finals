package com.mlabs.bbm.firstandroidapp;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Hashtable;
    import android.content.ContentValues;
    import android.content.Context;
    import android.content.Intent;
    import android.database.Cursor;
    import android.database.DatabaseUtils;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.database.sqlite.SQLiteDatabase;
    import android.util.Log;
    import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
        public static final String DATABASE_NAME = "dbAccounts.db";
        public static final String ACCOUNTS_TABLE_NAME = "accounts";
        public static final String ACCOUNTS_FNAME = "fname";
        public static final String ACCOUNTS_LNAME = "lname";
        public static final String ACCOUNTS_UNAME = "username";
        public static final String ACCOUNTS_EMAIL = "email";
        public static final String ACCOUNTS_USERNAME = "username";
        public static final String ACCOUNTS_PASSWORD = "password";
        public static final String ACCOUNTS_DATE_CREATED = "date_created";


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table accounts " + "(email text primary key,username text unique, password text,fname text,lname text,date_created text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public String  insertAccount(String email, String password,String username,String fname, String lname,String created)
    {
        if (!(this.validateUser(email, password) == "True")) {
         if(!(this.validateUsername(username,password)=="True")){
             if(!(this.validateCross(username,password)=="True")){
                 SQLiteDatabase db = this.getWritableDatabase();
                 ContentValues contentValues = new ContentValues();
                 contentValues.put("email", email);
                 contentValues.put("username", username);
                 contentValues.put("password", password);
                 contentValues.put("fname", fname);
                 contentValues.put("lname", lname);
                 contentValues.put("date_created",created);
                 db.insert("accounts", null, contentValues);
                return "True";
             }else return "Username is taken as email";
         } else return "username already exists" ;
        } else return "email already exists" ;
    }

    public Cursor getData(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from accounts where email="+email+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, ACCOUNTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateAccounts(String email, String password, String created)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("date_created",created);
        db.update("accounts", contentValues, "email = ? ", new String[] { email } );
        return true;
    }

    public ArrayList<String> getAllAccounts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from accounts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(ACCOUNTS_EMAIL)));
            res.moveToNext();
        }
        return array_list;
    }

    public String validateUser(String email, String password)
    {
        String selectQuery = "SELECT * FROM " + ACCOUNTS_TABLE_NAME + " WHERE " + ACCOUNTS_PASSWORD + " = '" + password + "' AND " + ACCOUNTS_EMAIL + " = '" +email+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0)
        {
            cursor.close();
            db.close();
            return "True";
        }
        cursor.close();
        db.close();
        return "False";
    }
        public String validateUsername(String username, String password)
        {
            String selectQuery = "SELECT * FROM " + ACCOUNTS_TABLE_NAME + " WHERE " + ACCOUNTS_PASSWORD + " = '" + password + "' AND " + ACCOUNTS_USERNAME + " = '" +username+"'";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            //Move to first row
            cursor.moveToFirst();
            if (cursor.getCount() > 0)
            {
                cursor.close();
                db.close();
                return "True";
            }
            cursor.close();
            db.close();
            return "False";
        }

    public String validateCross(String username, String password)
    {
        String selectQuery = "SELECT * FROM " + ACCOUNTS_TABLE_NAME + " WHERE " + ACCOUNTS_PASSWORD + " = '" + password + "' AND " + ACCOUNTS_EMAIL + " = '" +username+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0)
        {
            cursor.close();
            db.close();
            return "True";
        }
        cursor.close();
        db.close();
        return "False";
    }


}