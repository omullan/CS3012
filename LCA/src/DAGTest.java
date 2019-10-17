import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class DAGTest {

	@Test
	void testValidateVertex() {
		DAG dag = new DAG(5);
		boolean result = dag.validateVertex(6);
		assertEquals(false, result);
		result = dag.validateVertex(-1);
		assertEquals(false, result);
	}
	
	@Test 
	void testAddEdge() {
		DAG dag = new DAG(5);
		boolean result = dag.addEdge(0, 1);
		assertEquals(true, result);
		result = dag.addEdge(9, 3);
		assertEquals(false, result);
	}
	
	@Test()
	void testExecptions() {
		assertThrows(Exception.class, () -> {DAG dag = new DAG(-10);});
	}
	
	@Test
	void testLCA() {
		DAG dag = new DAG(6);
		boolean e = dag.addEdge(0, 1);
		e = dag.addEdge(1, 2);
		e = dag.addEdge(0, 3);
		e = dag.addEdge(3, 4);
		int result = dag.findLCA(4, 2);
		assertEquals(0, result);
		e = dag.addEdge(1, 5);
		result = dag.findLCA(5, 2);
		assertEquals(1, result);
		result = dag.findLCA(34, -10);
		assertEquals(-1, result);
	}
	
	@Test
	void testNoEdges() {
		DAG dag = new DAG(5);
		int result = dag.findLCA(0, 2);
		assertEquals(-1, result);
	}
	
	@Test
	void testNoAncestor() {
		DAG dag = new DAG(6);
		boolean e = dag.addEdge(0, 4);
		e = dag.addEdge(1, 2);
		int result = dag.findLCA(2, 4);
		assertEquals(-1, result);
	}
	
	@Test
	void testCycles() {
		DAG dag = new DAG(3);
		boolean e = dag.addEdge(0, 1);
		e = dag.addEdge(1, 2);
		e = dag.addEdge(2, 0);
		int result = dag.findLCA(2, 1);
		assertEquals(-1, result);
	}
	
	@Test
	void testAdjacent() {
		DAG dag = new DAG(4);
		boolean e = dag.addEdge(0, 1);
		e = dag.addEdge(0, 2);
		e = dag.addEdge(0, 3);
		Iterable<Integer> result = dag.adjacent(0);
		assertEquals("[1, 2, 3]", result.toString());
	}
	
	@Test
	void testReverse() {
		
	}

}
