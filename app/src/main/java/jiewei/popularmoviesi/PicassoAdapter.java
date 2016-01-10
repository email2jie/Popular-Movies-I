package jiewei.popularmoviesi;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 1/8/2016.
 */
public class PicassoAdapter extends ArrayAdapter<MovieObject> {
    private static final String LOG_TAG = PicassoAdapter.class.getSimpleName();

    public PicassoAdapter(Context context, ArrayList<MovieObject> objects){
        super(context,0,objects);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        //get Movie object from the ArrayAdapter at the appropriate position
        MovieObject current = getItem(position);
        String thumbUrl = current.poster;

        ImageView imageView;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movies, parent, false);
        }
        imageView = (ImageView) convertView;
        imageView.setAdjustViewBounds(true);

        Picasso.with(getContext())
                .load(thumbUrl)
                .fit()
                .into(imageView);



        return imageView;
    }
}
