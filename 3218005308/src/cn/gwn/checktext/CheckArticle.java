package cn.gwn.checktext;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckArticle {

	public static void main(String[] args) throws IOException {
		String originPath; // 原文路径
		String copyPath; // 抄袭文本路径
		String filePath; // 答案路径
		String[] parms; // 接受参数

		if (args.length != 0) {
			parms = args;
		} else {
			System.out.println("请给出原文绝对路径，抄袭版论文绝对路径，输出答案文件的绝对路径(参数之间空格隔开，格式如下E:\\xxx\\xxx.txt)");
			Scanner scanner = new Scanner(System.in);
			parms = scanner.nextLine().split(" ");
			if (parms.length < 3) {
				System.out.println("输入的内容不符合要求，请重新输入！");
				System.exit(0);
			}
			scanner.close();
		}

		originPath = parms[0]; 
		copyPath=parms[1];
		filePath = parms[2];

		CheckArticle bean = new CheckArticle();
		// 把原著拆分做成一个词典
		Set<String> dictionary = bean.Deconstruct(originPath);
		// 存放被查重文章拆分出来的双字词
		List<String> checkedwords = new ArrayList<String>();

		Set<String> copydictionary = bean.Deconstruct(copyPath);
		Iterator<String> it = copydictionary.iterator();
		while (it.hasNext()) {
			String str = it.next();
			checkedwords.add(str);
		}

		CheckArticle.Checking(dictionary, checkedwords,filePath);
	}

	// 将每一行文本内容中的中文字符提取出来
	public List<String> readChinese(String line) {
		List<String> result = new ArrayList<String>();
		Pattern p = Pattern.compile("([\u4e00-\u9fa5]+)");
		Matcher m = p.matcher(line);

		while (m.find()) {
			result.add(m.group());
		}
		return result;
	}

	// 将文本所有中文字符逐行提取出来
	public List<String> readChineseFromFile(String filename) {
		List<String> result = new ArrayList<String>();
		BufferedReader br = null;
		try {
			// 读取文本文件的内容
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String line = null;
			Set<String> set = new HashSet<String>();
			while (null != (line = br.readLine())) {
				// 逐行遍历这个文本文件，调用这个函数，获取某一行内容中的所有中文字符
				List<String> rowChineseList = readChinese(line);
				// 通过一个集合对中文字符进行去重
				set.addAll(rowChineseList);
			}
			result.addAll(set);
			Collections.sort(result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					br = null;
				}
			}
		}
		return result;
	}

	// 将 readChineseFromFile()中提取到的文字进行拆解
	public Set<String> Deconstruct(String filename) {
		CheckArticle bean = new CheckArticle();
		List<String> result = bean.readChineseFromFile(filename);
		Set<String> WordsContainer = new HashSet<String>();
		String words = "";
		for (String s : result) {
			for (int i = 0; i < s.length() - 1; i++) {
				// 进行相邻双字词组合
				words = String.valueOf(s.charAt(i)) + String.valueOf(s.charAt(i + 1));
				if (WordsContainer.contains(words) == false) {
					WordsContainer.add(words);
				}
			}
		}
		return WordsContainer;
	}

	// 用Deconstruct(path)文章拆分出来的词组进行对比查重
	public static void Checking(Set<String> dictionary, List<String> checkedwords,String filePath) throws IOException {
		int DeconstructedNum = checkedwords.size();// 需要查重文章被拆分出来双词的数目
		int similarWords = 0; // 双词相似数目
		for (int i = 0; i < DeconstructedNum - 1; i++) {
			if (dictionary.contains(checkedwords.get(i)) == true) {
				similarWords++;
			}
		}
		float similar = (float) similarWords / (float) DeconstructedNum;
		System.out.println("\n相似双字词数目为：" + similarWords + "\n查重文章被拆分出的双字词数目为：" + DeconstructedNum + "\n查重率：" + similar);

		Logfile log = new Logfile(filePath, "result");
		log.addToLog("\n相似双字词数目为：" + similarWords);
		log.addToLog("\n查重文章被拆分出的双字词数目为：" + DeconstructedNum);
		log.addToLog("\n查重率：" + similar);

	}
}