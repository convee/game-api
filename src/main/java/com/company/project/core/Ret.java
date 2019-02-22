package com.company.project.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一API响应结果封装
 */
public class Ret {

    private Map<String, Object> result;

    public Ret() {
        this.result = new HashMap<>();
        this.result.put("code", ResultCode.SUCCESS.code);
    }

    public Ret setOption(String key, Object value) {
        this.result.put(key, value);
        return this;
    }

    public Ret setResult(Map<String, Object> result) {
        this.result = result;
        return this;
    }

    public Map<String, Object> getResult() {
        return this.result;
    }


}
