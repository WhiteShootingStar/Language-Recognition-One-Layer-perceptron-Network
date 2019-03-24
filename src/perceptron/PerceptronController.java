package perceptron;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import utils.Utilities;

public class PerceptronController {


	public void makePerceptrons(String path) {
		try {
			// List<Path> pathes = Files.walk(Paths.get(path)).filter(e ->
			// Files.isDirectory(e))
			// .collect(Collectors.toList());
			List<Path> pathes = new ArrayList<>();
			File[] pathes1 = new File(path).listFiles(File::isDirectory);
			for (File file : pathes1) {
				// System.out.println(file.getPath());
				// System.out.println(file.getName());
				pathes.add(Paths.get(file.getPath()));
			}

			List<Point> points = new ArrayList<>();
			for (Path p : pathes) {
				points.addAll(Files.walk(p).filter(e -> e.toFile().isFile())
						.map(e -> Utilities.createInputVecor(e.toString(), p.getFileName().toString())).collect(Collectors.toList()));
			}
		

			for (File f : pathes1) {

				(new Perceptron(points, f.getName())).start();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
