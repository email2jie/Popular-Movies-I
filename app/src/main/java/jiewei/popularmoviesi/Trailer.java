package jiewei.popularmoviesi;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by Wei on 1/14/2016.
 */
public class Trailer implements Parcelable {
    public static final String PARCEL_TAG = "trailer_tag";

    public String name;
    public String key;

    public Trailer(){

    }

    public Trailer (String name, String key){
        this.name= name;
        this.key = key;
    }

    private Trailer (Parcel in) {
        name = in.readString();
        key = in.readString();
    }
    @Override
    public int describeContents(){
        return  0;
    }
    @Override
    public void writeToParcel (Parcel parcel, int i){
        parcel.writeString(name);
        parcel.writeString(key);
    }
    public static final Parcelable.Creator<Trailer> CREATOR = new Parcelable.Creator<Trailer>(){
        @Override
        public  Trailer createFromParcel(Parcel parcel) {
            return new Trailer(parcel);
        }
        @Override
        public Trailer[] newArray(int i) {
            return new Trailer[i];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}