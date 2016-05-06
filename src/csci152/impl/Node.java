package csci152.impl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aikorkem Zhumabek Serik Zhilibayev
 */
import csci152.adt.Queue;

public class Node<T> {
    
    private T value;
    private Node<T> link;
    
    public Node(T val){
        value = val;
    }

    public T getValue(){
        return value;
    }
    
    public void setValue(T vl){
        value = vl;
    }
    
    public Node<T> getLink() {
        return link;
    }
    
    public void setLink(Node<T> link){
        this.link = link;
    }
    
    
    public String toString(){
        return value.toString();
    }
}
