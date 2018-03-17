package com.zhangqie.fragmentinterface;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zhangqie.fragmentinterface.fragment.A;
import com.zhangqie.fragmentinterface.fragment.B;
import com.zhangqie.fragmentinterface.fragment.BaseFragment;
import com.zhangqie.fragmentinterface.fragment.C;
import com.zhangqie.fragmentinterface.fragment.D;
import com.zhangqie.fragmentinterface.struct.FunctionManager;
import com.zhangqie.fragmentinterface.struct.FunctionNoParamNotResult;
import com.zhangqie.fragmentinterface.struct.FunctionNoParamWithResult;
import com.zhangqie.fragmentinterface.struct.FunctionWithParamNoResult;
import com.zhangqie.fragmentinterface.struct.FunctionWithParamResultn;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private Fragment mCurrentFragment;
    private BottomNavigationBar mBottomNavigationBar;
    BadgeItem badgeItem;

    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mFragmentManager = getSupportFragmentManager();
        initFragemnt();
        showFragment(0);
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor(android.R.color.white);
        badgeItem = new BadgeItem()
                .setBackgroundColor(Color.RED).setText("99")
                .setHideOnSelect(true); //设置被选中时隐藏角标
        mBottomNavigationBar
                .setActiveColor(R.color.colorAccent) //设置选中的颜色
                .setInActiveColor(R.color.colorPrimary);//未选中

        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "首页"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "店铺"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "购物车"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "我的").setBadgeItem(badgeItem))
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){

            @Override
            public void onTabSelected(int position) {
                showFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });


    }

    private void showFragment(int page) {
        FragmentTransaction mFragmentTransaction = mFragmentManager
                .beginTransaction();
        if (mCurrentFragment != null) {
            mFragmentTransaction.hide(mCurrentFragment);
        }
        mCurrentFragment = fragmentArrayList.get(page);
        if (mCurrentFragment.isAdded())
        {
            mFragmentTransaction.show(mCurrentFragment);
        }else {
            mFragmentTransaction.add(R.id.fragmenta, mCurrentFragment,mCurrentFragment.getClass().getName());
        }
        mFragmentTransaction.commitAllowingStateLoss();
    }

    private void initFragemnt(){
        fragmentArrayList.add(new A());
        fragmentArrayList.add(new B());
        fragmentArrayList.add(new C());
        fragmentArrayList.add(new D());
    }

    public void setFunctionForFragment(final String tag){
        BaseFragment fragment=(BaseFragment)mFragmentManager.findFragmentByTag(tag);
        FunctionManager functionManager=FunctionManager.getInstance();
        fragment.setmFunctionManager(functionManager.addFunction(new FunctionNoParamNotResult(A.INTERFCE) {
            @Override
            public void function() {
                Toast.makeText(MainActivity.this,"无参无返回值"+tag,Toast.LENGTH_LONG).show();
            }
        }).addFunction(new FunctionNoParamWithResult<String>(B.INTERFCE) {
            @Override
            public String function() {
                Toast.makeText(MainActivity.this,"无参有返回值",Toast.LENGTH_LONG).show();
                return "张三";
            }
        }).addFunction(new FunctionWithParamNoResult<Integer>(C.INTERFCE) {
            @Override
            public void function(Integer o) {
                Toast.makeText(MainActivity.this,"有参无返回值"+o,Toast.LENGTH_LONG).show();
            }
        }).addFunction(new FunctionWithParamResultn<String>(D.INTERFCE) {
            @Override
            public String function(String o) {
                Toast.makeText(MainActivity.this,"有参有返回值"+o,Toast.LENGTH_LONG).show();
                return "zhangqie";
            }
        }));
    }

}


