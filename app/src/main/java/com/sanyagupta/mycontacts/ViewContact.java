package com.sanyagupta.mycontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sanyagupta.mycontacts.data.MyDbHandler;
import com.sanyagupta.mycontacts.model.Contact;

public class ViewContact extends AppCompatActivity {
    String name, phone;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        Intent intent = getIntent();
        name = intent.getStringExtra("Rname");
        phone = intent.getStringExtra("Rphone");
        id = intent.getIntExtra("Rid", 0);

        TextView txt_name = findViewById(R.id.display_name);
        TextView txt_phone = findViewById(R.id.display_phone);
        ImageButton call = findViewById(R.id.call);
        ImageButton message = findViewById(R.id.message);

        txt_name.setText(name);
        txt_phone.setText("Mobile "+phone);
    }
    public void edit(View view){
        Intent intent = new Intent(this, EditContact.class);
        intent.putExtra("Eid", id);
        intent.putExtra("Ename", name);
        intent.putExtra("Ephone", phone);
        this.startActivity(intent);

    }
    public void deleteContact(View view){
        MyDbHandler db = new MyDbHandler(ViewContact.this);
        db.deleteContact(id);
        Toast.makeText(this, "Contact Deleted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
    public void call(View view){
        if(phone.isEmpty()) Toast.makeText(this, "No Number To Call", Toast.LENGTH_SHORT).show();
        else{
            String s = "tel:"+phone;
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(s));
            startActivity(intent);
        }
    }
    public void message(View view){
        Intent intent = new Intent(this, Message.class);
        intent.putExtra("Phone", phone);
        startActivity(intent);
    }
}