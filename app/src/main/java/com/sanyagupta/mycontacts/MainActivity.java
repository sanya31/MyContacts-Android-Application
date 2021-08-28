package com.sanyagupta.mycontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sanyagupta.mycontacts.adapter.RecyclerViewAdapter;
import com.sanyagupta.mycontacts.data.MyDbHandler;
import com.sanyagupta.mycontacts.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Contact> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyDbHandler db = new MyDbHandler(MainActivity.this);
        int c = db.getCount();
        TextView textView = findViewById(R.id.textView);
        textView.setText(c+" Contacts");
        contactArrayList = new ArrayList<>();
        List<Contact> contactList = db.getAllContacts();
        for(Contact contact:contactList){
            contactArrayList.add(contact);
        }
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    public void add(View view){
        Intent intent = new Intent(this, AddNew.class);
        this.startActivity(intent);
    }

    public void search(View view){
        Intent intent = new Intent(this, SearchContact.class);
        this.startActivity(intent);
    }
}