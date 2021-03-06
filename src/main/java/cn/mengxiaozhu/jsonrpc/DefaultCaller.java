package cn.mengxiaozhu.jsonrpc;

import cn.mengxiaozhu.jsonrpc.exceptions.NoSuchRPCMethodException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class DefaultCaller implements Caller {
    private FunctionFactory functionFactory;
    private Gson gson;

    public DefaultCaller(FunctionFactory functionFactory, Gson gson) {
        this.functionFactory = functionFactory;
        this.gson = gson;
    }

    @Override
    public Object call(String name, JsonArray params) throws Exception {
        Function function = functionFactory.get(name);
        if (function == null) {
            throw new NoSuchRPCMethodException(name);
        }
        return function.invoke(gson, params);
    }
}
