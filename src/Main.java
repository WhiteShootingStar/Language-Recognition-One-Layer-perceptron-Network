import perceptron.PerceptronController;

public class Main {

	public static void main(String[] args) {
		PerceptronController cont = new PerceptronController();
	cont.makePerceptrons("train","test");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cont.results();
	//	System.out.println("kappa");
	}

}
