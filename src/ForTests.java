import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import perceptron.PerceptronController;
import utils.Utilities;

public class ForTests {

//	@Test
//	public void testCountLetter() {
//		Assert.assertEquals(2, Utilities.countLetter("Putin Pidor" , 'i'));
//		Assert.assertEquals(5, Utilities.countLetter("Slava Ukraine, Sanya lox" , 'a'));
//		Assert.assertNotEquals(4, Utilities.countLetter("Slava Ukraine, Sanya lox" , 'a'));
//		Assert.assertEquals(2, Utilities.countLetter("Slava Ukraine, Sanya lox" , 'l'));
//	}
//	@Test
//	public void testCreateInpytVector() {
//		System.out.println((Utilities.createInputVecor(new File("train//DE//DE_1.txt"),"DE")));
//
//		System.out.println(Utilities.createInputVecor(new File("train//DE//DE_2.txt"),"DE"));
//		System.out.println(Utilities.createInputVecor(new File("train//PL//PL_1.txt"),"PL"));
//		System.out.println(Utilities.createInputVecor(new File("train//PL//PL_2.txt"),"PL"));
//	}
	
	@Test
	public void testmakePerceptron() {
		PerceptronController cont = new PerceptronController();
		cont.makePerceptrons("train");
	}

}
