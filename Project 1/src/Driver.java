import java.io.IOException;
public class Driver {

	public static void main(String[] args)throws IOException {
		boolean[] initState = new boolean[] {false, false, false, true, false, false, false};
		boolean[] finalState = new boolean[] {false, false, false};
		
		Automaton test1 = new Automaton("rule122-29cells-output.txt");
		test1.evolve(5);
		String output = test1.toString();
		System.out.println(output);
		//test1.evolve(7);
		//test1.save("rule22-15cells-output");
		
	}
}
