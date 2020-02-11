import java.util.Arrays;

/**
 * This class controls the rule the ECA follows and how it is applied in evolution
 * @author Arjun Ganesan
 * @version 1.0
 *
 */
public class Rule {
	private String ruleInBinary;
	
	//all seven binary combinations of the states' of the three cells
	private boolean[] zeroCombo = new boolean[]{false, false, false};
	private boolean[] oneCombo = new boolean[]{false, false, true};
	private boolean[] twoCombo = new boolean[]{false, true, false};
	private boolean[] threeCombo = new boolean[]{false, true, true};
	private boolean[] fourCombo = new boolean[]{true, false, false};
	private boolean[] fiveCombo = new boolean[]{true, false, true};
	private boolean[] sixCombo = new boolean[]{true, true, false};
	private boolean[] sevenCombo = new boolean[]{true, true, true};
	
	/**
	 * This constructor takes the rule of the ECA and converts it to binary
	 * @param rule is the rule of the ECA as an integer
	 */
	public Rule(int rule) {
		ruleInBinary = String.format("%8s", Integer.toBinaryString(rule)).replace(' ', '0');
	}
	
	/**
	 * This method sets the center cell of the next evolution of the ECA given three Cell objects
	 * @param cell1 is the cell left of the center cell
	 * @param cell2 is the center cell
	 * @param cell3 is the cell right of the center cell
	 * @return
	 */
	public Cell setNextCenterCell(Cell cell1, Cell cell2, Cell cell3) {
		
		//creates a temporary generation made of the three cell objects passed as arguments
		Cell[] threeCellArray = new Cell[] {cell1, cell2, cell3};
		Generation threeCells = new Generation(threeCellArray);
		//converts the Generation to a boolean array so it can be compared
		boolean[] generationAsBooleanArray = threeCells.getGenerationAsBooleanArray();
		//initializes variables to be used later
		char returnBit = ' ';
		boolean returnValue = false;
		
		//checks what combo the threeCells match and finds the relevant bit from the binary representation of the rule
		if(Arrays.equals(generationAsBooleanArray,zeroCombo)) {
			returnBit = ruleInBinary.charAt(7);
		}
		else if(Arrays.equals(generationAsBooleanArray,oneCombo)) {
			returnBit = ruleInBinary.charAt(6);
		}
		else if(Arrays.equals(generationAsBooleanArray,twoCombo)) {
			returnBit = ruleInBinary.charAt(5);
		}
		else if(Arrays.equals(generationAsBooleanArray,threeCombo)) {
			returnBit = ruleInBinary.charAt(4);
		}
		else if(Arrays.equals(generationAsBooleanArray,fourCombo)) {
			returnBit = ruleInBinary.charAt(3);
		}
		else if(Arrays.equals(generationAsBooleanArray,fiveCombo)) {
			returnBit = ruleInBinary.charAt(2);
		}
		else if(Arrays.equals(generationAsBooleanArray,sixCombo)) {
			returnBit = ruleInBinary.charAt(1);
		}
		else if(Arrays.equals(generationAsBooleanArray,sevenCombo)) {
			returnBit = ruleInBinary.charAt(0);
		}
		
		//if the bit was 1, the center cell of the next evolution is set to True
		if(returnBit == '1') {
			returnValue = true;
		}
		
		return new Cell(returnValue);
		
	}
}
