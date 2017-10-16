package com.itsmee.views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itsmee.R;
import com.itsmee.bean.User;
import com.itsmee.contact.adapters.MeeContactAdapter;
import com.itsmee.retrofit.Connect;
import com.itsmee.searchadapter.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MeeContact extends Fragment {


    private List<User> itemList = new ArrayList<>();
    private ListView listview;
    private EditText search;
    private RelativeLayout layout;
    public static PhoneContact newInstance() {
        PhoneContact fragment = new PhoneContact();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mee_contact, container, false);

        listview = (ListView) view.findViewById(R.id.meeContacts);

        search = (EditText) view.findViewById(R.id.searchp);

        layout = (RelativeLayout)view.findViewById(R.id.layout);

        layout.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        layout.setFocusableInTouchMode(true);

        getData();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle(itemList.get(position).getName());
                alert.setMessage("\n\n\n" + itemList.get(position).getPhone() + "\n" + itemList.get(position).getEmail());
                final TextView input = new TextView(getActivity());
                alert.setView(input);

                alert.setPositiveButton("DELETE CONTACT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });


                alert.show();


            }
        });

        return view;
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())// aldığımız Json verisini Gson a çevirmiş oluyoruz.
                .build();

        Connect connect = retrofit.create(Connect.class);
        Call<List<User>> call = connect.getUsers();

        itemList.clear();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                itemList = response.body();


                if (itemList != null) {

                    final SearchAdapter itemAdpter = new MeeContactAdapter(itemList, getContext()).registerFilter(User.class, "name").setIgnoreCase(true);
                    listview.setAdapter(itemAdpter);

                    search.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            itemAdpter.filter(s.toString());
                            System.out.println(s.toString());
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    });

                }


            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }


}
