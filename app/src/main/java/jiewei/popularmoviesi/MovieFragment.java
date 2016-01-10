package jiewei.popularmoviesi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieFragment extends Fragment {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private GridView mGridView;
    private PicassoAdapter mMovieAdapter;
    //ArrayList of movies
    public ArrayList<MovieObject> movies = new ArrayList<>();

    //Keys for Intent to detail activity
    public static final String MOVIE_TITLE = "MOVIE_TITLE";
    public static final String MOVIE_POSTER = "MOVIE_POSTER";
    public static final String MOVIE_BACKDROP = "MOVIE_BACKDROP";
    public static final String MOVIE_RELEASE = "MOVIE_RELEASE";
    public static final String MOVIE_RATING = "MOVIE_RATING";
    public static final String MOVIE_OVERVIEW = "MOVIE_OVERVIEW";



    public MovieFragment(){
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(false);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.moviefragment, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_refresh) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //creates a rootView with the GridView defined in movie_fragment.xml
        // and use the ViewGroup container settings, not attach to root
        View rootView = inflater.inflate(R.layout.movie_fragment, container, false);

        mGridView = (GridView) rootView.findViewById(R.id.gridview_movies);
        mMovieAdapter = new PicassoAdapter(getActivity(), movies);
        //attach movie adapter to gridview
        mGridView.setAdapter(mMovieAdapter);


        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailIntent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra(MOVIE_TITLE, movies.get(position).title)
                        .putExtra(MOVIE_POSTER, movies.get(position).poster)
                        .putExtra(MOVIE_BACKDROP, movies.get(position).back_drop)
                        .putExtra(MOVIE_RELEASE, movies.get(position).release_date)
                        .putExtra(MOVIE_RATING, movies.get(position).rating)
                        .putExtra(MOVIE_OVERVIEW, movies.get(position).overview);
                startActivity(detailIntent);
            }
        });

        return rootView;

    }

    public void updateMovies() {
        FetchMovieTask fetchMovieTask = new FetchMovieTask(mMovieAdapter);
        //set default preference to sort by popularity
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortPref = prefs.getString(
                getString(R.string.pref_sorting_key),
                getString(R.string.pref_sorting_default));
        fetchMovieTask.execute(sortPref);
    }

    public void onStart() {
        super.onStart();
        //do when app starts
        updateMovies();
    }



}
