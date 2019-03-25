package perceptron;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import utils.Utilities;

public class Perceptron extends Thread {

	public List<Point> foulders; // list of training points
	public final String activationValue; // activation value (the same as name of the folder)
	public double[] weights;
	public double THRESHOLD = 0.1;
	public static final double LEARNING_RATE = 0.5;
	public final double ERROR_THRESHHOLD = 0.06;
	volatile double[] inputVector;
	int lines_count = 0;
	double err = 0;
	public volatile List<Point> testing; // training points
	public ConcurrentMap<Point, Tuple> map;
	String toTest;

	public Perceptron(List<Point> foulders, String activationValue, List<Point> testing,
			ConcurrentHashMap<Point, Tuple> map, String toTest) {

		this.foulders = foulders;
		this.activationValue = activationValue;
		this.testing = testing;
		this.map = map;
		this.toTest = toTest;
	}

	@Override
	public synchronized void run() {

		int EPOCH = 0; // count epochs( amount of times testing file has been iterated);
		weights = new double[27];
		weights = Arrays.stream(weights).map(e -> e = 0.1).toArray(); // creating and initializing weight vector
		do {
			train(); // training
			System.out.println(Arrays.toString(weights) + " weight wector after " + EPOCH
					+ "epoch, done for activation value " + activationValue);
			System.out.println("Error is " + (double) err / lines_count + " for " + activationValue + " perceptron");
			EPOCH++;

		} while (err / lines_count > ERROR_THRESHHOLD);
		//spat(1000);
		System.out.println("\u001B[31m" + Arrays.toString(weights) + " are final after " + EPOCH
				+ "Epoch. Final error is " + err / lines_count + " .Activation is " + activationValue);

		test(); // testing

	}

	public synchronized void train() {
		Iterator<Point> iter = foulders.iterator();
		while (iter.hasNext()) { // create point -> modify weight using this point -> add error
			Point point = iter.next();
			modifyWeight(point);
			err += error(point);
			lines_count++;

		}
	}

	public void test() { // create point -> calculate its output -> check whether this point is present
							// in the map -> if yes, then update the value in the map, otherwise, simply add
							// new key-value to the map
		Iterator<Point> iter = testing.iterator();
		while (iter.hasNext()) {

			Point point = iter.next();

			double output = calculateActualOutput(point);
			if (map.containsKey(point)) {

				if (map.get(point).value < output) {
					map.get(point).value = output;
					map.get(point).perceptronName = activationValue;

				}

			} else {
				map.put(point, new Tuple(activationValue, output));
			}

		}
	//	spat(1000);

		Point testingPoint = Utilities.createInputVecor(toTest); // bad implementation of user input. Works bad with threads, should be done iteratively instead
		double output = calculateActualOutput(testingPoint);

		if (map.containsKey(testingPoint)) {

			if (map.get(testingPoint).value < output) {
				map.get(testingPoint).value = output;
				map.get(testingPoint).perceptronName = activationValue;

			}

		} else {
			map.put(testingPoint, new Tuple(activationValue, output));
		}

	}

	double calculateActualOutput(Point inputVector) { // calculate output by formula W^T *X >=0? 1:0 where W^T is
														// transposition of
		// weight vector and X - values vector
		double sum = 0;
		for (int i = 0; i < inputVector.value_vector.length; i++) {
			sum += inputVector.value_vector[i] * weights[i];
		}

		return sum;
		// return sum > 0 ? 1 : 0;

	}

	void modifyWeight(Point inputVector) { // modify weight by the formula Weight_vector = Weight_vector + (desired
											// output
		// - actual output)*lerning_rate* value_vector
		// int desiredOutput = Integer.parseInt(inputVector.type);
		double desiredOutput = 0;
		double actualOutput = calculateActualOutput(inputVector);

		if (inputVector.type.equals(activationValue)) {
			desiredOutput = 1;
		}
		for (int i = 0; i < inputVector.value_vector.length; ++i) {
			weights[i] += (desiredOutput - actualOutput) * LEARNING_RATE * inputVector.value_vector[i];
		}
	}

	double error(Point inputVector) { // calculate part error by formula |Desired_output-Actual_output|
		double a = 0;
		if (inputVector.type.equals(activationValue)) {
			a = 1;
		}
		return Math.abs(a - calculateActualOutput(inputVector));
	}

//	void spat(long milis) {
//		try {
//			Thread.sleep(milis);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}

}
