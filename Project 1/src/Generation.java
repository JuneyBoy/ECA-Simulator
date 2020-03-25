/**
 * This class represents one generation of the ECA, made up of an array of Cell objects
 * @author Arjun Ganesan
 * @version 1.0
 */

public class Generation {
	private Cell[] state;
	private int size = 0;
	
	public Generation(boolean[] state) {
		this.state = new Cell[state.length];
		for(int i = 0; i < state.length; ++i) {
			this.state[i] = new Cell(state[i]);
			++size;
		}
	}
	
	
	/**
	 * Constructor that takes an array of Cell objects
	 * @param state is an array of Cell objects
	 */
	public Generation(Cell[] state) {
		this.state = state;
		size = state.length;
	}
	
	/**
	 * 
	 * @return a boolean array representation of each of the Cell objects in the Generation
	 */
	public boolean[] getGenerationAsBooleanArray() {
		boolean[] returnArray = new boolean[state.length];
		for (int i = 0; i < state.length; ++i) {
			returnArray[i] = state[i].getState();
		}
		return returnArray;
	}
	
	public Cell[] getState() {
		return state;
	}
	
	public int getSize() {
		return size;
	}
	/**
	 * 
	 * @param index 
	 * @return a Cell at the given index of the Generation array
	 */
	public Cell getCell(int index) {
		return state[index];
	}
}
