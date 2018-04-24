package app.dkh.interviewapplication.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import app.dkh.interviewapplication.Constants;
import app.dkh.interviewapplication.models.FoodItem;
import app.dkh.interviewapplication.models.FoodItemsListModel;
import app.dkh.interviewapplication.util.FragmentUtils;
import app.dkh.interviewapplication.R;
import app.dkh.interviewapplication.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements MenuListFragment.OnFragmentInteractionListener {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentUtils.addFragment(R.id.frag_container, this , new MenuListFragment(), MenuListFragment.class.getSimpleName());
    }

    @Override
    public void onFragmentInteraction(FoodItem foodItem) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.SELECTED_ITEM, foodItem);
        detailsFragment.setArguments(bundle);

        FragmentUtils.replaceFragment(R.id.frag_container, this , detailsFragment, DetailsFragment.class.getSimpleName());
    }
}
