package app.dkh.interviewapplication.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import app.dkh.interviewapplication.R;
import app.dkh.interviewapplication.adapters.FoodListRecyclerViewAdapter;
import app.dkh.interviewapplication.models.FoodItem;
import app.dkh.interviewapplication.models.FoodItemsListModel;
import app.dkh.interviewapplication.viewmodels.MainActivityViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MenuListFragment extends Fragment implements FoodListRecyclerViewAdapter.FoodListAdapterListener {

    private final String TAG = MenuListFragment.class.getSimpleName();

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.rcv_menu_list)
    RecyclerView menuRecyclerView;

    @BindView(R.id.error)
    LinearLayout mErrorView;

    private MainActivityViewModel _viewModel;

    public MenuListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menu_list, container, false);
        ButterKnife.bind(this, view);
        retrieveFoodList();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        menuRecyclerView.setLayoutManager(layoutManager);
    }

    private void retrieveFoodList() {
        _viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        LiveData<FoodItemsListModel> data = _viewModel.getFoodArrayListLiveData();
        data.observe(this , foodItems -> {
            Log.e(TAG, foodItems.toString());
            displayFoodItems(foodItems);
        });
    }

    private void displayFoodItems(FoodItemsListModel foodItems) {
        if(foodItems != null && foodItems.getItems() != null){
            menuRecyclerView.setVisibility(View.VISIBLE);
            mErrorView.setVisibility(View.GONE);
            FoodListRecyclerViewAdapter adapter = new FoodListRecyclerViewAdapter(getActivity(), foodItems.getItems(), this);
            menuRecyclerView.setAdapter(adapter);

        }else{
            menuRecyclerView.setVisibility(View.GONE);
            mErrorView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFoodClicked(FoodItem foodItem) {

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(final int index);
    }
}
