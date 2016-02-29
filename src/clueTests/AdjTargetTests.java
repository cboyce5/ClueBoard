package clueTests;

import java.util.LinkedList;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class AdjTargetTests {
	private static Board board;
	@BeforeClass
	public static void setUp() {
		board = new Board("layout.txt", "legend.txt");
		board.initialize();
	}
	@Test
	public void testAdjacenciesInsideRooms()
	{
		LinkedList<BoardCell> testList = board.getAdjList(0, 0);
		assertEquals(0, testList.size());
		testList = board.getAdjList(6,0);
		assertEquals(0, testList.size());
		testList = board.getAdjList(9, 0);
		assertEquals(0, testList.size());
		testList = board.getAdjList(11, 3);
		assertEquals(0, testList.size());
		testList = board.getAdjList(5, 4);
		assertEquals(0, testList.size());
		testList = board.getAdjList(0, 20);
		assertEquals(0, testList.size());
	}
	@Test
	public void testAdjacencyRoomExit()
	{
		LinkedList<BoardCell> testList = board.getAdjList(13, 5);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(13, 6)));
		testList = board.getAdjList(11, 14);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(11, 13)));
		testList = board.getAdjList(5, 6);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 6)));
		testList = board.getAdjList(9, 4);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(8, 4)));
		
	}
	
	@Test
	public void testAdjacencyDoorways()
	{
		LinkedList<BoardCell> testList = board.getAdjList(13, 6);
		assertTrue(testList.contains(board.getCellAt(13, 5)));
		assertTrue(testList.contains(board.getCellAt(13, 7)));
		assertTrue(testList.contains(board.getCellAt(12, 6)));
		assertTrue(testList.contains(board.getCellAt(14, 6)));
		assertEquals(4, testList.size());
		testList = board.getAdjList(7, 1);
		assertTrue(testList.contains(board.getCellAt(6, 1)));
		assertTrue(testList.contains(board.getCellAt(7, 0)));
		assertTrue(testList.contains(board.getCellAt(7, 2)));
		assertTrue(testList.contains(board.getCellAt(8, 1)));
		assertEquals(4, testList.size());
		testList = board.getAdjList(17, 7);
		assertTrue(testList.contains(board.getCellAt(17, 6)));
		assertTrue(testList.contains(board.getCellAt(17, 8)));
		assertTrue(testList.contains(board.getCellAt(18, 7)));
		assertTrue(testList.contains(board.getCellAt(16, 7)));
		assertEquals(4, testList.size());
		testList = board.getAdjList(8, 4);
		assertTrue(testList.contains(board.getCellAt(8, 3)));
		assertTrue(testList.contains(board.getCellAt(8, 5)));
		assertTrue(testList.contains(board.getCellAt(7, 4)));
		assertTrue(testList.contains(board.getCellAt(9, 4)));
		assertEquals(4, testList.size());
	}

	@Test
	public void testAdjacencyEdge()
	{
		LinkedList<BoardCell> testList = null;
		testList = board.getAdjList(24,6);
		assertTrue(testList.contains(board.getCellAt(23, 6)));
		assertEquals(1, testList.size());
		
		testList = board.getAdjList(7,0);
		assertTrue(testList.contains(board.getCellAt(8, 0)));
		assertTrue(testList.contains(board.getCellAt(7, 1)));
		assertEquals(2, testList.size());
		
		testList = board.getAdjList(0, 3);
		assertTrue(testList.contains(board.getCellAt(1, 3)));
		assertEquals(1, testList.size());
		
		testList = board.getAdjList(8, 20);
		assertTrue(testList.contains(board.getCellAt(8, 19)));
		assertEquals(1, testList.size());
	}
	@Test
	public void testAdjacencyWalkways()
	{
		LinkedList<BoardCell> testList = board.getAdjList(0, 3);
		assertTrue(testList.contains(board.getCellAt(1, 3)));
		assertEquals(1, testList.size());
	
		testList = board.getAdjList(6, 3);
		assertTrue(testList.contains(board.getCellAt(6, 4)));
		assertTrue(testList.contains(board.getCellAt(5, 3)));
		assertTrue(testList.contains(board.getCellAt(7, 3)));
		assertEquals(3, testList.size());

		testList = board.getAdjList(8, 18);
		assertTrue(testList.contains(board.getCellAt(8, 17)));
		assertTrue(testList.contains(board.getCellAt(8, 19)));
		assertEquals(2, testList.size());

		testList = board.getAdjList(7,6);
		assertTrue(testList.contains(board.getCellAt(7, 5)));
		assertTrue(testList.contains(board.getCellAt(7, 7)));
		assertTrue(testList.contains(board.getCellAt(6, 6)));
		assertTrue(testList.contains(board.getCellAt(8, 6)));
		assertEquals(4, testList.size());
		
		testList = board.getAdjList(24, 15);
		assertTrue(testList.contains(board.getCellAt(23, 15)));
		assertTrue(testList.contains(board.getCellAt(24, 14)));
		assertEquals(2, testList.size());
		
		testList = board.getAdjList(6, 14);
		assertTrue(testList.contains(board.getCellAt(7, 14)));
		assertTrue(testList.contains(board.getCellAt(6, 13)));
		assertEquals(2, testList.size());

		testList = board.getAdjList(5, 12);
		assertTrue(testList.contains(board.getCellAt(6, 12)));
		assertTrue(testList.contains(board.getCellAt(5, 13)));
		assertEquals(2, testList.size());
		
	}
	
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(19, 7, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(18, 7)));
		assertTrue(targets.contains(board.getCellAt(19, 6)));	
		
		board.calcTargets(6, 3, 1);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(6, 4)));
		assertTrue(targets.contains(board.getCellAt(5, 3)));	
		assertTrue(targets.contains(board.getCellAt(7, 3)));			
	}
	
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(24, 6, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(22, 6)));
		
		board.calcTargets(15, 12, 2);
		targets= board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCellAt(13, 12)));
		assertTrue(targets.contains(board.getCellAt(14, 13)));	
		assertTrue(targets.contains(board.getCellAt(16, 13)));
		assertTrue(targets.contains(board.getCellAt(17, 12)));
		assertTrue(targets.contains(board.getCellAt(16, 11)));	
		assertTrue(targets.contains(board.getCellAt(15, 10)));	
		assertTrue(targets.contains(board.getCellAt(14, 11)));	
	}
	
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(0, 4, 4);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(4, 8)));
		
		board.calcTargets(1, 8, 4);
		targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(5, 8)));	
	}	
	

	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(19, 20, 6);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(19, 14)));
		assertTrue(targets.contains(board.getCellAt(20, 15)));		
	}	
	

	@Test 
	public void testTargetsIntoRoom()
	{
		board.calcTargets(7, 11, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(5, 11)));
		assertTrue(targets.contains(board.getCellAt(6, 12)));
		assertTrue(targets.contains(board.getCellAt(7, 13)));
		assertTrue(targets.contains(board.getCellAt(8, 12)));
		assertTrue(targets.contains(board.getCellAt(7, 9)));
		assertTrue(targets.contains(board.getCellAt(6, 10)));
		
	}
	
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(3, 12, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(1, 13)));
		assertTrue(targets.contains(board.getCellAt(5, 13)));
		assertTrue(targets.contains(board.getCellAt(3, 12)));	
	}

	@Test
	public void testRoomExit()
	{
		board.calcTargets(7, 17, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(8, 17)));
		board.calcTargets(7, 17, 2);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(8, 16)));
		assertTrue(targets.contains(board.getCellAt(8, 18)));
	}

}