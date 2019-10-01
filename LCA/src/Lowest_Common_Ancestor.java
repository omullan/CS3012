
public class Lowest_Common_Ancestor {
	
	class Node { 
	    int data; 
	    Node left, right; 
	  
	    public Node(int item) { 
	        data = item; 
	        left = right = null; 
	    } 
	} 
	  
	public class BinaryTree { 
	    Node root; 
	  
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
	}
}
