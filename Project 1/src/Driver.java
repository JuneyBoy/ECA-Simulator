import java.io.IOException;
public class Driver {

	public static void main(String[] args)throws IOException {
		boolean[] initState = new boolean[] {false, false, false, true, false, false, false};
		boolean[] finalState = new boolean[] {true, false, true, true, false, false};
		
		Automaton test1 = new Automaton(222, initState);
		test1.setFalseSymbol('t');
		test1.setTrueSymbol('f');
		test1.evolve(6);
		String output = test1.toString();
		System.out.println(output);
		//test1.evolve(7);
		//test1.save("rule22-15cells-output");
		
	}
}
