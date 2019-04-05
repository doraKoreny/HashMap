package com.dora.koreny.hashmap;

import java.util.LinkedList;

public class HashMap<K, V> {
    private int bucketSize = 16;
    private LinkedList<KeyValue<K, V>>[] elements = new LinkedList[bucketSize];

}
