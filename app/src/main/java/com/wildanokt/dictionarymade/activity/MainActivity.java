package com.wildanokt.dictionarymade.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wildanokt.dictionarymade.R;
import com.wildanokt.dictionarymade.db.DictionaryHelper;
import com.wildanokt.dictionarymade.model.EIDictionaryModel;
import com.wildanokt.dictionarymade.model.IEDictionaryModel;
import com.wildanokt.dictionarymade.preference.AppPreference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProgressBar pgBar;
    TextView tvProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pgBar = findViewById(R.id.progress_bar);
        tvProgress = findViewById(R.id.tv_progress_name);

        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {
        final String TAG = LoadData.class.getSimpleName();

        DictionaryHelper helper;
        AppPreference preference;

        double progress;
        double maxProgress = 100;

        @Override
        protected void onPreExecute() {
            helper = new DictionaryHelper(MainActivity.this);
            preference = new AppPreference(MainActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean firstRun = preference.getFirstRun();

            if (firstRun){
                ArrayList<IEDictionaryModel> arrayListIE = preLoadRawIE();
                ArrayList<EIDictionaryModel> arrayListEI = preLoadRawEI();
                helper.open();
                progress = 30;
                publishProgress((int)progress);
                Double progressMaxInsert = 80.0;
                Double progressDiff = (progressMaxInsert - progress)/arrayListIE.size();

                helper.beginTransaction();
                try {
                    for (IEDictionaryModel model : arrayListIE) {
                        helper.insertTransactionIE(model);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                    progress = 30;
                    for (EIDictionaryModel model2 : arrayListEI) {
                        helper.insertTransactionEI(model2);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                    helper.setTransactionSuccess();
                    Log.d(TAG, "doInBackground: Data store success");
                }catch (Exception e){
                    Log.e(TAG, "doInBackground: Error: "+e.getMessage() );
                }

                helper.endTransaction();
                helper.close();

                preference.setFirstRun(false);
                publishProgress((int) maxProgress);
            }else {
                try {
                    synchronized (this){
                        this.wait(2000);
                        publishProgress(50);
                        this.wait(2000);
                        publishProgress((int) maxProgress);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            pgBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent(MainActivity.this, IEDictionaryActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public ArrayList<IEDictionaryModel> preLoadRawIE(){
        ArrayList<IEDictionaryModel> arrayList = new ArrayList<>();
        String line;
        BufferedReader reader;

        try {
            Resources resources = getResources();
            InputStream stream = resources.openRawResource(R.raw.indonesia_english);
            reader = new BufferedReader(new InputStreamReader(stream));

            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                IEDictionaryModel model = new IEDictionaryModel(splitstr[0],splitstr[1]);
                arrayList.add(model);
            }while (line!=null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return arrayList;
    }
    public ArrayList<EIDictionaryModel> preLoadRawEI(){
        ArrayList<EIDictionaryModel> arrayList = new ArrayList<>();
        String line;
        BufferedReader reader;

        try {
            Resources resources = getResources();
            InputStream stream = resources.openRawResource(R.raw.english_indonesia);
            reader = new BufferedReader(new InputStreamReader(stream));

            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                EIDictionaryModel model = new EIDictionaryModel(splitstr[0],splitstr[1]);
                arrayList.add(model);
            }while (line!=null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return arrayList;
    }

}
