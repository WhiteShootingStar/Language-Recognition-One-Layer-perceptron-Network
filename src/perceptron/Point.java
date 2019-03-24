package perceptron;

import java.util.Arrays;

public class Point {
	public double[] value_vector;
	public String type;

	public Point(double[] values, String type) {
		value_vector = values;
		this.type = type;
	}

	@Override
	public String toString() {
		return Arrays.toString(value_vector) + "this type is " + type;
	}
}
