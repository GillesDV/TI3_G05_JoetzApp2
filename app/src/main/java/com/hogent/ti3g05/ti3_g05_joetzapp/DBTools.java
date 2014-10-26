package com.hogent.ti3g05.ti3_g05_joetzapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBTools extends SQLiteOpenHelper {

    private final static int DB_VERSION = 10;

    public DBTools(Context context) {
        super(context, "joetz.db", null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table logins (userId Integer primary key autoincrement, "+
                " gebruikersnaam text, wachtwoord text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oudeVersie, int nieuweVersie) {
        try{
            System.out.println("UPGRADE DB oldVersion="+oudeVersie+" - newVersion="+nieuweVersie);
            //recreateDb(sqLiteDatabase);
            if (oudeVersie<10){
                String query = "create table logins (userId Integer primary key autoincrement, "+
                        " username text, password text)";
                sqLiteDatabase.execSQL(query);
            }
        }
        catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // super.onDowngrade(db, oldVersion, newVersion);
        System.out.println("DOWNGRADE DB oldVersion="+oldVersion+" - newVersion="+newVersion);
    }

    public Gebruiker insertUser (Gebruiker queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", queryValues.gebruikersnaam);
        values.put("password", queryValues.wachtwoord);
        queryValues.userId=database.insert("logins", null, values);
        database.close();
        return queryValues;
    }

    public int updateUserPassword (Gebruiker queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", queryValues.gebruikersnaam);
        values.put("password", queryValues.wachtwoord);
        queryValues.userId=database.insert("logins", null, values);
        database.close();
        return database.update("logins", values, "userId = ?", new String[] {String.valueOf(queryValues.userId)});
    }

    public Gebruiker getUser (String gebruikersnaam){
        String query = "Select userId, password from logins where username ='"+gebruikersnaam+"'";
        Gebruiker myUser = new Gebruiker(0,gebruikersnaam,"");
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                myUser.userId=cursor.getLong(0);
                myUser.wachtwoord=cursor.getString(1);
            } while (cursor.moveToNext());
        }
        return myUser;
    }
}