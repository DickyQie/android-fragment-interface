package com.zhangqie.fragmentinterface.struct;

import android.text.TextUtils;

import com.zhangqie.fragmentinterface.function.FunctionException;

import java.util.HashMap;

/**
 * Created by zhangqie on 2017/9/24.
 */

public class FunctionManager {

    private static FunctionManager _instance;
    private FunctionManager(){
        mFuncationNoCNoResult =new HashMap<>();
        mFunctionWithParamNoResult =new HashMap<>();
        mFunctionNoParamWithResult =new HashMap<>();
        mFunctionWithParamResultn =new HashMap<>();
    }
    public static FunctionManager getInstance(){
        if (_instance == null){
            _instance=new FunctionManager();
        }
        return _instance;
    }


    private HashMap<String,FunctionNoParamNotResult> mFuncationNoCNoResult;
    private HashMap<String,FunctionWithParamNoResult> mFunctionWithParamNoResult ;
    private HashMap<String,FunctionNoParamWithResult> mFunctionNoParamWithResult;
    private HashMap<String,FunctionWithParamResultn> mFunctionWithParamResultn;


    public FunctionManager addFunction(FunctionNoParamNotResult function){
        mFuncationNoCNoResult.put(function.mFunctionName,function);
        return this;
    }

    /***
     * 无参无返回值
     * @param funName
     */
    public void invokeFunction(String funName){
        if (TextUtils.isEmpty(funName)){
            return;
        }
        FunctionNoParamNotResult functionNoParamNotResult = null;
        if (mFuncationNoCNoResult !=null){
            functionNoParamNotResult=mFuncationNoCNoResult.get(funName);
            if (functionNoParamNotResult != null) {
                functionNoParamNotResult.function();
            }
        }
    }

    /***
     * 无参数有返回值
     * @param functionWithParamResult
     * @return
     */
    public FunctionManager addFunction(FunctionNoParamWithResult functionWithParamResult ){
        mFunctionNoParamWithResult.put(functionWithParamResult.mFunctionName,functionWithParamResult);
        return this;
    }

    public <Result> Result invokeFunction(String funName,Class<Result> c){
        if (TextUtils.isEmpty(funName)){
            return null;
        }
        FunctionNoParamWithResult functionWithParamResult = null;
        if (mFunctionNoParamWithResult !=null){
            functionWithParamResult = mFunctionNoParamWithResult.get(funName);
            if (functionWithParamResult != null) {
                if (c != null){
                    return c.cast(functionWithParamResult.function());
                }else {
                    return (Result) functionWithParamResult.function();
                }
            }else {
                try {
                    throw new FunctionException("没有此函数"+funName);
                }catch (FunctionException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /***
     * 有参数无返回值
     * @param functionNowithParamResult
     * @return
     */
    public FunctionManager addFunction(FunctionWithParamNoResult functionNowithParamResult ){
        mFunctionWithParamNoResult.put(functionNowithParamResult.mFunctionName,functionNowithParamResult);
        return this;
    }

    public <Param> void invokeFunction(String funcName,Param param) throws FunctionException{
        if (TextUtils.isEmpty(funcName)){
            return;
        }
        FunctionWithParamNoResult functionWithParamNoResult = null;
        if (mFunctionWithParamNoResult != null){
            functionWithParamNoResult = mFunctionWithParamNoResult.get(funcName);
            if (functionWithParamNoResult != null){
                functionWithParamNoResult.function(param);
            }else {
                try {
                    throw new FunctionException("没有此函数"+funcName);
                }catch (FunctionException e){
                    e.printStackTrace();
                }
            }
        }
    }


    /***
     * 有参数you返回值
     * @param functionWithParamResultn
     * @return
     */
    public FunctionManager addFunction(FunctionWithParamResultn functionWithParamResultn ){
        mFunctionWithParamResultn.put(functionWithParamResultn.mFunctionName,functionWithParamResultn);
        return this;
    }

    public <Result,Param> Result invokeFunction(String funcName,Param param, Class<Result> c) throws FunctionException{
        if (TextUtils.isEmpty(funcName)){
            return null;
        }
        FunctionWithParamResultn functionWithParamResultn = null;
        if (mFunctionWithParamResultn != null){
            functionWithParamResultn = mFunctionWithParamResultn.get(funcName);
            if (functionWithParamResultn != null){
                if (c != null){
                    return  c.cast(functionWithParamResultn.function(param));
                }else {
                    return (Result) functionWithParamResultn.function(param);
                }
            }else {
                try {
                    throw new FunctionException("没有此函数"+funcName);
                }catch (FunctionException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }





}
