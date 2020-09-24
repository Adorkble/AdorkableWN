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
		String originPath; // ԭ��·��
		String copyPath; // ��Ϯ�ı�·��
		String filePath; // ��·��
		String[] parms; // ���ܲ���

		if (args.length != 0) {
			parms = args;
		} else {
			System.out.println("�����ԭ�ľ���·������Ϯ�����ľ���·����������ļ��ľ���·��(����֮��ո��������ʽ����E:\\xxx\\xxx.txt)");
			Scanner scanner = new Scanner(System.in);
			parms = scanner.nextLine().split(" ");
			if (parms.length < 3) {
				System.out.println("��������ݲ�����Ҫ�����������룡");
				System.exit(0);
			}
			scanner.close();
		}

		originPath = parms[0]; 
		copyPath=parms[1];
		filePath = parms[2];

		CheckArticle bean = new CheckArticle();
		// ��ԭ���������һ���ʵ�
		Set<String> dictionary = bean.Deconstruct(originPath);
		// ��ű��������²�ֳ�����˫�ִ�
		List<String> checkedwords = new ArrayList<String>();

		Set<String> copydictionary = bean.Deconstruct(copyPath);
		Iterator<String> it = copydictionary.iterator();
		while (it.hasNext()) {
			String str = it.next();
			checkedwords.add(str);
		}

		CheckArticle.Checking(dictionary, checkedwords,filePath);
	}

	// ��ÿһ���ı������е������ַ���ȡ����
	public List<String> readChinese(String line) {
		List<String> result = new ArrayList<String>();
		Pattern p = Pattern.compile("([\u4e00-\u9fa5]+)");
		Matcher m = p.matcher(line);

		while (m.find()) {
			result.add(m.group());
		}
		return result;
	}

	// ���ı����������ַ�������ȡ����
	public List<String> readChineseFromFile(String filename) {
		List<String> result = new ArrayList<String>();
		BufferedReader br = null;
		try {
			// ��ȡ�ı��ļ�������
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String line = null;
			Set<String> set = new HashSet<String>();
			while (null != (line = br.readLine())) {
				// ���б�������ı��ļ������������������ȡĳһ�������е����������ַ�
				List<String> rowChineseList = readChinese(line);
				// ͨ��һ�����϶������ַ�����ȥ��
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

	// �� readChineseFromFile()����ȡ�������ֽ��в��
	public Set<String> Deconstruct(String filename) {
		CheckArticle bean = new CheckArticle();
		List<String> result = bean.readChineseFromFile(filename);
		Set<String> WordsContainer = new HashSet<String>();
		String words = "";
		for (String s : result) {
			for (int i = 0; i < s.length() - 1; i++) {
				// ��������˫�ִ����
				words = String.valueOf(s.charAt(i)) + String.valueOf(s.charAt(i + 1));
				if (WordsContainer.contains(words) == false) {
					WordsContainer.add(words);
				}
			}
		}
		return WordsContainer;
	}

	// ��Deconstruct(path)���²�ֳ����Ĵ�����жԱȲ���
	public static void Checking(Set<String> dictionary, List<String> checkedwords,String filePath) throws IOException {
		int DeconstructedNum = checkedwords.size();// ��Ҫ�������±���ֳ���˫�ʵ���Ŀ
		int similarWords = 0; // ˫��������Ŀ
		for (int i = 0; i < DeconstructedNum - 1; i++) {
			if (dictionary.contains(checkedwords.get(i)) == true) {
				similarWords++;
			}
		}
		float similar = (float) similarWords / (float) DeconstructedNum;
		System.out.println("\n����˫�ִ���ĿΪ��" + similarWords + "\n�������±���ֳ���˫�ִ���ĿΪ��" + DeconstructedNum + "\n�����ʣ�" + similar);

		Logfile log = new Logfile(filePath, "result");
		log.addToLog("\n����˫�ִ���ĿΪ��" + similarWords);
		log.addToLog("\n�������±���ֳ���˫�ִ���ĿΪ��" + DeconstructedNum);
		log.addToLog("\n�����ʣ�" + similar);

	}
}