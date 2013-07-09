package com.jxj.algorithm;
/**
 * 二叉查找树
 * @author kreding
 *
 * @param <Key>
 * @param <Value>
 */
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
	
	/**
	 * 获取制定的节点值
	 * @param key
	 * @return
	 */
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
	
	/**
	 * 添加新的节点或更新已存在节点
	 * @param key
	 * @param val
	 */
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
	
	/**
	 * 最小节点
	 * @return
	 */
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
	
	/**
	 * 查找排名为k的值
	 * @param k
	 * @return
	 */
	public Key select(int k) {
		if(k > size()) return null;
		
		return select(root, k).key;
	}
	
	private Node select(Node node, int k) {
		if(node == null) {
			return null;
		}
		
		int t = size(node);
		if(t > k) {
			return select(node.left, k);
		}else if(t < k) {
			return select(node.right, k -t -1);
		}else {
			return node;
		}
	}
	
	/**
	 * 查找key的排名
	 * @param key
	 * @return
	 */
	public int rank(Key key) {
		return rank(root, key);
	}
	
	private int rank(Node node, Key key) {
		if(node == null) {
			return 0;
		}
		
		int cmp = key.compareTo(node.key);
		if(cmp > 0) {
			return 1 + size(node.left) + rank(node.right, key);
		}else if(cmp < 0) {
			return rank(node.left, key);
		}else {
			return size(node);
		}
	}
	
	/**
	 * 删除最小值
	 */
	public void deleteMin() {
		// 递归从跟节点到最小节点的父节点，并更新其N值，最后返回的是更新N值后原始根结点
		// 注：此处返回的不是被删除掉的节点，而是被删除节点父节点
		root = deleteMin(root);
	}
	
	// 递归从根结点开始的左右左子树的节点，知道左子树为空
	private Node deleteMin(Node node) {
		if(node.left == null) {
			return node.right;
		}
		
		node.left = deleteMin(node.left);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	/**
	 * 删除制定key的节点
	 * 
	 * 原理：
	 * 当被删除节点的左右子树都不为空时，用其右子树的最小节点替换它的位置，
	 * 新节点的左子树为被删除节点左子树，新节点右子树为被删除节点的右子树
	 * 删除最小节点后形成新的右子树
	 * @param key
	 */
	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node node, Key key) {
		if(node == null) {
			return null;
		}
		
		int cmp = key.compareTo(node.key);
		if(cmp > 0) {
			node.right = delete(node.right, key);
		}else if(cmp < 0) {
			node.left = delete(node.left, key);
		}else {
			if(node.left == null ) {
				return node.right;
			}
			if(node.right == null) {
				return node.left;
			}
			
			Node d = node;
			node = min(node.right);
			node.right = deleteMin(d.right);
			node.left = d.left;
		}
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
}
