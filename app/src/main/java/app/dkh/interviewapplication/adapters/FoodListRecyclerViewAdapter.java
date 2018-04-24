package app.dkh.interviewapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.dkh.interviewapplication.R;
import app.dkh.interviewapplication.models.FoodItem;
import app.dkh.interviewapplication.models.FoodItemsListModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodListRecyclerViewAdapter extends RecyclerView.Adapter<FoodListRecyclerViewAdapter.FoodListViewHolder> {


    private final Context mContext;
    private final ArrayList<FoodItem> mFoodArrayList;
    private final FoodListAdapterListener mFoodListAdapterListener;

    public FoodListRecyclerViewAdapter(Context context, ArrayList<FoodItem> foodArrayList, FoodListAdapterListener foodListAdapterListener) {
        this.mContext = context;
        this.mFoodArrayList = foodArrayList;
        this.mFoodListAdapterListener = foodListAdapterListener;
    }
    @Override
    public FoodListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menu_item, parent, false);
        return new FoodListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(FoodListViewHolder holder, int position) {
        final FoodItem foodItem = mFoodArrayList.get(position);
        final String foodThumbnailURL = foodItem.getPhotoUrl();
        if(foodThumbnailURL != null && !foodThumbnailURL.isEmpty()){
            Picasso.get()
                    .load(foodThumbnailURL)
                    .resize(300, 300)
                    .placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .into(holder.img_thumbnail);
        }

        holder.txtName.setText(foodItem.getName());
    }
    @Override
    public int getItemCount() {
        return mFoodArrayList.size();
    }
    public class FoodListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.img_menu)
        ImageView img_thumbnail;
        @BindView(R.id.tv_name)
        TextView txtName;

        public FoodListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            FoodItem foodItem = mFoodArrayList.get(getAdapterPosition());
            mFoodListAdapterListener.onFoodClicked(foodItem);
        }
    }
    public interface FoodListAdapterListener{
        void onFoodClicked(FoodItem foodItem);
    }
}
