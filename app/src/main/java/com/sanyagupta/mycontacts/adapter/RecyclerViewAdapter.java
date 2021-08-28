package com.sanyagupta.mycontacts.adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanyagupta.mycontacts.R;
import com.sanyagupta.mycontacts.ViewContact;
import com.sanyagupta.mycontacts.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        private Context context;
        private List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.contactname.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView contactname;
        public TextView phoneNumber;
        public ImageView iconbutton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            contactname = itemView.findViewById(R.id.name_text);
            phoneNumber = itemView.findViewById(R.id.phone_text);
            iconbutton = itemView.findViewById(R.id.icon_button);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            Contact contact = contactList.get(position);
            String name = contact.getName();
            String phone = contact.getPhoneNumber();
            int id = contact.getId();

            Intent intent = new Intent(context, ViewContact.class);
            intent.putExtra("Rname", name);
            intent.putExtra("Rphone", phone);
            intent.putExtra("Rid", id);
            context.startActivity(intent);
        }
    }
}
