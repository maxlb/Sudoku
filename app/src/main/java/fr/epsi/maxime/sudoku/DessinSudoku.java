package fr.epsi.maxime.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by maxim on 25/04/2018.
 */


public class DessinSudoku extends View {

    public static caseSudoku[][] cases;

    public DessinSudoku(Context c, AttributeSet as){
        super(c,as);
    }

    @Override
    public void onDraw(Canvas c){
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.BLACK);
        cases = new caseSudoku[9][9];

        int t = c.getHeight()/9; // Taille case

        for(int i = 0 ; i <= 9 ; i++){
            if(i%3 == 0){
                p.setStrokeWidth(10);
            } else {
                p.setStrokeWidth(5);
            }

            c.drawLine(i*t, 0, i*t, 9*t, p);
            c.drawLine(0, i*t, 9*t, i*t, p);
        }


        for(int i = 0 ; i < 9 ; i++) {
            for(int j = 0 ; j < 9 ; j++) {
                int val = Sudoku.monSudoku[i][j];
                boolean ok;
                if(val != 0){
                    boolean dansLigne = Sudoku.isInRow(Sudoku.monSudoku, i, j, val);
                    boolean dansColonne = Sudoku.isInColumn(Sudoku.monSudoku, i, j, val);
                    boolean dansCarre = Sudoku.isInSquare(Sudoku.monSudoku, i, j, val);
                    ok = (!dansLigne && !dansColonne && !dansCarre);
                } else {
                    ok = true;
                }
                caseSudoku cs = new caseSudoku(i, j, val, ok);
                cs.Draw(c);
                cases[i][j] = cs;
            }
        }

        if(isWin()){
            Sudoku.winTV.setTextColor(Color.GREEN);
            Sudoku.winTV.setText("Bravo ! Vous avez résolu ce sudoku !");
        }

    }

    public static boolean isWin() {
        if(Sudoku.isNotEnd(Sudoku.monSudoku)){
            return false;
        } else {
            for(int i = 0 ; i < 9 ; i++) {
                for(int j = 0 ; j < 9 ; j++) {
                    if(!cases[i][j].isOk()){
                        return false;
                    }
                }
            }
        }
        return true;
    }



}
