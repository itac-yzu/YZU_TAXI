package itac.yzu_taxi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ChengYuShao on 15/7/5.
 */
public class pagerFragment extends Fragment {

    public static Fragment create(int pageNumber){
        pagerFragment fragment = new pagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pagerNumber", pageNumber);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_pagerfragment,container,false);
        TextView textView = (TextView) view.findViewById(R.id.pager_tv_number);
        Bundle bundle = getArguments();
        if(bundle != null){
            textView.setText(Integer.valueOf(bundle.getInt("pagerName") + 1).toString());
            textView.setTextColor(getResources().getColor(R.color.black));
        }
        else {
            textView.setVisibility(View.GONE);
        }
        return view;
    }
}
