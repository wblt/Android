package com.example.mac.communicationfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by mac on 17/2/18.
 */

public class Fragment1 extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1,null);

        Button bt_button = (Button) view.findViewById(R.id.bt_button);
        bt_button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_button:

                Fragment2 f2 = (Fragment2) getActivity().getFragmentManager().findFragmentByTag("f2");

                f2.setText("ddddd");

                break;
            default:
                break;
        }
    }
}
