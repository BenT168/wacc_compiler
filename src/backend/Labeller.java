package backend;

public class Labeller {

	private int i = 0;

	/* returns label number */
	public int getLabel() {
		int output = i;
		i++;
		return output;
	}

	public static final Labeller counter = new Labeller() {
	};

}
