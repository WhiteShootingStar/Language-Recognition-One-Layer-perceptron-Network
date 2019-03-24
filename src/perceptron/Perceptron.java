package perceptron;

import java.io.Console;
import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Perceptron extends Thread {

	public List<Point> foulders;
	public final String activationValue;
	public double[] weights;
	public double THRESHOLD = 0.1;
	public static final double LEARNING_RATE = 0.5;
	public final double ERROR_THRESHHOLD = 0.002;
	volatile double[] inputVector;
	int lines_count=0;
	int err =0;
	public Perceptron(List<Point> foulders, String activationValue) {

		this.foulders = foulders;
		this.activationValue = activationValue;
	}

	@Override
	public void run() {
		
	 // count total lines read
		int EPOCH = 0; // count epoches( amount of times testing file has been iterated);
		weights = new double[27];
		weights =Arrays.stream(weights).map(e -> e = 0.1).toArray();
		do {
			train();
			System.out.println(Arrays.toString(weights) + " weight wector after " + EPOCH + "epoch, done for activation value " + activationValue );
			System.out.println("Error is " + (double) err / lines_count + " for " + activationValue + " perceptron");
			EPOCH++;
		
		} while ((double) err / lines_count > ERROR_THRESHHOLD);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("\u001B[31m" + Arrays.toString(weights) + " are final after "+ EPOCH + "Epoch. Final error is " + (double) err / lines_count  );
	}

	public void train() {
		Iterator<Point> iter = foulders.iterator();
		while (iter.hasNext()) {
			Point point = iter.next();
			modifyWeight(point);
			err += error(point);
			lines_count++;
//			System.out.println(calculateActualOutput(point));
		}
	}

	int calculateActualOutput(Point inputVector) { // calculate output by formula W^T *X >=0? 1:0 where W^T is
													// transposition of
		// weight vector and X - values vector
		double sum = 0;
		for (int i = 0; i < inputVector.value_vector.length; i++) {
			sum += inputVector.value_vector[i] * weights[i];
		}

		return sum > 0 ? 1 : 0;
		
	}

	void modifyWeight(Point inputVector) { // modify weight by the formula Weight_vector = Weight_vector + (desired
											// output
		// - actual output)*lerning_rate* value_vector
		// int desiredOutput = Integer.parseInt(inputVector.type);
		int desiredOutput = 0;
		int actualOutput = calculateActualOutput(inputVector);
		if (inputVector.type.equals(activationValue)) {
			desiredOutput = 1;
		}
		for (int i = 0; i < inputVector.value_vector.length; ++i) {
			weights[i] += (desiredOutput - actualOutput) * LEARNING_RATE * inputVector.value_vector[i];
		}
	}

	int error(Point inputVector) { // calculate part error by formula |Desired_output-Actual_output|
		int a = 0;
		if (inputVector.type.equals(activationValue)) {
			a = 1;
		}
		return Math.abs(a - calculateActualOutput(inputVector));
	}

}
