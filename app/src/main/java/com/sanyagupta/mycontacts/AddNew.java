package com.sanyagupta.mycontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sanyagupta.mycontacts.data.MyDbHandler;
import com.sanyagupta.mycontacts.model.Contact;

public class AddNew extends AppCompatActivity {
    EditText name;
    EditText phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        Intent intent = getIntent();
        name = findViewById(R.id.Name);
        phone = findViewById(R.id.Phone);
    }
    public void saveContact(View view){
        String Name = String.valueOf(name.getText());
        String Phone = String.valueOf(phone.getText());
        Contact contact = new Contact();
        contact.setName(Name);
        contact.setPhoneNumber(Phone);
        MyDbHandler db = new MyDbHandler(AddNew.this);
        db.addContact(contact);
        Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
    public void cancel(View view){
        Toast.makeText(this, "Nothing to save. Contact Discarded.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}