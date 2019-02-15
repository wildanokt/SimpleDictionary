package com.wildanokt.dictionarymade.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.wildanokt.dictionarymade.R;

public class DictionaryDetailItem extends AppCompatActivity {

    TextView tvHeader, tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_detail_item);

        tvHeader = findViewById(R.id.tv_detail_header);
        tvHeader.setText(getIntent().getStringExtra("HEADER"));

        tvContent = findViewById(R.id.tv_detail_content);
        tvContent.setText(getIntent().getStringExtra("CONTENT"));

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(tvHeader.getText().toString());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
