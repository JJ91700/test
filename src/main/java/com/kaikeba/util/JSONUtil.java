package com.kaikeba.util;

import com.google.gson.Gson;

public class JSONUtil {
    private static Gson g;
    public static  String toJSON(Object obj) {
        return g.toJson(g);
    }
}
