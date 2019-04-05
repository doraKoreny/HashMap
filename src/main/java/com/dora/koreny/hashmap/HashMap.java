package com.dora.koreny.hashmap;

import com.dora.koreny.exceptions.KeyAlreadyExistsException;

import java.util.LinkedList;

public class HashMap<K, V> {

    private int bucketArraySize = 16;
    private LinkedList<KeyValue<K, V>>[] bucketArray = new LinkedList[bucketArraySize];

    public int getBucketArraySize() {
        return bucketArraySize;
    }

    public LinkedList<KeyValue<K, V>>[] getBucketArray() {
        return bucketArray;
    }

    public void put(K key, V value) {
        int position = getBucketIndex(key);
        LinkedList bucket = bucketArray[position];
        if (bucket == null) {
            bucket = new LinkedList<KeyValue<K, V>>();
            bucket.add(new KeyValue(key, value));
            bucketArray[position] = bucket;
        } else {
            if (bucketContainsKeyValue(bucket, key)) {
                throw new KeyAlreadyExistsException();
            }
        }
    }

    public int getBucketIndex(K key) {
        return Math.abs(key.hashCode() % bucketArraySize);
    }

    public boolean bucketContainsKeyValue(LinkedList<KeyValue<K,V>> bucket, K key) {
        for (KeyValue keyValue : bucket) {
            if (keyValue.getKey() == key) {
                return true;
            }
        }
        return false;
    }

}
