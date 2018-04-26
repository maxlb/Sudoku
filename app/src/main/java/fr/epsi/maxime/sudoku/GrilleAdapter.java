package fr.epsi.maxime.sudoku;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by maxim on 26/04/2018.
 */

public class GrilleAdapter extends ArrayAdapter<DoGrille> {

    public GrilleAdapter(Context context, List<DoGrille> grilles) {
        super(context,0,grilles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row,parent, false);
        }

        GrilleViewHolder viewHolder = (GrilleViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new GrilleViewHolder();
            viewHolder.level = (TextView) convertView.findViewById(R.id.level);
            viewHolder.num = (TextView) convertView.findViewById(R.id.num);
            viewHolder.done = (TextView) convertView.findViewById(R.id.pourcent);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        DoGrille grille = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.level.setText("Niveau " + grille.getLevel());
        viewHolder.num.setText("Grille " + grille.getNum());
        viewHolder.done.setText(grille.getDone() + "%");
        if(grille.getDone() > 50){
            viewHolder.done.setTextColor(Color.GREEN);
        } else {
            viewHolder.done.setTextColor(Color.RED);
        }

        return convertView;
    }

}
