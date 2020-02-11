/**
 * Most basic unit of the entire program representing one cell of the Automaton that is either set to true or false
 * @author Arjun Ganesan
 * @version 1.0
 *
 */
public class Cell {
	private boolean cellState;
	
	/**
	 * Constructs a Cell object that has one attribute: whether it is true or false
	 * @param state sets the Cell to true or false
	 */
	public Cell(boolean state) {
		cellState = state;
	}
	/**
	 * 
	 * @return returns the state of the cell
	 */
	public boolean getState() {
		return cellState;
	}
}
