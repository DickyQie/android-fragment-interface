package com.zhangqie.fragmentinterface.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zhangqie.fragmentinterface.R;
import com.zhangqie.fragmentinterface.function.FunctionException;

/**
 * Created by zhangqie on 2017/9/24.
 */

public class C extends BaseFragment {


    public static final  String INTERFCE=C.class.getName() +"C";

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
        btn.setText("有参数无返回");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed();
            }
        });
    }

    public void onButtonPressed(){
        try {
            mFunctionManager.invokeFunction(INTERFCE,123);
        } catch (FunctionException e) {
            e.printStackTrace();
        }
    }


}
