package jiewei.popularmoviesi;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import jiewei.popularmoviesi.data.MovieContract;
/**
 * Created by Wei on 1/14/2016.
 */
public class FavoriteView extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
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


    public static FavoriteView newInstance(int position, Uri uri) {
        FavoriteView fragment = new FavoriteView();
        Bundle args = new Bundle();
        fragment.mPosition = position;
        fragment.mUri = uri;
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
        View rootView = inflater.inflate(R.layout.detail_fragment, container, false);
        poster = (ImageView) rootView.findViewById(R.id.poster_image_view);
        backdrop= (ImageView) rootView.findViewById(R.id.backdrop_image_view);
        movie_title = (TextView)rootView.findViewById(R.id.movie_title_text);
        movie_release = (TextView) rootView.findViewById(R.id.movie_release_date_text);
        movie_rating = (TextView)rootView.findViewById(R.id.movie_rating_text);
        movie_overview = (TextView) rootView.findViewById(R.id.movie_overview_text);
        trailer = (ListView)rootView.findViewById(R.id.trailer_list_view);
        review = (ListView) rootView.findViewById(R.id.review_list_view);

        Bundle args = this.getArguments();
        getLoaderManager().initLoader(CURSOR_LOADER_ID, args, FavoriteView.this);

        return rootView;
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
            selection = MovieContract.FavoriteEntry._ID;
            selectionArgs = new String[]{String.valueOf(mPosition)};
        }
        return new CursorLoader(getActivity(),
                mUri,
                null,
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
        mDetailCursor = data;
        mDetailCursor.moveToFirst();
        DatabaseUtils.dumpCursor(data);
        poster.setImageResource(mDetailCursor.getInt(5));
        backdrop.setImageResource(mDetailCursor.getInt(4));
        movie_title.setText(mDetailCursor.getString(2));
        movie_release.setText(mDetailCursor.getString(8));
        movie_rating.setText(mDetailCursor.getString(7));
        movie_overview.setText(mDetailCursor.getString(6));

    }

    // reset CursorAdapter on Loader Reset
    @Override
    public void onLoaderReset(Loader<Cursor> loader){
        mDetailCursor = null;
    }

}

