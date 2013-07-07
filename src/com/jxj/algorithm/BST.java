package com.jxj.algorithm;

public class BST<Key extends Comparable<Key>, Value> {

	private Node root;
	
	private class Node {
		private Key key;
		private Value val;
		private Node left;
		private Node right;
		private int N;
		
		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node node) {
		if(node == null) {
			return 0;
		}
		
		return node.N;
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Value get(Node node, Key key) {
		if(node == null) {
			return null;
		}
		
		int cmp = key.compareTo(node.key);
		if(cmp > 0) {
			return get(node.right, key);
		}else if(cmp < 0) {
			return get(node.left, key);
		}else {
			return node.val;
		}
		
	}
	
	public void put(Key key, Value val) {
		put(root, key, val);
	}
	
	private Node put(Node node, Key key, Value val) {
		if(node == null) {
			return new Node(key, val, 1);
		}
		
		int cmp = key.compareTo(node.key);
		if(cmp > 0) {
			put(node.right, key, val);
		}else if(cmp < 0) {
			put(node.left, key, val);
		}else {
			node.val = val;
		}
		
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public Key min() {
		return min(root).key;
	}
	
	private Node min(Node node) {
		if(node == null) {
			return node;
		}
		
		return node.left;
	}
	
	public Key max() {
		return max(root).key;
	}
	
	private Node max(Node node) {
		if(node == null) {
			return node;
		}
		
		return node.right;
	}
	/**
	 * 求小于等于key的最大值
	 * @param key
	 * @return
	 */
	public Key floor(Key key) {
		Node node = floor(root, key);
		if(node == null) {
			return null;
		}
		
		return node.key;
	}
	
	private Node floor(Node node, Key key) {
		if(node == null) {
			return null;
		}
		
		int cmp = key.compareTo(node.key);
		if(cmp == 0) {
			return node;
		}
		// 节点key比目标key大，则从节点左子树找
		if(cmp < 0) {
			return floor(node.left, key);
		}
		
		// 节点key比目标节点小，则从节点右子树找
		Node t = floor(node.right, key);
		
		// 注意：如果在节点右子树中找不到跟目标节点相同key的节点时，返回最后找到节点
		if(t != null) {
			return t;
		}else {
			return node;
		}
	}
	
	/**
	 * 求大于等于key的最小值
	 * @param key
	 * @return
	 */
	public Key ceiling(Key key) {
		Node node = ceiling(root, key);
		
		if(node == null) {
			return null;
		}
		
		return node.key;
	}

	private Node ceiling(Node node, Key key) {
		if(node == null) {
			return null;
		}
		
		int cmp = key.compareTo(node.key);
		if(cmp == 0) {
			return node;
		}
		
		if(cmp > 0) {
			return ceiling(node.right, key);
		}
		
		Node t = ceiling(node.left, key);
		
		if(t == null) {
			return node;
		}else {
			return t;
		}
	}
}
