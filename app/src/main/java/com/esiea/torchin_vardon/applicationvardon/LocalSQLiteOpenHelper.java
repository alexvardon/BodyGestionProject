package com.esiea.torchin_vardon.applicationvardon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalSQLiteOpenHelper extends SQLiteOpenHelper {

    static String DB_NAME="sport.db";
    static int DB_VERSION=2;

    public static final String SEANCE_TABLE = "SEANCE";
    public static final String EXERCICE_TABLE = "EXERCICE";

    public static final String ID_COLUMN = "id";
   // public static final String SEANCE_NOM_COLUMN = "sce_nom";
    public static final String NOM_COLUMN = "nom";
    public static final String SERIES_COL = "series";
    public static final String REPETITIONS_COL = "repetitions";

    String tableExercice = "CREATE TABLE EXERCICE(" +
            "id INTEGER PRIMARY KEY," +
            "nom TEXT," +
            " iconPath TEXT," +
            " muscles TEXT," +
            " resume TEXT);";

    /*String tableSeance = "CREATE TABLE SEANCE(" +
            "id_sceance INTEGER PRIMARY KEY," +
            "FOREIGN KEY(nom) TEXT REFERENCES EXERCICE(nom)," +
            "series INTEGER," +
            "repetitions INTEGER);";*/

    public static final String tableSeance = "CREATE TABLE "
            + SEANCE_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY, "
            + NOM_COLUMN + " TEXT, "
            + SERIES_COL + " INT, "
            + REPETITIONS_COL + " INT, "
            + "FOREIGN KEY(" + NOM_COLUMN + ") REFERENCES "
            + EXERCICE_TABLE + "(nom) " + ")";


    public LocalSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(tableExercice);
        db.execSQL(tableSeance);


        ExerciceTest exercice = new ExerciceTest(
                "Sylvain",
                "torchin",
                "Muscles1,Muscles2",
                "Je m'appelle Sylvain Torchin et je suis nul a LOL mais chut, il faut pas que ça se sache ;)"
        );

        ExerciceTest exercice2 = new ExerciceTest(
                "Alexandre",
                "vardon",
                "Muscles1,Muscles2",
                "Normalement il y a un text de description de l'exercice POMPES mais là comme j'ai pas trop d'inspiration et que je Nono a bientot fini ses cours j'ai pas le temps pour vraiment faire quelque chose de bien ^^ Mais si vous voulez vous pouvez avoir la description du film StarTrek en cliquant sur les autres exercices ;)"
        );

        ExerciceTest exercice3 = new ExerciceTest(
                "Maxime",
                "fernandez",
                "Muscles1,Muscles2",
                "Je m'appelle Maxime Fernandez et le travaille que je fais pour le colloque est 1000x plus importants que pour n'importe quel proje du monde mais chut, c'est un secret ;)"
        );

        Seance seance1 = new Seance(
                "Maxime",
                5,
                4
        );

        Seance seance3 = new Seance(
                "Alexandre",
                5,
                4
        );

        Seance seance2 = new Seance(
                "Sylvain",
                2,
                1
        );

        /*
        NE PAS OUBLIER DE CREER LA CLASSE SCEANCES POUR POUVOIR INSERER DES VALEURS
         */

//        db = this.getWritableDatabase();
        exercice.id=db.insert("EXERCICE", null, exercice.getContentValues());
        exercice2.id=db.insert("EXERCICE", null, exercice2.getContentValues());
        exercice3.id=db.insert("EXERCICE", null, exercice3.getContentValues());



        seance1.id=db.insert("SEANCE", null, seance1.getContentValues());
        seance2.id=db.insert("SEANCE", null, seance2.getContentValues());
        seance3.id=db.insert("SEANCE", null, seance3.getContentValues());


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + "SEANCE" + ";");
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }



}
