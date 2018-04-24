package app.dkh.interviewapplication.viewmodels;


import android.arch.lifecycle.LiveData;
import app.dkh.interviewapplication.models.FoodItemsListModel;
import app.dkh.interviewapplication.repositories.FoodItemRepository;
import io.realm.Realm;

public class MainActivityViewModel extends RealmViewModel {
    private Realm realm;
    private LiveData<FoodItemsListModel> foodArrayListLiveData;
    private FoodItemRepository foodItemRepository;

    public MainActivityViewModel() {
        super();
        realm = getRealm();
        foodItemRepository = new FoodItemRepository(realm);

        foodArrayListLiveData = foodItemRepository.getFoodItemsList();
    }

    public LiveData<FoodItemsListModel> getFoodArrayListLiveData() {
        return foodArrayListLiveData;
    }
}
