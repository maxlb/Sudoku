package fr.epsi.maxime.sudoku;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

/**
 * Created by maxim on 25/04/2018.
 */

public class caseSudoku {
    private int i;
    private int j;
    private int val;
    private boolean canModif;
    private boolean ok;

    caseSudoku(int x, int y, int val, boolean ok){
        i = x;
        j = y;
        this.val = val;
        canModif = (Sudoku.sudokuInitial[i][j] == 0);
        this.ok = ok;
    }

    public boolean isOk() {
        return ok;
    }

    public boolean isCanModif() {
        return canModif;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void Draw(Canvas c){
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        if(canModif && ok){
            p.setColor(Color.GREEN);
        } else if(canModif) {
            p.setColor(Color.RED);
        } else {
            p.setColor(Color.BLACK);
        }

        p.setTextSize(65);

        int t = c.getHeight()/9; // Taille case

        if(val != 0) {
            String s = "" + val;
            c.drawText(s, (j * t) + 22, ((i+1) * t) - 16, p);
        }
    }
}
