package app.dkh.interviewapplication.service;

import java.util.ArrayList;

import app.dkh.interviewapplication.models.FoodItem;
import app.dkh.interviewapplication.models.FoodItemsListModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ok on 4/23/2018.
 */

public interface FoodItemsService {
    @GET("kvdzh/")
    Call<FoodItemsListModel> getFoodItemsMenu();
}
