package com.zhangqie.fragmentinterface.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.zhangqie.fragmentinterface.MainActivity;
import com.zhangqie.fragmentinterface.struct.FunctionManager;

/**
 * Created by zhangqie on 2017/9/25.
 */

public class BaseFragment extends Fragment {


    protected FunctionManager mFunctionManager;
    private MainActivity mBaseActivity;

    public void setmFunctionManager(FunctionManager functionManager){
        this.mFunctionManager=functionManager;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            mBaseActivity=(MainActivity)context;
            mBaseActivity.setFunctionForFragment(getTag());
        }
    }


}
