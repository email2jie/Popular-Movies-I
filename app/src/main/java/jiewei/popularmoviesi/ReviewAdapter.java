package jiewei.popularmoviesi;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.security.PublicKey;
import java.util.ArrayList;
/**
 * Created by Wei on 1/14/2016.
 */
public class ReviewAdapter extends ArrayAdapter<Review>{
    private static final String LOG_TAG = ReviewAdapter.class.getSimpleName();

    public ReviewAdapter(Context context, ArrayList<Review> objects) {
        super(context,0,objects);
    }
    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        //get Movie object from the ArrayAdapter at the appropriate position
        Review current = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate (R.layout.review_item,parent,false);
        }
        TextView reviewContent = (TextView)convertView.findViewById(R.id.review_content);
        reviewContent.setText(current.content);

        return convertView;
    }
}