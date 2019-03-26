package perceptron;

public class Tuple {// utility class for convenient (in my opinion ) storage in the map
	public String perceptronName;
	public double value;

	public Tuple(String perceptronName, double value) {

		this.perceptronName = perceptronName;
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((perceptronName == null) ? 0 : perceptronName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Tuple other = (Tuple) obj;
		if (perceptronName == null) {
			if (other.perceptronName != null)
				return false;
		} else if (!perceptronName.equals(other.perceptronName))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}

}
