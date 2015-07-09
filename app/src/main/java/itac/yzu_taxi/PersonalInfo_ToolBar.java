package itac.yzu_taxi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ChengYuShao on 15/6/1.
 */
public class PersonalInfo_ToolBar extends Fragment {

    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_personal_toolbar,container,false);
        toolbar = (Toolbar) v.findViewById(R.id.personalInfo_toolbar);
        toolbar.setTitle("個人資料");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        //((ActionBarActivity) this.getActivity()).setSupportActionBar(toolbar);
        return v;
    }
}
