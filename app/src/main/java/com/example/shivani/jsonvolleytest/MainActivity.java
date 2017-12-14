package com.example.shivani.jsonvolleytest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ListActivity {
    ArrayAdapter<String> adapter;
    ArrayList<String> list_items= new ArrayList<String>();
    int counter=0;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.editText); 
        final ListView listView = (ListView)findViewById(android.R.id.list); 
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_items);
        listView.setAdapter(adapter);
        Button btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString() !=null){
                    addItems(listView);
                    editText.setText(null);
                 
                    }
                    else{
                    Toast.makeText(MainActivity.this,"Please Insert Value",Toast.LENGTH_SHORT).show();
                    }
            }
        });

        Button btnVolley=(Button)findViewById(R.id.btnGetVolley);
        btnVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,VolleyActivity.class));
            }
        });

    }
    public void addItems(View v){
        
        list_items.add(editText.getText().toString());
            adapter.notifyDataSetChanged();}
     
    
}
