package cn.gwn.checktext;

import static org.junit.Assert.*;

import java.io.IOException;

public class Test {

	@org.junit.Test
	public void test() {
		String[] args={
				"E:\\����ѧϰ����\\�������\\��������\\orig.txt",
				"E:\\����ѧϰ����\\�������\\��������\\orig_0.8_dis_1.txt",
				"E:\\����ѧϰ����\\�������\\��������"
		};
		try {
			CheckArticle.main(args);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
