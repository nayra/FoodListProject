package app.dkh.interviewapplication.views;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.dkh.interviewapplication.Constants;
import app.dkh.interviewapplication.R;
import app.dkh.interviewapplication.models.FoodItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private FoodItem selectedItem = null;

    @BindView(R.id.img_main)
    ImageView imageView;

    @BindView(R.id.tv_description)
    TextView txtDescription;

    @Override
    public void setArguments(@Nullable Bundle bundle) {
        super.setArguments(bundle);
        if(bundle != null){
            selectedItem = bundle.getParcelable(Constants.SELECTED_ITEM);
        }
    }

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        displayFoodItemDetails();
        return view;

    }

    private void displayFoodItemDetails() {
        final String foodThumbnailURL = selectedItem.getPhotoUrl();
        if(foodThumbnailURL != null && !foodThumbnailURL.isEmpty()){
            Picasso.get()
                    .load(foodThumbnailURL)
                    .placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .into(imageView);
        }

        txtDescription.setText(selectedItem.getDescription());
    }

}
