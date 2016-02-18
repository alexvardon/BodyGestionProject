package com.esiea.torchin_vardon.applicationvardon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class PrimaryFragment extends Fragment {
    ListView list;
    List<ExerciceTest> listExercice;

    private static final int NUM_COL_ID = 0;
    private static final int NUM_COL_NOM = 1;
    private static final int NUM_COL_ICON = 2;
    private static final int NUM_COL_MUSCLES = 3;
    private static final int NUM_COL_RESUME = 4;




    private String[] allColumns = {MaBaseSQLite.COL_ID,
            MaBaseSQLite.COL_ID,
            MaBaseSQLite.COL_NOM,
            MaBaseSQLite.COL_ICON,
            MaBaseSQLite.COL_MUSCLES,
            MaBaseSQLite.COL_RESUME };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.sport_layout, container, false);
        ArrayList<ExerciceTest> exerciceList = ExerciceTest.getExercice(v.getContext());
        ExerciceAdapter exerciceAdapter = new ExerciceAdapter(getActivity(), exerciceList);


        list=(ListView) v.findViewById(R.id.list_exercice_sport);

        list.setAdapter(exerciceAdapter);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                startViewExerciceActivity(arg3);
            }
        });






        /*SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.esiea.torchin_vardon.applicationvardon.prefs", Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("embeddedDataInserted", false))
            readEmbeddedData();*/


        return v;
    }


    public void startViewExerciceActivity(long exerciceId){
        Intent intent = new Intent(getView().getContext(), ViewExerciceActivity.class);
        intent.putExtra("exerciceId", exerciceId);
        startActivity(intent);
    }






    //////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    private void readEmbeddedData() {
        InputStreamReader reader = null;
        InputStream file = null;
        BufferedReader bufferedReader = null;
        try {
            file = getActivity().getAssets().open("data.txt");
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
                    exercice.insert(getActivity());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    reader.close();
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.esiea.torchin_vardon.applicationvardon.prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("embeddedDataInserted", true);
                    editor.commit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }






    public List<ExerciceTest> getAllExercice(Context context) {
        List<ExerciceTest> exercices = new ArrayList<ExerciceTest>();

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query("EXERCICE",
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ExerciceTest exercice = cursorToExercice(cursor);
            exercices.add(exercice);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return exercices;
    }


    //Cette méthode permet de convertir un cursor en un exercice
    private ExerciceTest cursorToExercice(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();

        ExerciceTest exercice = new ExerciceTest();
        exercice.setId(c.getInt(NUM_COL_ID));
        exercice.setNom(c.getString(NUM_COL_NOM));
        exercice.setIconPath(c.getString(NUM_COL_ICON));
        exercice.setMuscles(c.getString(NUM_COL_MUSCLES));
        exercice.setResume(c.getString(NUM_COL_RESUME));

        c.close();

        return exercice;
    }
}