package jiewei.popularmoviesi;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import jiewei.popularmoviesi.data.MovieContract;
/**
 * Created by Wei on 1/14/2016.
 */
public class FavoriteFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String LOG_TAG = FavoriteFragment.class.getSimpleName();

    private FavoriteAdapter mFavoriteAdapter;
    private GridView mGridView;

    private static final int CURSOR_LOADER_ID = 0;


    public FavoriteFragment(){

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        Cursor c =
                getActivity().getContentResolver().query(MovieContract.FavoriteEntry.CONTENT_URI,
                        new String[]{MovieContract.FavoriteEntry._ID},
                        null,
                        null,
                        null);

        // initialize loader
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate favorite_grid layout
        View favoriteView = inflater.inflate(R.layout.favorite_grid, container, false);


        // initialize our FavoriteAdapter
        mFavoriteAdapter = new FavoriteAdapter(getActivity(), null, 0, CURSOR_LOADER_ID);
        // initialize mGridView to the GridView in favorite_grid.xml
        mGridView = (GridView) favoriteView.findViewById(R.id.favorite_grid_view);
        // set mGridView adapter to our CursorAdapter
        mGridView.setAdapter(mFavoriteAdapter);


        //make each item clickable
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position, long id){

                //increment the position to match database ids indexed starting at 1
                int uriId = position +1;
                String movId = MovieContract.FavoriteEntry.COLUMN_MOVIE_ID;
                Log.v(LOG_TAG, "movie id = " + movId);
                        //append Id to uri
                        Uri uri = ContentUris.withAppendedId(MovieContract.FavoriteEntry.CONTENT_URI, uriId);
                //create fragment
                FavoriteView detailFragment = FavoriteView.newInstance(uriId, uri);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, detailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return favoriteView;
    }

    // Attach loader to favorite database query
    // run when loader is initialized
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args){
        return new CursorLoader(getActivity(),
                MovieContract.FavoriteEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

    // Set the cursor in our CursorAdapter once the Cursor is loaded
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mFavoriteAdapter.swapCursor(data);


    }

    // reset CursorAdapter on Loader Reset
    @Override
    public void onLoaderReset(Loader<Cursor> loader){
        mFavoriteAdapter.swapCursor(null);
    }
}