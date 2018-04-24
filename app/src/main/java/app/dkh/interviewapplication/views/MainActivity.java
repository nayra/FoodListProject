package app.dkh.interviewapplication.views;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import app.dkh.interviewapplication.Constants;
import app.dkh.interviewapplication.R;
import app.dkh.interviewapplication.models.FoodItem;
import app.dkh.interviewapplication.util.FragmentUtils;

public class MainActivity extends AppCompatActivity implements MenuListFragment.OnFragmentInteractionListener {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentUtils.addFragment(R.id.frag_container, this , new MenuListFragment(), MenuListFragment.class.getSimpleName());
    }

    // navigate to details fragment after clicking on recyclerview item
    // send the selected item
    @Override
    public void onFragmentInteraction(FoodItem foodItem) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.SELECTED_ITEM, foodItem);
        detailsFragment.setArguments(bundle);

        FragmentUtils.replaceFragment(R.id.frag_container, this , detailsFragment, DetailsFragment.class.getSimpleName());
    }

    // handle back from fragments
    @Override
    public void onBackPressed() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final int backStack = fragmentManager.getBackStackEntryCount();

        if (backStack > 1) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }

}
