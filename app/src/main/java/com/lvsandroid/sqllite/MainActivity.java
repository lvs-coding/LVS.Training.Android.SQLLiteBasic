package com.lvsandroid.sqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            myDatabase.execSQL("DROP TABLE users");
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INTEGER(3), id INTEGER PRIMARY KEY)");

            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('rob',34)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('malbrouk',97)");

            Cursor c = myDatabase.rawQuery("SELECT * FROM users",null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();
            while(c != null) {
                Log.i("id", c.getString(idIndex));
                Log.i("name", c.getString(nameIndex));
                Log.i("age", Integer.toString(c.getInt(ageIndex)));

                c.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
