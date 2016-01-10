package jiewei.popularmoviesi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wei on 1/9/2016.
 */
public class MovieObject implements Parcelable {

    public static final String PARCEL_TAG = "movie_tag";
    public String id;
    public String title;
    public String poster;
    public String back_drop;
    public String overview;
    public String release_date;
    public String rating;


    public MovieObject(){

    }

    public MovieObject(String id, String title, String poster, String back_drop, String overview, String release_date, String rating) {

        this.id = id;
        this.title = title;
        this.poster = poster;
        this.back_drop = back_drop;
        this.overview = overview;
        this.release_date = release_date;
        this.rating = rating;
    }
    //generated getters and setters
    private MovieObject (Parcel in){
        id = in.readString();
        title = in.readString();
        poster = in.readString();
        back_drop = in.readString();
        overview = in.readString();
        release_date =in.readString();
        rating = in.readString();

    }
    @Override
    public int describeContents(){
        return  0;
    }
    @Override
    public void writeToParcel (Parcel parcel, int i){
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(poster);
        parcel.writeString(back_drop);
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeString(rating);

    }
    public static final Parcelable.Creator<MovieObject> CREATOR = new Parcelable.Creator<MovieObject>(){
        @Override
        public  MovieObject createFromParcel(Parcel parcel) {
            return new MovieObject(parcel);
        }
        @Override
        public MovieObject[] newArray(int i) {
            return new MovieObject[i];
        }
    };
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBack_drop() {
        return back_drop;
    }

    public void setBack_drop(String back_drop) {
        this.back_drop = back_drop;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
