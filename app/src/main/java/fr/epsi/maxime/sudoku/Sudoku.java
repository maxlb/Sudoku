package fr.epsi.maxime.sudoku;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Sudoku extends Activity {

    public static int[][] monSudoku;
    public static int[][] sudokuInitial;
    public static int selectedVal = 0;
    static TextView winTV;
    Sudoku contexte;

    @Override
    public boolean onNavigateUp(){
        Intent intent = new Intent(Sudoku.this, Grille.class);
        intent.putExtra("LEVEL", (String)getIntent().getExtras().get("LEVEL"));
        startActivity(intent);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        contexte = this;

        String levelChoisi = (String)getIntent().getExtras().get("LEVEL");
        String grilleChoisie = (String)getIntent().getExtras().get("GRILLE");

        winTV = (TextView)findViewById(R.id.gagne);

        final int[][] sudokuInitial = getSudoku(levelChoisi, grilleChoisie);
        this.sudokuInitial = sudokuInitial;

        monSudoku = getSudoku(levelChoisi, grilleChoisie);

        for(int j = 0 ; j < 10 ; j++) {
            int valID = getResources().getIdentifier("val" + j, "id", getPackageName());
            TextView tv = (TextView)findViewById(valID);
            tv.setOnClickListener(getValClick);
        }

        DessinSudoku monDessin = (DessinSudoku)findViewById(R.id.dessin);
        monDessin.setOnTouchListener(getCaseClick);

    }

    View.OnClickListener getValClick = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            TextView tv = (TextView)contexte.findViewById(v.getId());
            selectedVal = Integer.parseInt(tv.getText().toString());

            for(int j = 0 ; j < 10 ; j++) {
                int valID = contexte.getResources().getIdentifier("val" + j, "id", getPackageName());
                TextView tvw = (TextView)findViewById(valID);
                tvw.setBackgroundColor(Color.TRANSPARENT);
                tvw.setTextColor(Color.GRAY);
            }

            tv.setBackgroundColor(Color.DKGRAY);
            tv.setTextColor(Color.WHITE);
        }
    };

    View.OnTouchListener getCaseClick = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent e){
            if(!DessinSudoku.isWin()){
                if (e.getAction() == MotionEvent.ACTION_DOWN){
                    DessinSudoku monDessin = (DessinSudoku)contexte.findViewById(v.getId());
                    int t = monDessin.getWidth()/9;
                    int y = (int)e.getX()/t;
                    int x = (int)e.getY()/t;

                    if(DessinSudoku.cases[x][y].isCanModif()){
                        monSudoku[x][y] = selectedVal;
                        monDessin.invalidate();
                    }

                }
            }
            return true;
        }
    };

    private int[][] getSudoku(String lvl, String num) {
        int[][] grilleVal = new int[9][9];

        String grilleInit = getLigne(lvl, num);

        String[] grilleInitArray = grilleInit.split("");

        int c = 1;
        for(int i = 0 ; i < 9 ; i++) {
            for(int j = 0 ; j < 9 ; j++) {
                grilleVal[i][j] = Integer.parseInt(grilleInitArray[c]);
                c++;
            }
        }

        return grilleVal;
    }

    private String getLigne(String lvl, String num) {
        int fileResourceId;
        if (lvl.equals("1")) {
            fileResourceId = R.raw.level1;
        } else {
            fileResourceId = R.raw.level2;
        }

        InputStream is = this.getResources().openRawResource(fileResourceId);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String str = "";
        String grilleInit = "";

        try {
            int c = 0;
            while ((str = reader.readLine()) != null) {
                if(c == Integer.parseInt(num)) {
                    grilleInit = str;
                }
                c++;
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return grilleInit;
    }

    public static boolean isNotEnd(int[][] grille) {
        for(int i = 0 ; i < 9 ; i++) {
            for(int j = 0 ; j < 9 ; j++) {
                if(grille[i][j] == 0){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isInRow(int[][] grille, int rowIndex, int columnIndex, int nbToFind) {
        for(int i = 0 ; i < 9 ; i++) {
            if(i != columnIndex){
                if (grille[rowIndex][i] == nbToFind) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isInColumn(int[][] grille, int rowIndex, int columnIndex, int nbToFind) {
        for(int i = 0 ; i < 9 ; i++) {
            if(i != rowIndex){
                if (grille[i][columnIndex] == nbToFind) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isInSquare(int[][] grille, int rowIndex, int columnIndex, int nbToFind) {
        int x;
        if(columnIndex/3 < 1){
            x = 0;
        } else if(columnIndex/3 < 2){
            x = 3;
        } else {
            x = 6;
        }
        int y;
        if(rowIndex/3 < 1){
            y = 0;
        } else if(rowIndex/3 < 2){
            y = 3;
        } else {
            y = 6;
        }
        Log.d("X", String.valueOf(x));
        Log.d("Y", String.valueOf(y));

        for (int i = x ; i < x + 3 ; i++){
            for (int j = y ; j < y + 3; j++) {
                if(!(j == rowIndex && i == columnIndex)){
                    if (grille[j][i] == nbToFind) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}