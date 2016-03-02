package com.esiea.torchin_vardon.applicationvardon;
// Pas utilisé on utilise exercice test

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Exercice {
    long id;
    String nom;
    String iconPath;
    String[]muscles;
    String resume;




    public Exercice() {

    }


    private Exercice(Cursor cursor) {
        //  Permet de recup les objets (?)
        id = cursor.getLong(cursor.getColumnIndex("id"));
        nom = cursor.getString(cursor.getColumnIndex("nom"));
        iconPath = cursor.getString(cursor.getColumnIndex("iconPath"));
        muscles = cursor.getString(cursor.getColumnIndex("muscles")).split(";");
        resume = cursor.getString(cursor.getColumnIndex("resume"));









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

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String[] getMuscles() {
        return muscles;
    }

    public void setMuscles(String[] muscles) {
        this.muscles = muscles;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }


    public static ArrayList<Exercice> getExerciceList(Context context) {
        ArrayList<Exercice> listExercice = new ArrayList<Exercice>();
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);// BDD
        SQLiteDatabase db = helper.getReadableDatabase();// recup la data base
        Cursor cursor = db.query(true, "EXERCICE", new String[]{"id", "nom", "iconPath", "muscles", "resume"},
                null, null,null,null,"nom", null  );

        while (cursor.moveToNext()) {
            listExercice.add(new Exercice(cursor));
        }

        cursor.close();
        db.close();

        return listExercice;
    }

    public static Exercice getExercice(Context context, long id) {
        Exercice exercice = null;
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String where ="id = " + String.valueOf(id);
        Cursor cursor = db.query(true, "EXERCICE", new String[]{"id", "nom", "iconPath", "muscles", "resume"},
                where, null,null,null,"nom", null  );

        if(cursor.moveToFirst())
            exercice = new Exercice(cursor);

        cursor.close();
        db.close();

        return exercice;
    }

    public void insert(Context context) {
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        this.id=db.insert("EXERCICE", null, getContentValues());
        db.close();
    }

    public void update(Context context) {
        String whereClause = "id=" + String.valueOf(this.id);
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.update("EXERCICE", getContentValues(),whereClause,null);
        db.close();
    }

    public void delete(Context context) {
        String whereClause = "id= ?" ;
        String[] whereArgs = new String[1];
        whereArgs[0] = String.valueOf(this.id);
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("EXERCICE", whereClause,whereArgs);
        db.close();
    }

    private ContentValues getContentValues()  {
        ContentValues values = new ContentValues();
        values.put("nom",this.nom);
        values.put("iconPath",this.iconPath);
        if(this.muscles!=null) {
            String listMuscles = new String();
            for(int i =0;i<this.muscles.length;i++) {
                listMuscles+=this.muscles[i];
                if(i<this.muscles.length-1)
                    listMuscles+=";";
            }
            values.put("muscles",listMuscles);
    }
        values.put("resume",this.resume);
        return values;
    }

}
