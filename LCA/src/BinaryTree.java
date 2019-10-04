
public class BinaryTree {

	class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}

	Node root;

	public BinaryTree() {

	}

	Node findLCA(int n, int j) {
		return findLCA(root, n, j);
	}

	Node findLCA(Node node, int n, int j) {

		if (node == null) {
			return null;
		}
		if (node.data == n || node.data == j) {
			return node;
		}

		Node left = findLCA(node.left, n, j);
		Node right = findLCA(node.right, n, j);

		if (left != null && right != null)
			return node;

		if (left != null) {
			return left;
		} else {
			return right;
		}
	}

	void insert(int key) {
		root = insert(root, key);
	}

	Node insert(Node root, int key) {
		if (root == null) {
			root = new Node(key);
			return root;
		}

		if (key < root.data) {
			root.left = insert(root.left, key);
		} else if (key > root.data) {
			root.right = insert(root.right, key);
		}

		return root;
	}
}
