import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryTreeTest {

	@Test
	void test1() {
		BinaryTree tree = new BinaryTree();
		tree.insert(2);
		tree.insert(6);
		tree.insert(1);
		tree.insert(3);
		tree.insert(4);
		tree.insert(7);
		assertEquals(2, tree.findLCA(1, 7).data);
		assertEquals(6 ,tree.findLCA(3, 7).data);
	}
	
	@Test
	void test2() {
		BinaryTree tree = new BinaryTree();
		tree.insert(4);
		tree.insert(7);
		tree.insert(1);
		tree.insert(3);
		tree.insert(2);
		tree.insert(5);
		assertEquals(4, tree.findLCA(7, 1).data);
		assertEquals(3, tree.findLCA(3, 2).data);
	}
	
	@Test
	void test3() {
		BinaryTree tree= new BinaryTree();
		assertEquals(null, tree.findLCA(2, 3));
	}
}
