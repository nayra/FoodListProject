package app.dkh.interviewapplication.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.dkh.interviewapplication.util.FragmentUtils;
import app.dkh.interviewapplication.R;
public class MainActivity extends AppCompatActivity implements MenuListFragment.OnFragmentInteractionListener {



    // TODO: Implement the views. You decide whether you want to use fragments, activities or
    // something else for displaying the content of the app

    // TODO: Feel free to restructure the classes (add more folders, classes, move classes)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentUtils.addFragment(R.id.frag_container, this , new MenuListFragment(), MenuListFragment.class.getSimpleName());
    }

    @Override
    public void onFragmentInteraction(int index) {

    }
}
