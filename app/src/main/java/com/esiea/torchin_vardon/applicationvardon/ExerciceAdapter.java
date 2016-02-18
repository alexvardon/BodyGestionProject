package com.esiea.torchin_vardon.applicationvardon;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ExerciceAdapter extends ArrayAdapter<ExerciceTest> {

    Context context;
    public ExerciceAdapter(Context context, List<ExerciceTest> objects) {
        super(context, -1, objects);
        this.context = context;
    }

    @Override
    public long getItemId(int pos) {
        return getItem(pos).id;
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        View view=null;
        if(convertView==null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listitem_exercice, null);
        } else {
            view = convertView;
        }

        ExerciceTest exercice = getItem(pos);
        // la référence au dvd courant est directement stocké dans la vue courante.
        view.setTag(exercice);

        TextView titre =(TextView)view.findViewById(R.id.nom_exercice_layout);
        ImageView icon = (ImageView)view.findViewById(R.id.ceciestuntest);


        String fnm = exercice.getIconPath(); //  this is image file name
        String PACKAGE_NAME = context.getApplicationContext().getPackageName();
        int imgId = context.getResources().getIdentifier(PACKAGE_NAME+":drawable/"+fnm , null, null);
        System.out.println("IMG ID :: "+imgId);
        System.out.println("PACKAGE_NAME :: "+PACKAGE_NAME);
//    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),imgId);
        icon.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), imgId));


        titre.setText(exercice.getNom());





        //exercice.setIconExo(exercice.getIconExo());

        return view;
    }

}