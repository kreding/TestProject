package com.jxj.algorithm;
/**
 * ���������
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
	 * ��ȡ�ƶ��Ľڵ�ֵ
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
	 * ����µĽڵ������Ѵ��ڽڵ�
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
	 * ��С�ڵ�
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
	 * ��С�ڵ���key�����ֵ
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
		// �ڵ�key��Ŀ��key����ӽڵ���������
		if(cmp < 0) {
			return floor(node.left, key);
		}
		
		// �ڵ�key��Ŀ��ڵ�С����ӽڵ���������
		Node t = floor(node.right, key);
		
		// ע�⣺����ڽڵ����������Ҳ�����Ŀ��ڵ���ͬkey�Ľڵ�ʱ����������ҵ��ڵ�
		if(t != null) {
			return t;
		}else {
			return node;
		}
	}
	
	/**
	 * ����ڵ���key����Сֵ
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
	 * ��������Ϊk��ֵ
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
	 * ����key������
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
	 * ɾ����Сֵ
	 */
	public void deleteMin() {
		// �ݹ�Ӹ��ڵ㵽��С�ڵ�ĸ��ڵ㣬��������Nֵ����󷵻ص��Ǹ���Nֵ��ԭʼ�����
		// ע���˴����صĲ��Ǳ�ɾ�����Ľڵ㣬���Ǳ�ɾ���ڵ㸸�ڵ�
		root = deleteMin(root);
	}
	
	// �ݹ�Ӹ���㿪ʼ�������������Ľڵ㣬֪��������Ϊ��
	private Node deleteMin(Node node) {
		if(node.left == null) {
			return node.right;
		}
		
		node.left = deleteMin(node.left);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	/**
	 * ɾ���ƶ�key�Ľڵ�
	 * 
	 * ԭ��
	 * ����ɾ���ڵ��������������Ϊ��ʱ����������������С�ڵ��滻����λ�ã�
	 * �½ڵ��������Ϊ��ɾ���ڵ����������½ڵ�������Ϊ��ɾ���ڵ��������
	 * ɾ����С�ڵ���γ��µ�������
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
