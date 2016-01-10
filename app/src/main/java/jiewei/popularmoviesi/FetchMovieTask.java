package jiewei.popularmoviesi;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Wei on 1/9/2016.
 */
public class FetchMovieTask extends AsyncTask<String, Void, ArrayList<MovieObject>> {
    private static final String LOG_TAG = FetchMovieTask.class.getSimpleName();
    private PicassoAdapter mPicassoAdapter;

    public FetchMovieTask(PicassoAdapter mPicassoAdapter) {
        this.mPicassoAdapter = mPicassoAdapter;
    }

    @Override
    protected ArrayList<MovieObject> doInBackground(String... params) {

        if (params.length == 0) {
            //nothing to look up
            return null;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        //Will content the raw JSON response as a string.
        String movieJsonStr = null;

        //Sorting format passed in from setting activity,
        String sortFormat = null;
        sortFormat = params[0];


        try {
            //building bloacks of  URL to to be built
            final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
            final String SORT_PARAM = "sort_by";
            final String API_PARAM = "api_key";

            //build URI
            Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                    .appendQueryParameter(SORT_PARAM, sortFormat)
                    .appendQueryParameter(API_PARAM, BuildConfig.THEMOVIEDB_API_KEY)
                    .build();

            URL url = new URL(builtUri.toString());

            Log.d(LOG_TAG, "URL: " + builtUri.toString());
            //Create the request to Moviedb, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            //Reading input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                //nothing to do
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                //empty stream. No parsing
                return null;
            }
            movieJsonStr = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            return getMovieDataFromJson(movieJsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<MovieObject> getMovieDataFromJson(String MovieJsonStr) throws JSONException {
        final String OWM_RESULTS = "results";
        // Values we are going to fetch from JSON.
        final String OWM_ID = "id";
        final String OWM_OVERVIEW = "overview";
        final String OWM_RELEASE_DATE = "release_date";
        final String OWM_POSTER_PATH = "poster_path";
        final String OWM_BACKDROP_PATH = "backdrop_path";
        final String OWM_TITLE = "original_title";
        final String OWM_RATING = "vote_average";


        String posterBaseUrl = "http://image.tmdb.org/t/p/w185/";
        String backdropBaseUrl = "http://image.tmdb.org/t/p/w342/";

        JSONObject moviewJson = new JSONObject(MovieJsonStr);
        JSONArray movieArray = moviewJson.getJSONArray(OWM_RESULTS);

        //Loop JSON Array and fetch JSON movie objects
        ArrayList<MovieObject> resultMovies = new ArrayList<MovieObject>();
        for (int i = 0; i < movieArray.length(); i++) {
            //set strings default to null
            String id = null;
            String overview = null;
            String release_date = null;
            String poster = null;
            String back_drop = null;
            String title = null;
            String rating = null;
            //Get JSON object representing the Movie.
            JSONObject currentMovie = movieArray.getJSONObject(i);

            //get needed data from JSON text
            id = currentMovie.getString(OWM_ID);
            overview = currentMovie.getString(OWM_OVERVIEW);
            release_date = currentMovie.getString(OWM_RELEASE_DATE);
            poster = posterBaseUrl + currentMovie.getString(OWM_POSTER_PATH);
            back_drop = backdropBaseUrl + currentMovie.getString(OWM_BACKDROP_PATH);
            title = currentMovie.getString(OWM_TITLE);
            rating = currentMovie.getString(OWM_RATING);

            // Make a movie object and add to movie list
            MovieObject movie = new MovieObject(id, title, poster, back_drop,overview, release_date, rating);
            resultMovies.add(movie);
            Log.i(LOG_TAG, movie.toString());
        }
        return resultMovies;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieObject> result) {
        if (result != null) {
            mPicassoAdapter.clear();

            for (MovieObject item : result) {
                mPicassoAdapter.add(item);
            }


        }
    }
}