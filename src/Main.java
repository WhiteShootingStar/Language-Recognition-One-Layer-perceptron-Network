import perceptron.PerceptronController;

public class Main {

	public static void main(String[] args) {
		PerceptronController cont = new PerceptronController();
		cont.makePerceptrons("train", "test");

		System.out.println(Thread.activeCount());
		while (Thread.activeCount() <= cont.perceptronCount + 1) { // Bad implementation of thread-consistency
			if (Thread.activeCount() == 1) {
				cont.results();
				break;
			}
		}

	}

}
