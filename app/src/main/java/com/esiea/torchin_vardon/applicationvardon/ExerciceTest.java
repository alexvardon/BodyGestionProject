package com.esiea.torchin_vardon.applicationvardon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vardo_000 on 17/01/2016.
 */
public class ExerciceTest {

    private static final String COL_ID = "id";
    private static final String COL_NOM = "nom";
    private static final String COL_ICON = "iconPath";
    private static final String COL_MUSCLES = "muscles";
    private static final String COL_RESUME = "resume";
    private static final String COL_SERIES = "series";
    private static final String COL_REPETITIONS = "repetitions";

    public static final String EXERCICE_ID_WITH_PREFIX = "exe.id";
    public static final String EXERCICE_NOM_WITH_PREFIX = "exe.nom";
    public static final String EXERCICE_ICON_WITH_PREFIX = "exe.iconPath";
    public static final String EXERCICE_MUSCLES_WITH_PREFIX = "exe.muscles";
    public static final String EXERCICE_RESUME_WITH_PREFIX = "exe.resume";

    public static final String EXERCICE_TABLE = "EXERCICE";
    public static final String SEANCE_TABLE = "SEANCE";

    public static final String SCEANCE_SERIES_WITH_PREFIX = "sce.series";
    public static final String SCEANCE_REPETITIONS_WITH_PREFIX = "sce.repetitions";


    public static final String EMPLOYEE_NAME_WITH_PREFIX = "emp.name";
    public static final String DEPT_NAME_WITH_PREFIX = "dept.name";


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

    public long id;
    private String nom;
    private String iconPath;
    private String muscles;
    private String[] tabMuscles;
    private String resume;
    private int series;
    private int repetitions;



    public ExerciceTest() {
    }

    public ExerciceTest(String nom, String iconPath, String muscles, String resume){
        this.nom = nom;
        this.iconPath = iconPath;
        this.muscles = muscles;
        this.resume = resume;
        this.tabMuscles = muscles.split(",");

    }

    private ExerciceTest(Cursor cursor) {
        //  DVD dvd = new DVD();
        id = cursor.getLong(cursor.getColumnIndex("id"));
        nom = cursor.getString(cursor.getColumnIndex("nom"));
        iconPath = cursor.getString(cursor.getColumnIndex("iconPath"));
        muscles = cursor.getString(cursor.getColumnIndex("muscles"));
        tabMuscles = muscles.split(",");
        resume = cursor.getString(cursor.getColumnIndex("resume"));
    }



///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

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

    public String getMuscles() {
        return muscles;
    }

    public void setMuscles(String muscles) {
        this.muscles = muscles;
    }

    public String[] getTabMuscles() {
        return tabMuscles;
    }

    public void setTabMuscles(String[] tabMuscles) {
        this.tabMuscles = tabMuscles;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public long getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public long getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    ///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

    public static ArrayList<ExerciceTest> getExerciceList(Context context) {
        ArrayList<ExerciceTest> listExercice = new ArrayList<ExerciceTest>();
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(true, "EXERCICE", new String[]{"id", "nom", "iconPath", "muscles", "resume"},
                null, null,null,null,"nom", null  );

        while (cursor.moveToNext()) {
            listExercice.add(new ExerciceTest(cursor));
        }

        cursor.close();
        db.close();

        return listExercice;
    }



    public static ExerciceTest getExercice(Context context, long id) {
        ExerciceTest exercice = null;
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String where ="id = " + String.valueOf(id);
        Cursor cursor = db.query(true, "EXERCICE", new String[]{"id", "nom", "iconPath", "muscles", "resume"},
                where, null,null,null,"nom", null  );

        if(cursor.moveToFirst())
            exercice = new ExerciceTest(cursor);

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
        db.delete("EXERCICE", whereClause, whereArgs);
        db.close();
    }

    public ContentValues getContentValues()  {
        ContentValues values = new ContentValues();
        values.put("nom",this.nom);
        values.put("iconPath", this.iconPath);
        values.put("muscles", this.muscles);
        values.put("resume", this.resume);
        return values;
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

    public static ArrayList<ExerciceTest> getExercice(Context context) {

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();


        ArrayList<ExerciceTest> exercices = new ArrayList<ExerciceTest>();
      /* String query = "SELECT " + EXERCICE_ID_WITH_PREFIX + ","
                + EXERCICE_NOM_WITH_PREFIX + "," + COL_SERIES
                + "," + EXERCICE_MUSCLES_WITH_PREFIX + ","
                + EXERCICE_RESUME_WITH_PREFIX + SCEANCE_SERIES_WITH_PREFIX + ","
                + SCEANCE_REPETITIONS_WITH_PREFIX + ","
                + " FROM EXERCICE exe, SEANCE sce WHERE exe.nom = sce.nom";*/

        // Building query using INNER JOIN keyword
        String query = "SELECT " + EXERCICE_ID_WITH_PREFIX + ","
        + EXERCICE_NOM_WITH_PREFIX + "," + COL_ICON
        + "," + COL_MUSCLES + "," + COL_RESUME + "," + COL_SERIES + ","
        + COL_REPETITIONS + " FROM "
        + EXERCICE_TABLE + " exe INNER JOIN "
        + SEANCE_TABLE + " sce ON exe."
        + COL_NOM + " = sce."
        + COL_NOM;

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            ExerciceTest myExercice = new ExerciceTest();
            myExercice.setId(cursor.getInt(0));
            myExercice.setNom(cursor.getString(1));
            myExercice.setIconPath(cursor.getString(2));
            myExercice.setMuscles(cursor.getString(3));
            myExercice.setResume(cursor.getString(4));
            myExercice.setSeries(cursor.getInt(5));
            myExercice.setRepetitions(cursor.getInt(6));

            exercices.add(myExercice);
        }

        cursor.close();
        db.close();

        return exercices;
    }




}
