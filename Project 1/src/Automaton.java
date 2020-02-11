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
		generationList.add(new Generation(initState));
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
		
		Generation workingGeneration = generationList.get(steps);
		Cell[] nextGeneration = new Cell[initState.length];
		Cell cell1 = new Cell(false);
		Cell cell2 = new Cell(false);
		Cell cell3 = new Cell(false);
		
		for (int i = numSteps; i > 0; --i) {
			workingGeneration = generationList.get(steps);
			nextGeneration = new Cell[initState.length];
			
			for(int j = 0; j < initState.length; ++j) {
				if (j == 0) {
					cell1 = workingGeneration.getCell(initState.length - 1);
					cell2 = workingGeneration.getCell(j);
					cell3 = workingGeneration.getCell(j + 1);
				}
				
				else if(j == initState.length - 1) {
					cell1 = workingGeneration.getCell(j - 1);
					cell2 = workingGeneration.getCell(j);
					cell3 = workingGeneration.getCell(0);
				}
				
				else {
					cell1 = workingGeneration.getCell(j - 1);
					cell2 = workingGeneration.getCell(j);
					cell3 = workingGeneration.getCell(j + 1);
				}
				
				nextGeneration[j] = ruleAsBinary.setNextCenterCell(cell1, cell2, cell3);
			}
			generationList.add(new Generation(nextGeneration, steps));
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
	 * Converts the 
	 * @param stepNum
	 * @return
	 */
	public String getStateString(int stepNum) {
		String stateString = "";
		Generation relevantGeneration = generationList.get(stepNum);
		for (int i = 0; i < initState.length; ++i) {
			if((relevantGeneration.getCell(i)).getState() == true) {
				stateString += trueSymbol;
			}
			else {
				stateString += falseSymbol;
			}
		}
		return stateString;
	}
	
	public String toString() {
		String returnString = "";
		
		for(int i = 0; i <= steps; ++i) {
			if(i == steps) {
				returnString += this.getStateString(i);
			}
			else {
				returnString += this.getStateString(i) + "\n";
			}
		}
		return returnString;
	}
	
	public void save(String filename) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write(this.toString());
		writer.close();
	}
	
	public char getFalseSymbol() {
		return falseSymbol;
	}
	
	public void setFalseSymbol(char symbol) {
		falseSymbol = symbol;
	}
	
	public char getTrueSymbol() {
		return trueSymbol;
	}
	
	public void setTrueSymbol(char symbol) {
		trueSymbol = symbol;
	}

}
