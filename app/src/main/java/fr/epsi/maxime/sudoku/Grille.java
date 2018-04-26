package fr.epsi.maxime.sudoku;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Grille extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        final String levelChoisi = (String)getIntent().getExtras().get("LEVEL");

        ListView listGrilles = (ListView) findViewById(R.id.grilles);
        List<DoGrille> mesDoGrilles = genererDoGrilles(levelChoisi);

        GrilleAdapter adapter = new GrilleAdapter(Grille.this, mesDoGrilles);
        listGrilles.setAdapter(adapter);

        listGrilles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id){
                Intent intent = new Intent(Grille.this, Sudoku.class);
                intent.putExtra("LEVEL", levelChoisi);
                intent.putExtra("GRILLE", ""+pos);
                startActivity(intent);
            }
        });

    }

    private List<DoGrille> genererDoGrilles(String lvl){
        List<DoGrille> doGrilles = new ArrayList<DoGrille>();
        for(int i = 0 ; i < 100; i++) {
            doGrilles.add(new DoGrille(Integer.parseInt(lvl), i+1));
        }
        return doGrilles;
    }

}
