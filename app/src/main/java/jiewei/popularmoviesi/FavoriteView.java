package jiewei.popularmoviesi;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import jiewei.popularmoviesi.data.MovieContract;
import jiewei.popularmoviesi.data.MovieProvider;

/**
 * Created by Wei on 1/14/2016.
 */
public class FavoriteView extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String LOG_TAG = FavoriteView.class.getSimpleName();
    private Cursor mDetailCursor;
    private ImageView poster;
    private ImageView backdrop;
    private TextView movie_title;
    private TextView movie_release;
    private TextView movie_rating;
    private TextView movie_overview;
    private ListView trailer;
    private ListView review;
    private int mPosition;

    private Uri mUri;
    private static final int CURSOR_LOADER_ID = 0;
    private static final String[] FAVORITE_COLUMNS ={
            MovieContract.FavoriteEntry.TABLE_NAME + "." + MovieContract.FavoriteEntry._ID,
            MovieContract.FavoriteEntry.COLUMN_MOVIE_TITLE,
            MovieContract.FavoriteEntry.COLUMN_MOVIE_POSTER,
            MovieContract.FavoriteEntry.COLUMN_MOVIE_BACK_DROP,
            MovieContract.FavoriteEntry.COLUMN_MOVIE_RELEASE_DATE,
            MovieContract.FavoriteEntry.COLUMN_MOVIE_RATING,
            MovieContract.FavoriteEntry.COLUMN_MOVIE_OVERVIEW,
    };

    private static final int COL_MOVIE_ID = 0;
    private static final int COL_MOVIE_TITLE = 1;
    private static final int COL_MOVIE_POSTER = 2;
    private static final int COL_MOVIE_BACKDROP = 3;
    private static final int COL_MOVIE_RELEASE = 4;
    private static final int COL_MOVIE_RATING = 5;
    private static final int COL_MOVIE_OVERVIEW = 6;

    public static FavoriteView newInstance(int position, Uri uri) {
        FavoriteView fragment = new FavoriteView();
        Bundle args = new Bundle();
        fragment.mPosition = position;
       Log.v(LOG_TAG, "mPosition = " + position);
        fragment.mUri = uri;
        Log.v(LOG_TAG, "mUri = " + uri);
        args.putInt("id", position);
        fragment.setArguments(args);
        return fragment;
    }

    public FavoriteView() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View favoriteView = inflater.inflate(R.layout.detail_fragment, container, false);
        poster = (ImageView) favoriteView.findViewById(R.id.poster_image_view);
        backdrop= (ImageView) favoriteView.findViewById(R.id.backdrop_image_view);
        movie_title = (TextView)favoriteView.findViewById(R.id.movie_title_text);
        movie_release = (TextView) favoriteView.findViewById(R.id.movie_release_date_text);
        movie_rating = (TextView)favoriteView.findViewById(R.id.movie_rating_text);
        movie_overview = (TextView) favoriteView.findViewById(R.id.movie_overview_text);
        trailer = (ListView)favoriteView.findViewById(R.id.trailer_list_view);
        review = (ListView) favoriteView.findViewById(R.id.review_list_view);

        Bundle args = this.getArguments();
        getLoaderManager().initLoader(CURSOR_LOADER_ID, args, FavoriteView.this);

        return favoriteView;
    }


    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args){
        String selection = null;
        String [] selectionArgs = null;
        if (args != null){
            selection = MovieContract.FavoriteEntry.COLUMN_MOVIE_ID;
            selectionArgs = new String[]{String.valueOf(mPosition)};
        }
        return new CursorLoader(getActivity(),
                mUri,
                FAVORITE_COLUMNS,
                selection,
                selectionArgs,
                null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

    // Set the cursor in our CursorAdapter once the Cursor is loaded
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.v(LOG_TAG, "in onLoadFinished");
        mDetailCursor = data;
        if (!mDetailCursor.moveToFirst()) { return; }

          if  (mDetailCursor.moveToFirst()) {
              Log.v(LOG_TAG, "in moveToFirst");
              DatabaseUtils.dumpCursor(data);
              poster.setImageResource(R.drawable.movie_placeholder);
              backdrop.setImageResource(R.drawable.movie_placeholder);
              movie_title.setText(mDetailCursor.getString(COL_MOVIE_TITLE));
              movie_release.setText(mDetailCursor.getString(COL_MOVIE_RELEASE));
              movie_rating.setText(mDetailCursor.getString(COL_MOVIE_RATING));
              movie_overview.setText(mDetailCursor.getString(COL_MOVIE_OVERVIEW));
          }

    }

    // reset CursorAdapter on Loader Reset
    @Override
    public void onLoaderReset(Loader<Cursor> loader){
        mDetailCursor = null;
    }


}

