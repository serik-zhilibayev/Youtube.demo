/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;
import csci152.adt.Queue;
import csci152.impl.Node;


/**
 *
 * @author Aikorkem Zhumabek Serik Zhilibayev
 */
public class LinkedListQueue<T> implements Queue<T>{
    private int size;
    private Node<T> front;
    private Node<T> back;
    
    public LinkedListQueue(){
        size = 0;
        size = 0;
        front = null;
        back = null;
    }
    @Override
    public void enqueue(T value) {
        Node<T> nw = new Node(value);
        if (size == 0){
            back = nw;
            front = nw;
            size++;
        }else{
            back.setLink(nw);
            back = nw;
            size++;
        }
    }

    @Override
    public T dequeue() throws Exception {
        if (size == 0){
            throw new Exception("Empty Queue!!!");
        }
            else if (size==1)
        {
            T k = front.getValue();
            front = null;
            back = null;
            size = 0;
            return k;
        }
        
        T k = front.getValue();
        front=front.getLink();
        size--;
        return k;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        front = null;
        back = null;
        size = 0;
    }
    
    public String toString(){
        String res = "";
        
        Node<T> bf = front;
        
        for (int i = 0; i < size;i++){
            
            res += bf.getValue()+"  ";
            bf = bf.getLink();   
        }
        
        
        return res;
    }
}
