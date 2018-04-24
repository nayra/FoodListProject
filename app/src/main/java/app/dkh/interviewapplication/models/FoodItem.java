package app.dkh.interviewapplication.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FoodItem extends RealmObject {
    @PrimaryKey
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("photoUrl")
    private String photoUrl;
    @SerializedName("description")
    private String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
