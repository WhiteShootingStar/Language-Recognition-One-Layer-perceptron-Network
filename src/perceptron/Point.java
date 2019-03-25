package perceptron;

import java.util.Arrays;

public class Point { // simple class which stores values (normalized vector of letters, type of the text, and the name of the file which contains this text)
	public double[] value_vector;
	public String type;
	public String name;

	public Point(double[] values, String type, String name) {
		value_vector = values;
		this.type = type;
		this.name = name;
	}

	public Point(double[] values) {
		value_vector = values;
		type = null;
		name = null;
	}

	@Override
	public String toString() {
		return Arrays.toString(value_vector) + "this type is " + type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + Arrays.hashCode(value_vector);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (!Arrays.equals(value_vector, other.value_vector))
			return false;
		return true;
	}
	
}
