package com.esiea.torchin_vardon.applicationvardon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MaBaseSQLite extends SQLiteOpenHelper {

    static String DB_NAME="bodygestion.db";
    static int DB_VERSION=2;

    public static final String TABLE_EXERCICES = "EXERCICE";
    public static final String COL_ID = "id";
    public static final String COL_NOM = "nom";
    public static final String COL_ICON = "iconPath";
    public static final String COL_MUSCLES = "muscles";
    public static final String COL_RESUME = "resume";

    String tableExercice = "CREATE TABLE EXERCICE(" +
            "id INTEGER PRIMARY KEY," +
            "nom TEXT," +
            " iconPath TEXT," +
            " muscles TEXT," +
            " resume TEXT);";


    public MaBaseSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableExercice);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_EXERCICES + ";");
        onCreate(db);
    }
}
