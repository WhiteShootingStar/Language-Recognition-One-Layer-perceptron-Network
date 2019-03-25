package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import perceptron.Point;

public class Utilities { // utility helpful class to provide useful methods

	public static long countLetter(String text, char letter) { // count occurences of the given letter in the sentence
																// /text (counts both capital and small letters)
		return text.chars().filter(e -> e == letter || e == Character.toUpperCase(letter)).count();

	}

	public static void fillVector(String sentence, double[] vector) { // fill the vector with the total occurrences of
																		// each letter from English alphabet. Works OK
																		// if the size of the array is 27 (not 26)
		for (char i = 'a'; i <= 'z'; i++) {
			vector[i - 'a'] += countLetter(sentence, i);
		}

	}

	static double[] normalizeInputVector(double[] vector) { // normalize the vector (divide each member by the total letters in the text)

		return Arrays.stream(vector).map(e -> e / Arrays.stream(vector).sum()).toArray();

	}

	public static Point createInputVecor(String file, String foulderName, String name) { // create input vector from file
		double[] vector = new double[27];//allocate vector
		try {
			BufferedReader read = new BufferedReader(new FileReader(new File(file)));
			String line;
			while ((line = read.readLine()) != null) {
				fillVector(line, vector);//fill this vector
			}
			// normalizeInputVector(vector);
			read.close();

		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		vector[vector.length - 1] = -1; // make the last element as -1
		vector = normalizeInputVector(vector); // normalize it

		return new Point(vector, foulderName, name);
	}

	public static Point createInputVecor(String text) { // make vector from a text String
		double[] vector = new double[27];
		fillVector(text, vector);
		vector[vector.length - 1] = -1;
		vector = normalizeInputVector(vector);
		// System.out.println(normalizeInputVector(vector));
		return new Point(vector, "", "User Input");
	}
}