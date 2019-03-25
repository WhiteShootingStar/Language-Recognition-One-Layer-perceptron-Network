package perceptron;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import utils.Utilities;

public class PerceptronController {
	public ConcurrentHashMap<Point, Tuple> map = new ConcurrentHashMap<>();
	RandomAccessFile file;
	File toBuf = new File("NE.OTKRYWAT");
	public ByteBuffer buff;

	public void makePerceptrons(String trainPath, String testPath) {

		List<Point> points = makeSet(trainPath);
		List<Point> testing = makeSet(testPath);
		File[] pathes1 = new File(trainPath).listFiles(File::isDirectory);

		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter String in any language and I will try to recognise it");
		String line = scan.nextLine();
		for (File f : pathes1) {

			(new Perceptron(points, f.getName(), testing, map, line)).start();

		}

	}

	List<Point> makeSet(String path) {
		List<Point> list = new ArrayList<>();
		try {
			List<Path> pathes = new ArrayList<>();
			File[] pathes1 = new File(path).listFiles(File::isDirectory);
			for (File file : pathes1) {
				// System.out.println(file.getPath());
				// System.out.println(file.getName());
				pathes.add(Paths.get(file.getPath()));
			}

			for (Path p : pathes) {
				list.addAll(
						Files.walk(p).filter(e -> e.toFile().isFile()).map(e -> Utilities.createInputVecor(e.toString(),
								p.getFileName().toString(), e.getFileName().toString())).collect(Collectors.toList()));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void results() {
		for (Map.Entry<Point, Tuple> entry : map.entrySet()) {
			System.out.println(entry.getKey().name + "  " + entry.getKey().type
					+ " <--------- is actual type  and prognosed type is this ------> "
					+ entry.getValue().perceptronName);
		}
	}
}
