import java.io.IOException;
public class Driver {

	public static void main(String[] args)throws IOException {
		boolean[] initState = new boolean[] {false, false, false, true, false, false, false};
		boolean[] finalState = new boolean[] {true, false, true, true, false, false};
		
		Automaton test1 = new Automaton("rule54-29cells-input.txt");
		test1.evolve(30);
		test1.save("rule54-29cells-output.txt");
		//test1.evolve(7);
		//test1.save("rule22-15cells-output");
		
	}
}
