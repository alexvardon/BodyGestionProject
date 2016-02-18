package com.esiea.torchin_vardon.applicationvardon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by vardo_000 on 15/01/2016.
 */
public class ViewExerciceActivity extends Activity {

    TextView txtNomExercice;
    ImageView imgIconExercice;
    TextView txtDescriptionExercice;
    LinearLayout layoutMuscles;
    Exercice exercice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // affectation du fichier de layout
        setContentView(R.layout.activity_exercice__view);

        // Obtention des références sur les composants
        txtNomExercice = (TextView)findViewById(R.id.nomExercice);
        imgIconExercice= (ImageView)findViewById(R.id.imageExercice);
        txtDescriptionExercice= (TextView)findViewById(R.id.descriptionExercice);
        layoutMuscles = (LinearLayout)findViewById(R.id.layoutMuscles);

        Intent intent = getIntent();
        long exerciceId = intent.getLongExtra("exerciceId",-1);

        exercice = Exercice.getExercice(this, exerciceId);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Log.d("TAG", "Valeur de nom exercice : " + exercice.getNom());




        //On fixe l'image de l'exercice
        String fnm = exercice.getIconPath(); //  this is image file name
        String PACKAGE_NAME = this.getApplicationContext().getPackageName();
        int imgId = this.getResources().getIdentifier(PACKAGE_NAME+":drawable/"+fnm , null, null);
        System.out.println("IMG ID :: " + imgId);
        System.out.println("PACKAGE_NAME :: " + PACKAGE_NAME);
        imgIconExercice.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), imgId));

        txtNomExercice.setText(exercice.getNom());

        //On fixe les muscles de l'exercice
        for(String muscle : exercice.getMuscles()) {
            TextView textView = new TextView(this);
            textView.setText(muscle);
            textView.setTextColor(Color.WHITE);
            layoutMuscles.addView(textView);

        }

        //On fixe la description de l'exercice
        txtDescriptionExercice.setText(exercice.getResume());
    }

}
