package fr.epsi.maxime.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Level extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        int nbLevel = 2;
        LinearLayout levels = findViewById(R.id.levels);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for(int i = 1 ; i <= nbLevel ; i++){
            Button level = new Button(this);
            level.setText("Niveau " + i);
            level.setId(i);
            level.setLayoutParams(params);
            level.setOnClickListener(myListener);
            levels.addView(level);
        }

    }

    private View.OnClickListener myListener = new View.OnClickListener() {
        public void onClick(View v) {
            Button b = (Button)v;
            Intent intent = new Intent(Level.this, Grille.class);
            intent.putExtra("LEVEL", (b.getText().toString()).split(" ")[1]);
            startActivity(intent);

        }
    };
}
