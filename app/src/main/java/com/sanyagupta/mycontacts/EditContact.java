package com.sanyagupta.mycontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sanyagupta.mycontacts.data.MyDbHandler;
import com.sanyagupta.mycontacts.model.Contact;

public class EditContact extends AppCompatActivity {
    EditText editName;
    EditText editPhone;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Intent intent = getIntent();
        id = intent.getIntExtra("Eid", 0);
        String name = intent.getStringExtra("Ename");
        String phone = intent.getStringExtra("Ephone");

        editName = findViewById(R.id.ChangeName);
        editPhone = findViewById(R.id.ChangePhone);
        editName.setText(name);
        editPhone.setText(phone);
    }
    public void save(View view){
        Contact contact = new Contact();
        contact.setId(id);
        String n = String.valueOf(editName.getText());
        String p = String.valueOf(editPhone.getText());
        contact.setName(n);
        contact.setPhoneNumber(p);
        MyDbHandler db = new MyDbHandler(EditContact.this);
        db.updateContact(contact);
        Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show();
    }
}