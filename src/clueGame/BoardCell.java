package clueGame;

import com.sun.javafx.scene.traversal.Direction;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private DoorDirection doorDir;
	
	
	public BoardCell(int row, int column, char initial, DoorDirection d) {
		super();
		this.row = row;
		this.column = column;
		this.initial = initial;
		this.doorDir = d;
	}
	public BoardCell(int row, int column, char initial, char direction) {
		super();
		this.row = row;
		this.column = column;
		this.initial = initial;
		switch (direction)
		{
			case 'L': doorDir = DoorDirection.LEFT;
						break;
			case 'U': doorDir = DoorDirection.UP;
						break;
			case 'R': doorDir = DoorDirection.RIGHT;
						break;
			case 'D': doorDir = DoorDirection.DOWN;
						break;
			default: doorDir = DoorDirection.NONE;
		}
	}
	public char getInitial() {
		return initial;
	}

	public DoorDirection getDoorDirection() {
		return doorDir;
	}

	public boolean isWalkway() {
		return true;
	}
	
	public boolean isRoom() {
		return true;
	}
	
	public boolean isDoorway() {
		if (doorDir != DoorDirection.NONE)
			return true;
		return false;
	}
}
