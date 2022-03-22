package com.example.databaseapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtname,txtusername,txtpassword;
    Button btninsert,btnview,btnupdate,btndelete;
    database g;
    //ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        g=new database(this);
        txtname=findViewById(R.id.textname);
        txtusername=findViewById(R.id.txtusername);
        txtpassword=findViewById(R.id.txtpassword);
        btninsert=findViewById(R.id.btninsert);
        btnview=findViewById(R.id.btnview);
        btnupdate=findViewById(R.id.btnupdate);
        btndelete=findViewById(R.id.btndelete);
        //listView=findViewById(R.id.listView);
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1=txtname.getText().toString();
                String username1=txtusername.getText().toString();
                String password1=txtpassword.getText().toString();
                if(name1.equals("")||username1.equals("")||password1.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Please Enter Data",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean i=g.insert_data(name1,username1,password1);
                    if(i==true)
                    {
                        Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Data is not inserted",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
//        btnview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,ViewActivity.class);
//                startActivity(intent);
//            }
//        });
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor t=g.getinfo();
                if(t.getCount()==0)
                {
                    Toast.makeText(MainActivity.this,"Not data found",Toast.LENGTH_SHORT).show();
                }
//                List<String> ls=new ArrayList<>();
//                while (t.moveToNext())
//                {
//                    ls.add(t.getString(0));
//                    ls.add(t.getString(1));
//                    ls.add(t.getString(2));
//                }
//                ArrayAdapter<String> adp=new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,ls);
//                listView.setAdapter(adp);
                StringBuffer buffer=new StringBuffer();
                while(t.moveToNext())
                {
                    buffer.append("name"+t.getString(1)+"\n");
                    buffer.append("username"+t.getString(2)+"\n");
                    buffer.append("password"+t.getString(3)+"\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Signup Users Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=txtusername.getText().toString();
                Boolean i=g.delete_data(username);
                if(i==true)
                {
                    Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=txtname.getText().toString();
                String username=txtusername.getText().toString();
                String password=txtpassword.getText().toString();
                Boolean i=g.update_data(name,username,password);
                if(i==true)
                {
                    Toast.makeText(MainActivity.this,"Successfull",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Not Successfull",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}