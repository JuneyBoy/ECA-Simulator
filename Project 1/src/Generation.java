/**
 * This class represents one generation of the ECA, made up of an array of Cell objects
 * @author Arjun Ganesan
 * @version 1.0
 */

public class Generation {
	private Cell[] state;
	private int generationNum;
	
	
	public Generation(boolean[] state) {
		this.state = new Cell[state.length];
		for(int i = 0; i < state.length; ++i) {
			this.state[i] = new Cell(state[i]);
		}
		generationNum = 0;
	}
	
	
	/**
	 * Constructor that does not "care" about what generation number it is, mainly used in Rule class
	 * @param state is an array of Cell objects
	 */
	public Generation(Cell[] state) {
		this.state = state;
		generationNum = 0;
	}
	
	/**
	 * Main constructor that will be used in the Automaton class
	 * @param state is an array of Cell objects
	 * @param generationNum represents which step of the evolution of the ECA this specific generation is
	 */
	public Generation(Cell[] state, int generationNum) {
		this.state = state;
		this.generationNum = generationNum;
	}
	
	public int getGenerationNum() {
		return generationNum;
	}
	
	public boolean[] getGenerationAsBooleanArray() {
		boolean[] returnArray = new boolean[state.length];
		for (int i = 0; i < state.length; ++i) {
			returnArray[i] = state[i].getState();
		}
		return returnArray;
	}
	
	public Cell getCell(int index) {
		return state[index];
	}
}
