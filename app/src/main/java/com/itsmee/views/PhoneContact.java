package com.itsmee.views;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.itsmee.R;
import com.itsmee.bean.User;
import com.itsmee.contact.Contact;
import com.itsmee.contact.ContactFetcher;
import com.itsmee.contact.adapters.ContactsAdapter;
import com.itsmee.searchadapter.SearchAdapter;

import java.util.ArrayList;

public class PhoneContact extends Fragment {

    ArrayList<Contact> listContacts;
    ListView lvContacts;
    final int myPermissionInt = 0;
    private EditText search;

    public static PhoneContact newInstance() {
        PhoneContact fragment = new PhoneContact();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_phone_contact, container, false);

        search = (EditText)view.findViewById(R.id.searchp);

        lvContacts = (ListView) view.findViewById(R.id.lvContacts);

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {


            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    myPermissionInt);


        } else {
            listContacts = new ContactFetcher(getActivity()).fetchAll();
            final SearchAdapter itemAdpter = new ContactsAdapter(listContacts, getContext()).registerFilter(Contact.class, "name").setIgnoreCase(true);
            lvContacts.setAdapter(itemAdpter);

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


        return view;


    }


    private void toaster(String s) {
        Toast.makeText(getActivity(), s,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case myPermissionInt: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    listContacts = new ContactFetcher(getActivity()).fetchAll();

                    final SearchAdapter itemAdpter = new ContactsAdapter(listContacts, getContext()).registerFilter(Contact.class, "name").setIgnoreCase(true);
                    lvContacts.setAdapter(itemAdpter);

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

                } else {


                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}
