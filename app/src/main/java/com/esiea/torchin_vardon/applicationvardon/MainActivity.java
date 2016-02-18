package com.esiea.torchin_vardon.applicationvardon;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(this);

        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;





        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();

        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();




                if (menuItem.getItemId() == R.id.nav_item_sent) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();

                }

                if (menuItem.getItemId() == R.id.nav_item_reload) {
                    /*db = helper.getWritableDatabase();
                    helper.onUpgrade(db, 0, 0);*/
                    Seance seance2 = new Seance(
                            "Sylvain",
                            2,
                            1
                    );
                    db = helper.getWritableDatabase();
                    seance2.id=db.insert("SEANCE", null, seance2.getContentValues());


                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);


       // toolbar.setLogo(R.mipmap.ic_launcher);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        /*SharedPreferences sharedPreferences = getSharedPreferences("com.esiea.torchin_vardon.applicationvardon.prefs", Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("embeddedDataInserted", false))
            readEmbeddedData();*/

    }

    /*@Override
    public void onResume(){
        super.onResume();
        ArrayList<Exercice> exerciceList = Exercice.getExerciceList(this);
        ExerciceAdapter exerciceAdapter = new ExerciceAdapter(this, exerciceList);
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
                    SharedPreferences sharedPreferences = getSharedPreferences("com.esiea.torchin_vardon.applicationvardon.prefs", ContextContext.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("embeddedDataInserted", true);
                    editor.commit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}