package com.dora.koreny.hashmap;

import com.dora.koreny.exceptions.KeyAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    private HashMap<String, Integer> hashMap;

    @BeforeEach
    public void createHashMap() {
        hashMap = new HashMap();
    }

    @Test
    public void getValidBucketPosition() {
        int lowestPosition = 0;
        int bucketArraySize = hashMap.getBucketArraySize();
        int position = hashMap.getBucketIndex("Key");
        assertTrue(position >= lowestPosition || position <= bucketArraySize);
    }

    @Test
    public void testPutIfKeyDoesNotExists() {
        int position = hashMap.getBucketIndex("Key");
        hashMap.put("Key", 1);
        LinkedList bucket = hashMap.getBucketArray()[position];
        assertTrue(hashMap.bucketContainsKeyValue(bucket, "Key"));
    }

    @Test
    public void testPutIfKeyAlreadyExistsThrowException() {
        hashMap.put("Key", 1);
        assertThrows(KeyAlreadyExistsException.class, () -> hashMap.put("Key", 2));
    }
}