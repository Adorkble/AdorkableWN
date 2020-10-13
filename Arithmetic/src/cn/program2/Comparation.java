package cn.program2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import cn.program2.Logfile;

public class Comparation {
	private static void readFile(File StudentAnswers,File Answers,String Gradepath) throws IOException {
//    	将标准答案与学生答案对比，生成Grade.txt
	   FileInputStream san = new FileInputStream(StudentAnswers);
	   FileInputStream an = new FileInputStream(Answers);
	   BufferedReader brStudent = new BufferedReader(new InputStreamReader(san));
	   BufferedReader br2 = new BufferedReader(new InputStreamReader(an));
	    
	   Collection<Integer> Correctlist=new ArrayList<>();
       Collection<Integer> Wronglist=new ArrayList<>();
	   String lineStudent = null;
	   String line = null;
	   int CorrectCount=0; 
	   int WrongCount=0;
	   int count=0;
	   while (((lineStudent = brStudent.readLine()) != null)&&((line = br2.readLine()) != null)) {
		    if(lineStudent.equals(line)){
			   CorrectCount++;
			   count++;
			   Correctlist.add(count);
		    }
		    else {
		    	WrongCount++;
			    count++;
			    Wronglist.add(count);
		    }
	   }
	 
	   Logfile logGrade = new Logfile(Gradepath, "Grade");
	   Logfile.addToLog("Correct："+CorrectCount+" "+Correctlist.toString());
	   Logfile.addToLog("Wrong："+WrongCount+" "+Wronglist.toString());
	   brStudent.close();
	   san.close();
	   br2.close();
	   an.close();  
   }
	public static void main(String[] args) throws IOException {
		String AnswerPath; // 标准答案路径
		String StudentAnswerPath; // 学生答案路径
		String GradePath; // 得分文件生成路径
		String[] parms; // 接受参数

		if (args.length != 0) {
			parms = args;
		} else {
			System.out.println("请给出标准答案路径，你的答案绝对路径，输出得分文件的绝对路径(参数之间空格隔开，格式如下E:\\xxx\\xxx.txt)");
			Scanner scanner = new Scanner(System.in);
			parms = scanner.nextLine().split(" ");
			
			if (parms.length < 3) {
				System.out.println("输入的内容不符合要求，请重新输入！");
				System.exit(0);
			}
			scanner.close();
		}

		AnswerPath = parms[0]; 
		StudentAnswerPath=parms[1];
		GradePath = parms[2];
		
		File file1 = new File(AnswerPath);
		File file2 = new File(StudentAnswerPath);
//		C://Users//HASEE//workspace//Arithmetic//src//cn//program2//Answers.txt
//		C://Users//HASEE//workspace//Arithmetic//src//cn//program2//StudentAnswers.txt
//		C://Users/HASEE//workspace//Arithmetic//src//cn//program2
		
		readFile(file1,file2,parms[2]);
			
	}
}
