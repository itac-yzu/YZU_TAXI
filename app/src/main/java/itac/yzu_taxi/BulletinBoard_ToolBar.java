package itac.yzu_taxi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ChengYuShao on 15/6/8.
 */
public class BulletinBoard_ToolBar extends Fragment {

    private Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_bulletinboard_toolbar,container,false);
        toolbar = (Toolbar) v.findViewById(R.id.bulletinBoard_toolbar);
        toolbar.setTitle("揪團區");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        return v;
    }
}
