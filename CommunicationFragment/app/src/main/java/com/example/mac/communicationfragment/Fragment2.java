package com.example.mac.communicationfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mac on 17/2/18.
 */

public class Fragment2 extends Fragment {

    private TextView tv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2,null);

        tv = (TextView) view.findViewById(R.id.tv);

        return view;
    }

    public void setText(String text) {
        tv.setText(text);
    }
}
