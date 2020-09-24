package cn.gwn.checktext;

import static org.junit.Assert.*;

import java.io.IOException;

public class Test {

	@org.junit.Test
	public void test() {
		String[] args={
				"E:\\大三学习资料\\软件工程\\查重内容\\orig.txt",
				"E:\\大三学习资料\\软件工程\\查重内容\\orig_0.8_dis_1.txt",
				"E:\\大三学习资料\\软件工程\\查重内容"
		};
		try {
			CheckArticle.main(args);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
