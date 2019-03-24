package perceptron;

import java.util.Arrays;

public class Point {
	public double[] value_vector;
	public String type;
	public String name;
	public Point(double[] values, String type,String name) {
		value_vector = values;
		this.type = type;
		this.name=name;
	}

	@Override
	public String toString() {
		return Arrays.toString(value_vector) + "this type is " + type;
	}
}
