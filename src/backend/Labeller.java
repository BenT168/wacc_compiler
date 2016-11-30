package backend;

public class Labeller {

	private int i = 0;

	public int getLabel() {
		int output = i;
		i++;
		return output;
	}

	public static final Labeller counter = new Labeller() {
	};

}
