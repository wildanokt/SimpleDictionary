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
import com.wildanokt.dictionarymade.adapter.EIDictionaryAdapter;
import com.wildanokt.dictionarymade.db.DictionaryHelper;
import com.wildanokt.dictionarymade.model.EIDictionaryModel;

import java.util.ArrayList;

public class EIDictionaryActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rvEI;
    EIDictionaryAdapter adapter;
    DictionaryHelper helper;

    EditText edtSearch;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_ei);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        helper = new DictionaryHelper(this);
        adapter = new EIDictionaryAdapter(this);

        rvEI = findViewById(R.id.rv_ei);
        rvEI.setHasFixedSize(true);
        rvEI.setLayoutManager(new LinearLayoutManager(this));
        rvEI.setAdapter(adapter);

        //load data
        helper.open();
        ArrayList<EIDictionaryModel> arrayList = helper.getAllDataEI();
        helper.close();
        adapter.addItem(arrayList);

        edtSearch = findViewById(R.id.edt_search_ei);
        btnSearch = findViewById(R.id.btn_search_ei);
        btnSearch.setOnClickListener(this);

        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("English -> Indonesia");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_ie:
                    Intent intent = new Intent(EIDictionaryActivity.this, IEDictionaryActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_ei:
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onClick(View v) {
        if (!edtSearch.getText().equals("")){
            helper.open();
            ArrayList<EIDictionaryModel> arrayList = helper.getDataEI(edtSearch.getText().toString());
            helper.close();
            adapter.addItem(arrayList);
        }
    }
}
