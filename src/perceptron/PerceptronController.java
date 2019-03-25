package perceptron;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import utils.Utilities;

public class PerceptronController { // this class controls (starts threads (perceptrons), though you can implement
									// it without threads
	public ConcurrentHashMap<Point, Tuple> map = new ConcurrentHashMap<>(); // Map to be able to store and implement
																			// Maximal Selector
	public int perceptronCount = 0; // counts how much perceptrons we will have (bad implementation for
									// thread-consistency)

	public void makePerceptrons(String trainPath, String testPath) { // main method to run evverything

		List<Point> points = makeSet(trainPath); // training set
		List<Point> testing = makeSet(testPath); // testing set
		File[] pathes1 = new File(trainPath).listFiles(File::isDirectory);// finding how many perceptrons we will have
																			// (the same amount as language foulder)
		perceptronCount = pathes1.length;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter String in any language and I will try to recognise it");
		String line = scan.nextLine(); // getting user input once (BAD IMPLEMENTATION)
		scan.close();
		for (File f : pathes1) {

			(new Perceptron(points, f.getName(), testing, map, line)).start(); // make perceptrons

		}

	}

	List<Point> makeSet(String path) { // method to make a set (training or testing)
		List<Point> list = new ArrayList<>();
		try {
			List<Path> pathes = new ArrayList<>();
			File[] pathes1 = new File(path).listFiles(File::isDirectory);
			for (File file : pathes1) {
				pathes.add(Paths.get(file.getPath())); // finding all the pathes to files
			}

			for (Path p : pathes) {
				list.addAll(
						Files.walk(p).filter(e -> e.toFile().isFile()).map(e -> Utilities.createInputVecor(e.toString(),
								p.getFileName().toString(), e.getFileName().toString())).collect(Collectors.toList())); // finding
																														// a
																														// file
																														// and
																														// create
																														// InputVector
																														// from
																														// it
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void results() { // printing
		for (Map.Entry<Point, Tuple> entry : map.entrySet()) {
			System.out.println(entry.getKey().name + "  " + entry.getKey().type
					+ " <--------- is actual type  and prognosed type is this ------> "
					+ entry.getValue().perceptronName);
		}
	}
}
