package com.consult.keyvalue;

import com.consult.utils.HttpUtils;
import com.consult.utils.JsonUtils;

import java.util.List;

public class KeyValueService {

    private static String ENDPOINT_KV = "/v1/kv/";

    public Value get(String key) {
        return get(key, null, false);
    }

    public Value get(String key, String dataCentre, boolean recurse) {
        try {
            String url = ENDPOINT_KV + key;
            String response = HttpUtils.get(url);
            return (Value)JsonUtils.fromJson(response, Value.class);
        }catch(Exception e) {
            return null;
        }
    }

    public List<Value> list() {
        list(null);
    }

    public List<Value> list(String seperator) {

    }

    /*
     * Return value is simply either true or false. If the CAS check fails, then false will be returned.
     */
    public boolean put(String key, String value) {
        put(key, value, null, null);
    }

    public boolean put(String key, String value, Integer flags, Integer checkAndSetIndex) {

    }

    public void delete(String key) {

    }

    public void deleteWithPrefix(String prefix) {

    }

}
