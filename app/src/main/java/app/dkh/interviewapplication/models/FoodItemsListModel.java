package app.dkh.interviewapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoodItemsListModel {

    @SerializedName("items")
    private ArrayList<FoodItem> items = null;

    public ArrayList<FoodItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<FoodItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "FoodItemsListModel{" +
                "items=" + items +
                '}';
    }
}
