package com.dora.koreny;

import com.dora.koreny.hashmap.HashMap;

public class App {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

        System.out.println(hashMap.getBucketIndex("Macska"));
        hashMap.put("Kutya", 1);
        hashMap.put("Macska", 1);

        System.out.println(hashMap);
    }
}
