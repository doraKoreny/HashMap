package com.dora.koreny.hashmap;

import com.dora.koreny.exceptions.KeyAlreadyExistsException;
import com.dora.koreny.exceptions.KeyNotFound;

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
            if (bucketContainsKey(bucket, key)) {
                throw new KeyAlreadyExistsException();
            } else {
                bucket.add(new KeyValue(key, value));
                bucketArray[position] = bucket;
            }
        }
    }

    public int getHash(K key) {
        return key.hashCode();
    }

    public int getBucketIndex(K key) {
        return Math.abs(getHash(key) % bucketArraySize);
    }

    public boolean bucketContainsKey(LinkedList<KeyValue<K,V>> bucket, K key) {
        for (KeyValue keyValue : bucket) {
            if (keyValue.getKey() == key) {
                return true;
            }
        }
        return false;
    }

    public V getValue(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<KeyValue<K, V>> bucket = bucketArray[bucketIndex];
        if (bucket != null) {
            for (KeyValue<K, V> keyValue : bucket) {
                if (keyValue.getKey() == key) {
                    return keyValue.getValue();
                }
            }
            throw new KeyNotFound();
        } else {
            throw new NullPointerException("Key not found, bucket is empty");
        }
    }

    public void remove(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<KeyValue<K,V>> bucket = bucketArray[bucketIndex];
        if (bucket != null) {
            for (KeyValue<K, V> keyValue : bucket) {
                if (keyValue.getKey() == key) {
                    bucket.remove(keyValue);
                }
            }
        } else {
            throw new NullPointerException("Key not found, bucket is empty!");
        }
    }

}
