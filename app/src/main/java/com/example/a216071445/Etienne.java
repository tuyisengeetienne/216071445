package com.example.a216071445;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Etienne extends AppCompatActivity {


    SQLiteDatabase db;
    SQLiteOpenHelper mydb;
    private Button search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etienne);

        search = (Button)findViewById(R.id.select);


        mydb = new DatabaseHelper(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = mydb.getReadableDatabase();
                Cursor c = db.rawQuery(" SELECT * FROM " + DatabaseHelper.TABLE_NAME ,null);
                if (c.getCount() == 0){
                    showmessage("error" ,"nothing found");
                }
                StringBuffer buffer =  new StringBuffer();
                while (c.moveToNext()){
                    buffer.append(" Id :" + c.getString(0)+ "\n");
                    buffer.append("username : " + c.getString(1) + "\n");
                    buffer.append("Password : " + c.getString(2) + "\n\n");

                }
                showmessage("Users Table",buffer.toString());
            }
        });

    }

    public void showmessage( String title , String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
