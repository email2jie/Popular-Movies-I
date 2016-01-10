package jiewei.popularmoviesi;

/**
 * Created by Wei on 1/9/2016.
 */
public class MovieObject {

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
