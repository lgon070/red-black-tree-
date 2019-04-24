package rbtree;

public class TestGrounds {
//Testing class
	public static void main(String[] args) {
//		Integer ar = new Integer[](53,90,89,62,75,38,70,36,52,32);
		
		
		RedBlackTree<Integer> rb = new RedBlackTree<Integer>();
		rb.insert(53);
		rb.insert(90);
		rb.insert(89);
		rb.insert(62);
		rb.insert(75);
		rb.insert(38);
		rb.insert(70);
		rb.insert(36);
		rb.insert(52);
		rb.insert(32);
		
		rb.delete(62);
		rb.delete(53);
		rb.delete(52);
		
		rb.printTree();
	}

}
