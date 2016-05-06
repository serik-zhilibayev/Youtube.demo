/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.adt;

/**
 *
 * @author Администратор
 */
public interface HashTableStats {
    /**
     * @return the number of buckets in the hash table
     */
    public int getNumberOfBuckets();
    
    /**
     * @param index of the bucket
     * @return the number of items in the bucket at the given index
     * @throws Exception if the index is not a proper 
     *         bucket index for the hash table
     */
    public int getBucketSize(int index) throws Exception;
        
    /**
     * @return the load factor for the hash table 
     */
    public double getLoadFactor();
    
    /**
     * @return the standard deviation of the bucket sizes
     */
    public double getBucketSizeStandardDev();
    
    /**
     * @return the contents of the hash table, separated into the 
     * individual buckets
     */
    public String bucketsToString();
}
