package fr.epsi.maxime.sudoku;

/**
 * Created by maxime on 25/04/2018.
 */

public class DoGrille {
    int level;
    int num;
    int done;

    public int getLevel() {
        return level;
    }

    public int getNum() {
        return num;
    }

    public int getDone() {
        return done;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDone(int done) {
        this.done = done;
    }

    DoGrille(int l, int n){
        level = l;
        num = n;
        done = (int)(Math.random()*101);

    }

}
