package com.zhangqie.fragmentinterface.struct;

/**
 * Created by zhangqie on 2017/9/24.
 *
 *有参数无返回值
 *
 */

public abstract class FunctionWithParamNoResult<Result> extends Function {
    public FunctionWithParamNoResult(String mFunctionName) {
        super(mFunctionName);
    }

    public abstract void function(Result result);
}
