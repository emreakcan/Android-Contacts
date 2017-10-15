package com.itsmee.contact.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.itsmee.R;
import com.itsmee.bean.User;
import com.itsmee.contact.Contact;
import com.itsmee.searchadapter.SearchAdapter;

import java.util.ArrayList;
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

    public ContactsAdapter(List<Contact> movies, Context context) {
        super(movies, context);
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


/*
    public ContactsAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item
        Contact contact = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.contact_view, parent, false);
        }
        // Populate the data into the template view using the data object
        TextView tvName = (TextView) view.findViewById(R.id.name);
        tvName.setText(contact.name);

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/test2.ttf");
        tvName.setTypeface(typeface);

        return view;
    }

}
*/
