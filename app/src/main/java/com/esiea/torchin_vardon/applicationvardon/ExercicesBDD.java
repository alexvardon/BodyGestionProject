/*package com.esiea.torchin_vardon.applicationvardon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


 * Created by vardo_000 on 17/01/2016.

public class ExercicesBDD {

    //INITIALISATION DES DIFFERENTS PARAMETRES UTILES A LA CREATION DE LA BDD
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "bodygestion.db";

    private static final String TABLE_EXERCICES = "EXERCICE";

    private static final String COL_ID = "id";
    private static final String COL_NOM = "nom";
    private static final String COL_ICON = "iconPath";
    private static final String COL_MUSCLES = "muscles";
    private static final String COL_RESUME = "resume";

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

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;
    ///////////////////////////////////////////////////////////////////////////




    //METHODES ERMETTANT DE GERER LA BASE DE DONNEES (CREATION, OUVERTURE, FERMETURE, RECUPERATION)


    public ExercicesBDD(Context context){
        maBaseSQLite = new MaBaseSQLite(context);
    }


    public void open(){
        bdd = maBaseSQLite.getWritableDatabase();
    }


    public void close(){
        bdd.close();
    }


    public SQLiteDatabase getBDD(){
        return bdd;
    }
    ///////////////////////////////////////////////////////////////////////////


    //PERMET L'INSERTION D'UN EXERCICE DANS LA BASE DE DONNEES
    public long insertExercice(ExerciceTest exercice){
        ContentValues values = new ContentValues();

        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_NOM, exercice.getNom());
        values.put(COL_ICON, exercice.getIconPath());
        values.put(COL_MUSCLES, exercice.getMuscles());

        values.put(COL_RESUME, exercice.getResume());


        return bdd.insert(TABLE_EXERCICES, null, values);

    }





    public void insert(Context context) {
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        this.id=db.insert("EXERCICE", null, getContentValues());
        db.close();
    }


















    ///////////////////////////////////////////////////////////////////////////



    public int updateExercice(int id, ExerciceTest exercice){

        //il faut simple préciser quelle exercice on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();


        values.put(COL_NOM, exercice.getNom());
        values.put(COL_ICON, exercice.getIconPath());
        values.put(COL_MUSCLES, exercice.getMuscles());
        values.put(COL_RESUME, exercice.getResume());


        return bdd.update(TABLE_EXERCICES, values, COL_ID + " = " + id, null);
    }


    public ExerciceTest getExerciceWithTitre(String nom){

        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre
        Cursor c = bdd.query(TABLE_EXERCICES, new String[] {COL_ID, COL_NOM, COL_ICON, COL_MUSCLES, COL_RESUME}, COL_NOM + " LIKE \"" + nom +"\"", null, null, null, null);
        return cursorToExercice(c);
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


    public List<ExerciceTest> getAllExercice() {
        List<ExerciceTest> exercices = new ArrayList<ExerciceTest>();

        Cursor cursor = bdd.query(MaBaseSQLite.TABLE_EXERCICES,
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



}
*/