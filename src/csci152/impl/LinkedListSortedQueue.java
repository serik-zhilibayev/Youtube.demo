/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.SortedQueue;
import csci152.impl.Node;

/**
 *
 * @author Serik Zhilibayev
 */
public class LinkedListSortedQueue<T extends Comparable> implements SortedQueue<T>{
    
    private Node<T> front;
    private int size;
    
    public LinkedListSortedQueue(){
       front = null;
       size = 0;
    }
    
    @Override
    public void insert(T value) {
        Node<T> nw = new Node(value);
        if (size == 0){
            front = nw;
        }else {
            if(value.compareTo(front.getValue()) <= 0){    
                nw.setLink(front);
                front = nw;
            }else{
                Node<T> curr = front;
                for(int i = 0; i < size-1; i++){
                    if (value.compareTo(curr.getLink().getValue()) < 0){
                        nw.setLink(curr.getLink());
                        curr.setLink(nw);
                        size++;
                        return;
                    }else if (value.compareTo(curr.getLink().getValue()) == 0){
                        return;
                    }
                    curr = curr.getLink();
                }
                curr.setLink(nw);   
            }
        }
        size++;
    }

    @Override
    public T dequeue() throws Exception {
        if (size == 0){
            throw new Exception("Queue is empty");
        }
        if (size == 1){
            T k = front.getValue();
            front = null;
            size = 0;
            return k;
        }
        T res = front.getValue();
        front = front.getLink();
        size--;
        return res;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        front = null;
        size = 0;
    }
    
    public String toString(){
        String res = "";
        Node<T> k = front;
        for (int i = 0; i < size; i ++){
            res += k.getValue() + "\n";
            k = k.getLink();
        }
        
        return res;
    }
    
    
    
}
