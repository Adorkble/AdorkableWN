package cn.program2;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import cn.program2.ProductHomework;

public class ProductHomeworkTest {

	@Test
	public void testReductionFraction() {
		String[] args={
			"-r 20",
			"-n 50"
			
		};
		try {
			ProductHomework.main(args);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
