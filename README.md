 # The secret behind HashMap 
 
The purpose of this project is to learn the internal working of HashMap data structure. HashMap can store key-value pairs. 
I have been using it quite often, but I didn't really know what is going on under the hood so I wanted to figure it out for myself.

## How does HashMap work?

A HashMap stores its data in a primitive array of Linked Lists, so called buckets. When we add an element to the HashMap
first of all it has to calculate the index of the bucket, where the key-value pair is going to be stored. To do so, first the key is being hashed with a hash function. 
But how can we manage that? 
Java has a built-in function for it. HashCode() is a function of Object, so all non-primitive types can easily be converted to an integer. Using this we can calculate the bucketIndex between 0 and the length of our array ith the following code: 

**```obj.hashCode() % array.length ```**

So now we have the index of a bucket, where we can store our element.
But there is an issue: multiple strings can hash to the same value (this phenomenon called *hash collision*). 
To work around the hash collision the base array's elements are Linked Lists (or sometimes Binary Search Trees), and the elements are added here. 
  
 Retrieving works the reverse way: First we calculate the hash of the key whose value we want to get to find the position in the base array.
Then we loop trough the Linked List there to find the value we are looking for.

![HashMapInternalStructure](https://user-images.githubusercontent.com/39567974/55667217-b267ee00-5859-11e9-873b-6036a2f39aa7.png)

image src: https://javaconceptoftheday.com/wp-content/uploads/2016/02/HashMapInternalStructure.png

## Requirements
- create a HashMap data structure
- it should be initialized with the size of 16, don't need resize it when it gets too big
- it should have the following functions: 
    - put(key, value)
    - getValue(key)
    - remove(key)
    - clearAll()
- make it generic
- write JUnit tests


## Built with
- Maven - Dependency Management

## Programming Language 
- Java 8

## JUnit tests
- JUnit 5 
    



