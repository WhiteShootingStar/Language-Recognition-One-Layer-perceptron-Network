package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import perceptron.Point;

public class Utilities {

	public static long countLetter(String sentence, char letter) {
		// int count =0;
		// for(int i =0;i<sentence.length();i++) {
		// if(sentence.charAt(i)==letter || sentence.charAt(i)==5) {
		// count ++;
		//
		// }
		// }
		return sentence.chars().filter(e -> e == letter || e == Character.toUpperCase(letter)).count();

	}

	public static void fillVector(String sentence, double[] vector) {
		for (char i = 'a'; i <= 'z'; i++) {
			vector[i - 'a'] += countLetter(sentence, i);
		}

	}

	static double[] normalizeInputVector(double[] vector) {

		return Arrays.stream(vector).map(e -> e / Arrays.stream(vector).sum()).toArray();

	}

	public static Point createInputVecor(String file, String foulderName, String name) {
		double[] vector = new double[27];
		try {
			BufferedReader read = new BufferedReader(new FileReader(new File(file)));
			String line;
			while ((line = read.readLine()) != null) {
				fillVector(line, vector);
			}
			// normalizeInputVector(vector);
			read.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vector[vector.length - 1] = -1;
		vector = normalizeInputVector(vector);

		return new Point(vector, foulderName, name);
	}

	public static Point createInputVecor(String text) {
		double[] vector = new double[27];
		fillVector(text, vector);
		vector[vector.length - 1] = -1;
		vector = normalizeInputVector(vector);
		return new Point(vector);
	}
}