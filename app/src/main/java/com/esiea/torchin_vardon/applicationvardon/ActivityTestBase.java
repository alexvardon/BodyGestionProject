/*package com.esiea.torchin_vardon.applicationvardon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ActivityTestBase extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_layout);

        SharedPreferences sharedPreferences = getSharedPreferences("com.esiea.torchin_vardon.applicationvardon.prefs", Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("embeddedDataInserted", false))
            readEmbeddedData();
    }

    @Override
    public void onResume(){
        super.onResume();
        ArrayList<Exercice> exerciceList = Exercice.getExerciceList(this);
        ExerciceAdapter exerciceAdapter = new ExerciceAdapter(this, exerciceList);
        list = (ListView) findViewById(R.id.list_exercice_sport);
        list.setAdapter(exerciceAdapter);
    }

    private void readEmbeddedData() {
        InputStreamReader reader = null;
        InputStream file = null;
        BufferedReader bufferedReader = null;
        try {
            file = getAssets().open("data.txt");
            reader = new InputStreamReader(file);
            bufferedReader = new BufferedReader(reader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data != null && data.length == 4) {
                    Exercice exercice = new Exercice();
                    exercice.nom = data[0];
                    exercice.iconPath = data[1];
                    exercice.muscles = data[2].split(",");
                    exercice.resume = data[3];
                    exercice.insert(this);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    reader.close();
                    SharedPreferences sharedPreferences = getSharedPreferences("com.esiea.torchin_vardon.applicationvardon.prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("embeddedDataInserted", true);
                    editor.commit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}*/
