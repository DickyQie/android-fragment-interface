package com.zhangqie.fragmentinterface.struct;

/**
 * Created by zhangqie on 2017/9/24.
 *
 * 有参数有返回值
 *
 */

public abstract class FunctionWithParamResultn<Result> extends Function {
    public FunctionWithParamResultn(String mFunctionName) {
        super(mFunctionName);
    }

    public abstract Result function(Result result);
}
