package com.example.lovestory.util.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IteraterTest {
    public static void main(String[] args) {
        List<Map<String, Object>> dataMap = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "1.2929384");
        map.put("lcy", "1.2929384");
        map.put("mc", "1.2929384");
        map.put("mcgood", "1.2929384");
        dataMap.add(map);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("jjzz", "1.2929384");
        map2.put("PKID", "1.2929384");
        map2.put("mc", "1.2929384");
        map2.put("mcgood", "1.2929384");
        dataMap.add(map);
        if (dataMap.size() > 0) {
            //大小写都支持
            String lower = "";
            String upper = "";
            List<String> keys = new ArrayList<>();
            for (Map<String, Object> stringObjectMap : dataMap) {
                Set<String> strings = stringObjectMap.keySet();
                keys.addAll(strings);
                for (String s : keys) {
                    Object result = stringObjectMap.get(s);
                    lower = s.toLowerCase();
                    upper = s.toUpperCase();
                    stringObjectMap.put(lower, result);
                    stringObjectMap.put(upper, result);
                }
                keys.clear();
            }
        }
        System.out.println(dataMap);

    }
}
