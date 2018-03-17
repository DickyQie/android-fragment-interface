package com.zhangqie.fragmentinterface.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.zhangqie.fragmentinterface.R;

/**
 * Created by zhangqie on 2017/9/24.
 */

public class B extends BaseFragment {

    public static final  String INTERFCE=B.class.getName() +"B";

    private Button btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.a,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn=(Button)getView().findViewById(R.id.btn);
        btn.setText("无参数有返回");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(INTERFCE);
            }
        });
    }

    public void onButtonPressed(String data){
        try {
            String result=mFunctionManager.invokeFunction(data,String.class);
            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
