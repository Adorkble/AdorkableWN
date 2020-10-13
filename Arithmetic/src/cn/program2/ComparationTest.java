package cn.program2;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ComparationTest {

	@Test
	public void test() {
		String[] args={
			"C://Users//HASEE//workspace//Arithmetic//src//cn//program2//Answers.txt",
			"C://Users//HASEE//workspace//Arithmetic//src//cn//program2//StudentAnswers.txt",
			"C://Users/HASEE//workspace//Arithmetic//src//cn//program2"
				
		};
		try {
			Comparation.main(args);
	   } catch (IOException e) {
		    e.printStackTrace();
	   }
    }
}


