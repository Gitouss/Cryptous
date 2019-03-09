package com.doplgangr.secrecy.activities;


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.DatabaseUtils;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String BDsql = "Cryptous.db";
    public Database(Context context) {
        super(context, BDsql, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Utilisateur (id INTEGER PRIMARY KEY, " +
                "username TEXT, " +
                "password TEXT," +
                "passwordR TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Utilisateur");
        onCreate(db);
    }

    public boolean InsertCustomer(String username, String password, String password_recovery) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("passwordR", password_recovery);
        database.insert("Utilisateur", null, contentValues);
        database.close();
        return true;
    }

    public boolean DeleteCustomer(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("DELETE FROM Utilisateur WHERE id ='0' OR id ='1'", null);
        if(cursor.getCount()==0){
            return true;
        }
        return false;
    }

    public long GetCountTable(){
        SQLiteDatabase database = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(database, "Utilisateur");
    }

    public boolean GetPasswordR(String passwordR){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Utilisateur", null);
        if (cursor.moveToFirst()) {
            do {
                if(passwordR.equals(cursor.getString(3))){
                    return true;
                }
            } while (cursor.moveToNext());
        }
        return false;
    }
    public boolean UpdateUserPassword(String password){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("UPDATE Utilisateur SET password = '"+password+"' WHERE username = 'Admin'");
        return true;
    }

    public boolean VerifyPassword(String Password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM Utilisateur WHERE username='Admin'", null);
        if (res.moveToFirst()) {
            do {
                if(Password.equals(res.getString(2))){
                    return true;
                }
            } while (res.moveToNext());
        }
        return false;
    }
}