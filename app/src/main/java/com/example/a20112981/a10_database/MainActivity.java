package com.example.a20112981.a10_database;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText,editText1;
    String name,number;
    Boolean CheckEditTextEmpty ;
    String SQLiteQuery;
    SQLiteDatabase SQLITEDATABASE;
    Intent intent;
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Done(View view){
        DBCreate();

        SubmitData2SQLiteDB();
    }

    public void DBCreate(){

        SQLITEDATABASE = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);

        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS demoTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, phone_number VARCHAR);");
    }

    public void SubmitData2SQLiteDB(){

        editText = (EditText)findViewById(R.id.editText);
        editText1 = (EditText)findViewById(R.id.editText2);
        name = editText.getText().toString();
        number = editText1.getText().toString();
        CheckEditTextIsEmptyOrNot( name,number);

        if(CheckEditTextEmpty == true)
        {

            SQLiteQuery = "INSERT INTO demoTable (name,phone_number) VALUES('"+name+"', '"+number+"');";

            SQLITEDATABASE.execSQL(SQLiteQuery);

            Toast.makeText(MainActivity.this,"Data Submit Successfully", Toast.LENGTH_LONG).show();

            ClearEditTextAfterDoneTask();

        }
        else {

            Toast.makeText(MainActivity.this,"Please Fill All the Fields", Toast.LENGTH_LONG).show();
        }
    }

    public void CheckEditTextIsEmptyOrNot(String Name,String PhoneNumber){

        if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(PhoneNumber)){

            CheckEditTextEmpty = false ;

        }
        else {
            CheckEditTextEmpty = true ;
        }
    }

    public void ClearEditTextAfterDoneTask(){

        editText.getText().clear();
        editText1.getText().clear();

    }

    public void Display(View view){

        intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
