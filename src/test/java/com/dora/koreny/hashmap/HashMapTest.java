package com.dora.koreny.hashmap;

import com.dora.koreny.exceptions.KeyAlreadyExistsException;
import com.dora.koreny.exceptions.KeyNotFound;
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
        assertTrue(hashMap.bucketContainsKey(bucket, "Key"));
    }

    @Test
    public void testPutIfKeyAlreadyExistsThrowException() {
        hashMap.put("Key", 1);
        assertThrows(KeyAlreadyExistsException.class, () -> hashMap.put("Key", 2));
    }

    @Test
    public void testPutIfBucketNotEmptyAndKeyDoesNotExist() {
        int bucketIndex = hashMap.getBucketIndex("FB");
        hashMap.put("FB", 1);
        hashMap.put("Ea", 2);
        assertTrue(hashMap.getBucketArray()[bucketIndex].size() == 2);
    }

    @Test
    public void getValueByKeySuccess() {
        hashMap.put("Key", 1);
        assertTrue(hashMap.getValue("Key").equals(1));
    }

    @Test
    public void getValueByKeyFailsBucketIsEmpty() {
        hashMap.put("Key", 1);
        assertThrows(NullPointerException.class, () -> hashMap.getValue("Key2"));
    }

    @Test
    public void getValueByKeyFailsKeyNotFoundInBucket() {
        hashMap.put("FB", 1);
        assertThrows(KeyNotFound.class, () -> hashMap.getValue("Ea"));
    }
}