package jiewei.popularmoviesi;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import jiewei.popularmoviesi.data.MovieContract;


/**
 * Created by Wei on 1/14/2016.
 */
public class FavoriteAdapter extends CursorAdapter {
    private static final String LOG_TAG = FavoriteAdapter.class.getSimpleName();
    private Context mContext;
    private static int sLoaderID;

    public static class ViewHolder {
        public final ImageView favoriteGrid;

        public ViewHolder (View view){
            favoriteGrid = (ImageView)view.findViewById(R.id.favorite_image_item);
        }
    }
    public FavoriteAdapter(Context context, Cursor c, int flags, int loaderID){
        super(context,c,flags);
        Log.d(LOG_TAG, "FavoriteAdaper");
        mContext=context;
        sLoaderID=loaderID;
    }
    @Override
    public View newView (Context context,Cursor cursor,ViewGroup parent){
        int layoutId = R.layout.favorite_image_item;

        Log.d (LOG_TAG,  "in new view");

        View view = LayoutInflater.from(context).inflate(layoutId,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }
    @Override
    public void bindView (View view,Context context,Cursor cursor){
        ViewHolder viewHolder = (ViewHolder)view.getTag();
        Log.d (LOG_TAG, "in bind view");
        int thumbIndex = cursor.getColumnIndex(MovieContract.FavoriteEntry.COLUMN_MOVIE_POSTER);
        int thumbImage = cursor.getInt(thumbIndex);
        viewHolder.favoriteGrid.setImageResource(thumbImage);
    }
}
