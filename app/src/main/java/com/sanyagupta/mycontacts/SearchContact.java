package com.sanyagupta.mycontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.sanyagupta.mycontacts.adapter.RecyclerViewAdapter;
import com.sanyagupta.mycontacts.data.MyDbHandler;
import com.sanyagupta.mycontacts.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class SearchContact extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Contact> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);

        Intent intent = getIntent();

    }
    public void searchNow(View view){
        EditText editText = findViewById(R.id.search);
        String name = editText.getText().toString().trim();
        recyclerView = findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyDbHandler db = new MyDbHandler(SearchContact.this);
        contactArrayList = new ArrayList<>();
        List<Contact> contactList = db.getAllContacts();
        List<Contact> searchlist = new ArrayList<>();
        for(Contact contact:contactList){
            if(contact.getName().toLowerCase().contains(name.toLowerCase())) searchlist.add(contact);
        }
        if(searchlist.isEmpty()) Toast.makeText(this, "No Contact found", Toast.LENGTH_SHORT).show();
        recyclerViewAdapter = new RecyclerViewAdapter(SearchContact.this, searchlist);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}