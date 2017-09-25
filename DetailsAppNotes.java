package com.example.android.appnots;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;

public class DetailsAppNotes extends AppCompatActivity implements TextWatcher {

    int notId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_app_notes);

        EditText editText=(EditText)findViewById(R.id.editText);
        Intent i=getIntent();
         notId=i.getIntExtra("notId",-1);
        if(notId != -1)
        {
            editText.setText(MainActivity.notes.get(notId));
        }

        editText.addTextChangedListener(this);



    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        MainActivity.notes.set(notId, String.valueOf(s));
        MainActivity.arrayAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.android.appnots", Context.MODE_PRIVATE);
        if(MainActivity.set ==null)
        {
            MainActivity.set=new HashSet<String>();
        }
        else
        {
            MainActivity.set.clear();
        }


        MainActivity.set.addAll(MainActivity.notes);
        sharedPreferences.edit().remove("notes").apply();
        sharedPreferences.edit().putStringSet("notes",MainActivity.set).apply();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
