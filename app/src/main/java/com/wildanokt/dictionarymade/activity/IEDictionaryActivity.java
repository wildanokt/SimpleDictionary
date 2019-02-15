package com.wildanokt.dictionarymade.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wildanokt.dictionarymade.R;
import com.wildanokt.dictionarymade.adapter.IEDictionaryAdapter;
import com.wildanokt.dictionarymade.db.DictionaryHelper;
import com.wildanokt.dictionarymade.model.EIDictionaryModel;
import com.wildanokt.dictionarymade.model.IEDictionaryModel;

import java.util.ArrayList;

public class IEDictionaryActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rvIE;
    IEDictionaryAdapter adapter;
    DictionaryHelper helper;

    EditText edtSearch;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_ie);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        helper = new DictionaryHelper(this);
        adapter = new IEDictionaryAdapter(this);

        rvIE = findViewById(R.id.rv_ie);
        rvIE.setLayoutManager(new LinearLayoutManager(this));
        rvIE.setAdapter(adapter);

        helper.open();
        ArrayList<IEDictionaryModel> arrayList = helper.getAllDataIE();
        helper.close();
        adapter.addItem(arrayList);

        edtSearch = findViewById(R.id.edt_search_ie);
        btnSearch = findViewById(R.id.btn_search_ie);
        btnSearch.setOnClickListener(this);

        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("Indonesia -> English");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_ie:
                    return true;
                case R.id.navigation_ei:
                    Intent intent = new Intent(IEDictionaryActivity.this, EIDictionaryActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onClick(View v) {
        if (!edtSearch.getText().equals("")){
            helper.open();
            ArrayList<IEDictionaryModel> arrayList = helper.getDataIE(edtSearch.getText().toString());
            helper.close();
            adapter.addItem(arrayList);
        }
    }
}
