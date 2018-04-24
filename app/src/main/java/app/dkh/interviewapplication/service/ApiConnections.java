package app.dkh.interviewapplication.service;

import app.dkh.interviewapplication.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ok on 4/23/2018.
 */

public class ApiConnections {
    private static Retrofit retrofit;

    public static FoodItemsService getRetrofit() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(FoodItemsService.class);
    }
}
