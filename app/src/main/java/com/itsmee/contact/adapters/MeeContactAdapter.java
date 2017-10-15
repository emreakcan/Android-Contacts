package com.itsmee.contact.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itsmee.R;
import com.itsmee.bean.User;
import com.itsmee.searchadapter.SearchAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by emre on 15.10.2017.
 */

public class MeeContactAdapter extends SearchAdapter<User> {


    class ViewHolder {
        @InjectView(R.id.name)
        TextView name;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public MeeContactAdapter(List<User> movies, Context context) {
        super(movies, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.mee_contact_view, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(filteredContainer.get(position).getName());
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/test2.ttf");
        viewHolder.name.setTypeface(typeface);
        return convertView;
    }

}


