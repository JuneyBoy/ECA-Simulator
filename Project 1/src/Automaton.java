import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is class represents one elementary cellular automata
 * @author Arjun Ganesan
 * @version 1.0
 *
 */

public class Automaton {
	
	private int ruleNum;
	private boolean[] initState;
	private int steps;
	private char falseSymbol;
	private char trueSymbol;
	private ArrayList<Generation> generationList = new ArrayList<Generation>();
	private Rule ruleAsBinary;
	
	/**
	 * This constructor directly takes the rule and initial state of the ECA as parameters
	 * @param ruleNum assigns the rule by which the ECA will be evolving
	 * @param initState is the first generation of the ECA represented by an array of boolean values
	 */
	public Automaton(int ruleNum, boolean[] initState) {
		this.ruleNum = ruleNum;
		ruleAsBinary = new Rule(ruleNum);
		this.initState = initState;
		generationList = new ArrayList<Generation>();
		generationList.add(new Generation(initState));
		this.setFalseSymbol('0');
		this.setTrueSymbol('1');
		steps = 0;
	}
	
	/**
	 * This constructor takes a .txt file and reads all the relavent info from it to make an ECA
	 * @param filename is the file from which the rule, true and false symbols, and the initial state will be read
	 * @throws IOException
	 */
	public Automaton(String filename)throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		//ruleNum will be on the first line of the file
		String ruleNumString = reader.readLine();
		//the symbols will be on the second line
		String symbolsString = reader.readLine();
		//the initial state will be on the third line
		String initStateString = "";
		String line = reader.readLine();
		while(line != null) {
			initStateString += line;
			line = reader.readLine();
		}
		
		reader.close();
		
		//converts the ruleNum to an int value
		ruleNum = Integer.parseInt(ruleNumString);
		ruleAsBinary = new Rule(ruleNum);
		
		//separates the true and false symbols from the string
		falseSymbol = symbolsString.charAt(0);
		trueSymbol = symbolsString.charAt(2);
		
		//creates a boolean array from the initStateString by reading which cells contained the true and false symbols
		initState = new boolean [initStateString.length()];
		for(int i = 0; i < initStateString.length(); ++i) {
			if(initStateString.charAt(i) == trueSymbol) {
				initState[i] = true;
			}
			else {
				initState[i] = false;
			}
		}
		generationList = new ArrayList<Generation>();
		generationList.add(new Generation(initState));
		steps = 0;
	}
	
	/**
	 * Returns the rule of the ECA
	 * @return
	 */
	public int getRuleNum() {
		return ruleNum;
	}
	
	/**
	 * Evolves the ECA a specified number of generations
	 * @param numSteps is how many generations the ECA will be evolved
	 */
	public void evolve(int numSteps) {
		//gets most recent Generation
		Generation workingGeneration = generationList.get(steps);
		//initializes Cell array that will become the next Generation
		Cell[] nextGeneration = new Cell[initState.length];
		//initializes 3 Cell objects to be used to determine the Cell objects in the next Generation
		Cell leftCell = new Cell(false);
		Cell centerCell = new Cell(false);
		Cell rightCell = new Cell(false);
		
		//outer for loop iterates numSteps times
		for (int i = numSteps; i > 0; --i) {
			//at the beginning of each iteration of the outer for loop, the workingGeneration is updated to be the most recent generation
			workingGeneration = generationList.get(steps);
			//similarly, the nextGeneration is initialized with default Cell objects
			nextGeneration = new Cell[initState.length];
			
			//inner for loop iterates through each Cell of any given Generation(all generations of a given ECA are the same length)
			for(int j = 0; j < initState.length; ++j) {
				//if the "center cell" is the first cell in the Generation, the left cell is defined as the last cell in the Generation
				if (j == 0) {
					leftCell = workingGeneration.getCell(initState.length - 1);
					centerCell = workingGeneration.getCell(j);
					rightCell = workingGeneration.getCell(j + 1);
				}
				
				//if the "center cell" is the last cell in the Generation, the right cell is defined as the first cell in the Generation
				else if(j == initState.length - 1) {
					leftCell = workingGeneration.getCell(j - 1);
					centerCell = workingGeneration.getCell(j);
					rightCell = workingGeneration.getCell(0);
				}
				
				//otherwise, the left cell and right cell are pretty self explanatory
				else {
					leftCell = workingGeneration.getCell(j - 1);
					centerCell = workingGeneration.getCell(j);
					rightCell = workingGeneration.getCell(j + 1);
				}
				//calling Rule object method to set the center cell of the next Generation
				nextGeneration[j] = ruleAsBinary.setNextCenterCell(leftCell, centerCell, rightCell);
			}
			//adds the Generation that was just created to the end of the Generation arraylist
			generationList.add(new Generation(nextGeneration));
			//increments the number of steps each iteration of the outer for loop
			++steps;
		}
	}
	
	/**
	 * Returns the current number of generations the ECA has been evolved
	 * @return
	 */
	public int getTotalSteps() {
		return steps;
	}
	
	/**
	 * Returns the boolean array representation of a given generation
	 * @param stepNum is the generation that will be returned
	 * @return
	 */
	public boolean[] getState(int stepNum) {
		return (generationList.get(stepNum)).getGenerationAsBooleanArray();
	}
	
	/**
	 * Converts a given generation to a string 
	 * @param stepNum is the specific generation that will be represented as a string 
	 * @return
	 */
	public String getStateString(int stepNum) {
		String stateString = "";
		//gets the Generation from the arraylist at the given stepNum
		Generation relevantGeneration = generationList.get(stepNum);
		//for loop iterates through each Cell of the Generation
		for (int i = 0; i < initState.length; ++i) {
			//Checks what state each Cell is in and adds to the String the appropriate true and false characters
			if((relevantGeneration.getCell(i)).getState() == true) {
				stateString += trueSymbol;
			}
			else {
				stateString += falseSymbol;
			}
		}
		return stateString;
	}
	
	/**
	 * outputs the entire evolution of the ECA as a string
	 */
	public String toString() {
		String returnString = "";
		//iterates through every Generation of the ECA
		for(int i = 0; i <= steps; ++i) {
			//if the Generation is the most current of the ECA, getStateString is used and it is added to the string
			if(i == steps) {
				returnString += this.getStateString(i);
			}
			//if the Generation is any other, a newline character is added before adding the next generation to the string
			else {
				returnString += this.getStateString(i) + "\n";
			}
		}
		return returnString;
	}
	
	/**
	 * outputs the entire evolution of the ECA as a string to a .txt file
	 * @param filename is the .txt file that the output will be saved to
	 * @throws IOException
	 */
	public void save(String filename) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write(this.toString());
		writer.close();
	}
	
	/**
	 * 
	 * @return returns the falseSymbol for the ECA
	 */
	public char getFalseSymbol() {
		return falseSymbol;
	}
	
	/**
	 * 
	 * @param symbol sets the falseSymbol for the ECA to the given character
	 */
	public void setFalseSymbol(char symbol) {
		falseSymbol = symbol;
	}
	
	/**
	 * 
	 * @return returns the trueSymbol for the ECA
	 */
	public char getTrueSymbol() {
		return trueSymbol;
	}
	
	/**
	 * 
	 * @param symbol sets the falseSymbol for the ECA to the given character
	 */
	public void setTrueSymbol(char symbol) {
		trueSymbol = symbol;
	}

}
