package com.zhangqie.fragmentinterface.struct;

/**
 * Created by zhangqie on 2017/9/24.
 *
 * 无参数又返回值
 *
 */

public abstract class FunctionNoParamWithResult<Result> extends Function {
    public FunctionNoParamWithResult(String mFunctionName) {
        super(mFunctionName);
    }

    public abstract Result function();
}
