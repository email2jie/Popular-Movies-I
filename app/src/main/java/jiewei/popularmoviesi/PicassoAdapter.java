package jiewei.popularmoviesi;

import android.app.Activity;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Wei on 1/8/2016.
 */
public class PicassoAdapter extends ArrayAdapter {

    public PicassoAdapter(Activity context, List<Image> objects){
        super(context,0,objects);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(getContext());
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.with(getContext())
                .load("https://cms-assets.tutsplus.com/uploads/users/21/posts/19431/featured_image/CodeFeature.jpg")
                .into(imageView);

        return imageView;
    }
}
