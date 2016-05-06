/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package csci152.impl;
import csci152.adt.HashTableMap;
import csci152.adt.Map;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Администратор
 */

public class LLQHashTableMap <K extends Comparable, V> implements HashTableMap<K, V>{
    private LinkedListQueue<KeyValuePair<K, V>>[] buckets;
    private int size;
    
    public LLQHashTableMap(int val){
        size = 0;
        buckets = (LinkedListQueue<KeyValuePair<K, V>>[]) new LinkedListQueue[val];
    }
    
    @Override
    public void define(K key, V value) {
        int k = Math.abs(key.hashCode())%buckets.length;
        
        if (buckets[k] == null){
            buckets[k] = new LinkedListQueue(); 
        }
        
        KeyValuePair<K, V> result = null;
        
        for (int i = 0; i < buckets[k].getSize(); i ++){
            try {
                result = buckets[k].dequeue();
                buckets[k].enqueue(result);
            } catch (Exception ex) {System.out.println(ex.getMessage());}
            if (result.getKey().equals(key)){
                result.setValue(value);
                return;
            }
        }
        buckets[k].enqueue(new KeyValuePair(key, value));
        size++;
    }

    @Override
    public V getValue(K key) {
        int k = Math.abs(key.hashCode())%buckets.length;
        
        if (buckets[k] == null){
            buckets[k] = new LinkedListQueue(); 
        }   
        KeyValuePair<K, V> result = null;
        
        int size = buckets[k].getSize();
        for (int i = 0; i < size; i ++){
            try {
                result = buckets[k].dequeue();
                buckets[k].enqueue(result);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            if (result.getKey().equals(key)){
                return result.getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int k = Math.abs(key.hashCode())%buckets.length;
        
        if (buckets[k] == null){
            buckets[k] = new LinkedListQueue(); 
        }   
        KeyValuePair<K, V> result = null;
        
        int sizus = buckets[k].getSize();
       
        for (int i = 0; i < sizus; i ++){
            try {
                result = buckets[k].dequeue();
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            if (result.getKey().equals(key)){
               size--;
               return result.getValue();
            }
            buckets[k].enqueue(result);
        }
        return null;
    }

    @Override
    public KeyValuePair<K, V> removeAny() throws Exception {
        if (size == 0){
            throw new Exception("Empty!!!");
        }
        
        int i;
        
        for (i = 0; i < buckets.length; i++){
            if (buckets[i] != null && buckets[i].getSize() > 0){
                break;
            }
        }
        size--;
        return buckets[i].dequeue();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        int i;
        for (i = 0; i < buckets.length; i++){
            if (buckets[i] != null){
                buckets[i].clear();
            }
        }
        size = 0;
    }

    public String toString(){

         String res = "[";
         for (int i = 0; i < buckets.length; i++){
             if (buckets[i] != null){
                 for(int j = 0; j < buckets[i].getSize(); j++){
                     KeyValuePair<K, V> x = null;
                     try {
                         x = buckets[i].dequeue();
                     } catch (Exception ex) {}
                     buckets[i].enqueue(x);
                     res += x.toString() + "\n";
                 }
                 
             }
         }
         return res+"]";

     }

    @Override
    public int getNumberOfBuckets() {
        return buckets.length;
    }

    @Override
    public int getBucketSize(int index) throws Exception {
        if (index > this.getNumberOfBuckets()){
            throw new Exception("Out of bounds!!!");
        }else if (buckets[index] == null){
            return 0;
        }
        return buckets[index].getSize();
    }

    @Override
    public double getLoadFactor() {
        return (double)size/(buckets.length*1.0);
    }

    @Override
    public double getBucketSizeStandardDev() {
        double mean = 0;
        int n = this.getNumberOfBuckets();
        for (int i = 0; i < n; i ++){
            try {
                mean += getBucketSize(i);
            } catch (Exception ex) {}
        }
        
        mean = mean / n;
        
        double d = 0;
        for (int i = 0; i < n; i ++){
            try {
                d += Math.pow(getBucketSize(i) - mean,2);
            } catch (Exception ex) {}
        }
        return Math.sqrt(d/n);
    }

    @Override
    public String bucketsToString() {
        String res = "";
        for (int i = 0; i < buckets.length; i++){
            if (buckets[i] != null){
                res += buckets[i] + "\n";
            }
        }
        return res;
    }
    
}
