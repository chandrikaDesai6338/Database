package com.example.a20112981.a10_database;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    SQLiteDatabase SQLITEDATABASE;
    String FetchQuery;
    ArrayList<String> contacts = new ArrayList<>();
    ArrayList<String> name  = new ArrayList<>();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        show();
    }
    private void show(){
        SQLITEDATABASE = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);
        FetchQuery = "SELECT * FROM demoTable";
        Cursor resultSet = SQLITEDATABASE.rawQuery(FetchQuery,null);
        resultSet.moveToFirst();

        do{
            int contIndext = resultSet.getColumnIndex("phone_number");
            int nameIndext = resultSet.getColumnIndex("name");

                contacts.add(resultSet.getString(contIndext));
                name.add(resultSet.getString(nameIndext));
        }while(resultSet.moveToNext());

        String[] name_contact = new String[contacts.size()];
        for (int i =0;i<name_contact.length;i++)
            name_contact[i] = "Name : "+name.get(i)+"\nContact :"+contacts.get(i);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_act, name_contact);
        ListView listView = (ListView) findViewById(R.id.activity1_main2);
        listView.setAdapter(adapter);
    }


}
