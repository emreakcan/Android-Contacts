package com.itsmee.contact.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itsmee.R;
import com.itsmee.contact.Contact;
import com.itsmee.searchadapter.SearchAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ContactsAdapter extends SearchAdapter<Contact> {


    class ViewHolder {
        @InjectView(R.id.name)
        TextView name;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public ContactsAdapter(List<Contact> contacts, Context context) {
        super(contacts, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.mee_contact_view, null);
            viewHolder = new ContactsAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ContactsAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(filteredContainer.get(position).name);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/test2.ttf");
        viewHolder.name.setTypeface(typeface);
        return convertView;
    }

}
