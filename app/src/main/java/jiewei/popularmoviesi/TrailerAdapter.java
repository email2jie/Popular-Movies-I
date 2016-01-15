package jiewei.popularmoviesi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
/**
 * Created by Wei on 1/14/2016.
 */
public class TrailerAdapter extends ArrayAdapter<Trailer> {
    private static final String LOG_TAG = TrailerAdapter.class.getSimpleName();

    public TrailerAdapter(Context context,  ArrayList<Trailer> objects) {
        super(context,0,objects);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        //get Movie object from the ArrayAdapter at the appropriate position
        Trailer current = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate (R.layout.trailer_item,parent,false);
        }
        TextView trailerName = (TextView)convertView.findViewById(R.id.trailer_name);
        trailerName.setText(current.name);



        return convertView;
    }
}