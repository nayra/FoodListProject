package app.dkh.interviewapplication.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;

import app.dkh.interviewapplication.MyApplication;
import app.dkh.interviewapplication.models.FoodItem;
import app.dkh.interviewapplication.models.FoodItemsListModel;
import app.dkh.interviewapplication.service.ApiConnections;
import app.dkh.interviewapplication.util.ConnectivityCheck;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodItemRepository {

    private Realm _realm;

    private static final String TAG = FoodItemRepository.class.getSimpleName();

    public FoodItemRepository(Realm realm) {
        _realm = realm;
    }

    public LiveData<FoodItemsListModel> getFoodItemsList(){
        final MutableLiveData<FoodItemsListModel> data = new MutableLiveData<>();

        if (ConnectivityCheck.isConnected(MyApplication.getContext())) {            // if connection exists connect to api and save the result
            ApiConnections.getRetrofit().getFoodItemsMenu().enqueue(new Callback<FoodItemsListModel>() {
                @Override
                public void onResponse(Call<FoodItemsListModel> call, Response<FoodItemsListModel> response) {
                    if (response != null) {
                        Log.d(TAG, "response");
                        data.setValue(response.body());
                        _realm.executeTransactionAsync(_realm -> {
                            _realm.copyToRealmOrUpdate(response.body().getItems());
                        });
                    } else {
                        // get data from data base if nothing returns from api
                        Log.d(TAG, "null response");
                        data.setValue(retrieveDataFromDB());
                    }
                }

                @Override
                public void onFailure(Call<FoodItemsListModel> call, Throwable t) {
                    Log.e(TAG, t.toString());
                    // get data from data base if failure happened
                    data.setValue(retrieveDataFromDB());
                }
            });
        }else{
            // get data from data base if no connection

            Log.e(TAG, "No internet connectivity");
            data.setValue(retrieveDataFromDB());
        }
        return data;
    }

    private FoodItemsListModel retrieveDataFromDB(){
        final RealmResults<FoodItem> realmResults = _realm.where(FoodItem.class).findAll();
        final ArrayList<FoodItem> nonLiveDataArrayList = new ArrayList<>(_realm.copyFromRealm(realmResults));
        final FoodItemsListModel model = new FoodItemsListModel();
        model.setItems(nonLiveDataArrayList);
        return model;
    }
}
