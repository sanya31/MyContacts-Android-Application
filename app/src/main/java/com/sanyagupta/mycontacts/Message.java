package com.sanyagupta.mycontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Message extends AppCompatActivity {
    String phone;
    EditText editPhone;
    EditText editMessage;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent intent = getIntent();
        phone = intent.getStringExtra("Phone");
        editPhone = findViewById(R.id.et_phone);
        editMessage = findViewById(R.id.et_message);
        send = findViewById(R.id.sendMessage);
        editPhone.setText(phone);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(Message.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    sendMessage();
                }
                else{
                    ActivityCompat.requestPermissions(Message.this, new String[]{Manifest.permission.SEND_SMS}, 100);
                }
            }
        });
    }
        private void sendMessage(){
            String sPhone = editPhone.getText().toString().trim();
            String sMessage = editMessage.getText().toString().trim();

            if(!sPhone.isEmpty() && !sMessage.isEmpty()){
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(sPhone, null, sMessage, null, null);
                Toast.makeText(this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
            }
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) sendMessage();
        else Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
    }
}