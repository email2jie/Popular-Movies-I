package jiewei.popularmoviesi;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by Wei on 1/14/2016.
 */
public class Review implements Parcelable {
    public static final String PARCEL_TAG = "review_tag";

    public String content;

    public Review() {

    }
    public Review (String content){
        this.content = content;
    }

    private Review(Parcel in) {
        content = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(content);
    }

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>(){
        @Override
        public  Review createFromParcel(Parcel parcel) {
            return new Review(parcel);
        }
        @Override
        public Review[] newArray(int i) {
            return new Review[i];
        }
    };
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}