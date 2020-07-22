package com.kylin.ds.model;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    class Node {
    	
        int key;
        int val;
        
        Node prev;
        Node next;
        
        Node() {
        	
        }
        
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    private Map<Integer, Node> cache = new HashMap<>();
    
    private int size;
    
    private int capacity;
    
    private Node head, tail;
    
    public LRUCache(int capacity) {
    	this.size = 0;
    	this.capacity = capacity;
    	
    	head = new Node();
    	tail = new Node();
    	
    	head.next = tail;
    	tail.prev = head;
    }
    
    void moveToHead(Node node) {
    	removeNode(node);
    	addToHead(node);
    }
    
    void addToHead(Node node) {
    	node.next = head.next;
    	head.next = node;
    	node.next.prev = node;
    	node.prev = head;
    }
    
    void removeNode(Node node) {
    	node.prev.next = node.next;
    	node.next.prev = node.prev;
    	node.next = null;
    	node.prev = null;
    }
    
    Node popTail() {
    	Node lastNode = tail.prev;
    	removeNode(lastNode);
    	return lastNode;
    }
    
    public void put(int key, int val) {
    	
    	Node node = cache.get(key);
    	
    	if (node == null) {
    		Node newNode = new Node(key, val);
    		cache.put(key, newNode);
    		addToHead(newNode);
    		
    		size++;
    		if (size > capacity) {
    			Node tail = popTail();
    			cache.remove(tail.key);
    		}
    	}
    	else {
    		node.val = val;
    		moveToHead(node);
    	}
    	
    }
    
    public int get(int key) {
    	
    	if (!cache.containsKey(key)) {
    		return -1;
    	}
    	
    	Node node = cache.get(key);
    	
    	moveToHead(node);
    	return node.val;
    }
    
}