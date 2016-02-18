package com.esiea.torchin_vardon.applicationvardon;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by vardo_000 on 22/01/2016.
 */
public class Seance {
    public long id;
    private String nom;
    private int series;
    private int repetitions;

    public Seance(String nom, int series, int repetitions){
        this.nom = nom;
        this.series = series;
        this.repetitions = repetitions;
    }

    private Seance(Cursor cursor) {
        //  DVD dvd = new DVD();
        id = cursor.getLong(cursor.getColumnIndex("id"));
        nom = cursor.getString(cursor.getColumnIndex("nom"));
        series = cursor.getInt(cursor.getColumnIndex("series"));
        repetitions = cursor.getInt(cursor.getColumnIndex("repetitions"));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSeries(){
        return series;
    }

    public void setSeries(int series){
        this.series = series;
    }

    public int getRepetitions(){
        return repetitions;
    }

    public void setRepetitions(int repetitions){
        this.repetitions = repetitions;
    }



    public ContentValues getContentValues()  {
        ContentValues values = new ContentValues();
        values.put("nom",this.nom);
        values.put("series", this.series);
        values.put("repetitions", this.repetitions);
        return values;
    }

}
