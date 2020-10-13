package cn.program2;

import java.io.IOException;
import java.util.*;
import cn.program2.Logfile;

public class ProductHomework  {
	private static Random random = new Random();
	public static int range;
	
	public static String reductionFraction(int a, int b) {// 分数约分，用于计算结果
		int commonFactor = 1; //公因数
		int smaller=a>b?b:a;
		      for(int i=1; i<=smaller; i++) {
		       if (a%i==0 && b%i==0) {
		        	 commonFactor=i;      
		       }
		    } 
		int numerator = a/commonFactor; //分子
		int denominator = b/commonFactor; //分母
		if(numerator==0) { //如果分子为0
		     return "0";
		}
		if(denominator==1) { //如果分母为1
		     return numerator+"";
		} else {
		      return expression(numerator, denominator);
		}
	}
	
	public static String expression(int a, int b) {//判断是否为假分数并将假分数化为带分数
		  if(a>=b) {
		   int roundNumber=a/b;
		   int newNumerator=a%b;//余数
		   if(newNumerator==0) {
		     return roundNumber+"";
		   }
		   if(roundNumber==0){
			  return a + "/" + b;
		   }
		   else return roundNumber + "'" + newNumerator + "/" + b;
	 }
	return a + "/" + b;  
	}
	
	//判断程序一次运行生成的题目是否重复
	public static boolean CheckRepetition(Set<String> ExercisesContainer,String s){
		if(ExercisesContainer.contains(s)){
			return true;
		}
		return false;
	}

	public static String[] ProductExercise(int maxSize,int num,String TopicfilePath) {
		//生成题目，并且把题目写出到目录下Exercises.txt
		Logfile TopicResult = new Logfile(TopicfilePath, "Exercises");
		String[] results=new String[num];
		Set<String> ExercisesContainer = new HashSet<String>();
		for( int i=0;i<num;i++){ 
			    String exc[]=new String[2];//存放原题和位置调换后的题目为同一道题的题目
		        int a= (int) (random.nextInt(maxSize));//分子
		    	int b= (int) (random.nextInt(maxSize));//分母
		    	int c= (int) (random.nextInt(maxSize));//另一个分子
		    	int d= (int) (random.nextInt(maxSize));//另一个分母
		    	int Symbol=(int) (random.nextInt(4)+1);//随机产生运算符
		    	if(b!=0&&d!=0){//分母均不为0时生成带有分数的计算题，同时计算结果
		    		if(Symbol==1){
		    	  		  if(((a*d-b*c>=0)&&b*d>0)||((a*d-b*c<0)&&b*d<0)){
		    	  			  int numerator=a*d+b*c;
		    	  	    	  int denominator=b*d;
		    	  	    	  exc[0]=expression(a,b)+'+'+expression(c,d)+'=';
		    	  	    	  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  	    	  
		    	  		  }
		    	  		  else{ 
		    	  			i--;
		    	  		  }
		    	  	  }
		    	  	  if(Symbol==2) {
		    	  		  if(((a*d-b*c>=0)&&b*d>0)||((a*d-b*c<0)&&b*d<0)){
		    	  			  int numerator=a*d-b*c;
		    	  	    	  int denominator=b*d;
		    	  	    	  exc[0]=expression(a,b)+'-'+expression(c,d)+'=';
		    	  	    	  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }	
		    	  		  else{
		    	  			i--;
		    	  		  }
		    	  	  }
		    	  	  if(Symbol==3) {
		    	  		  if(((a*d-b*c>=0)&&b*d>0)||((a*d-b*c<0)&&b*d<0)){
		    	  			  int numerator=a*c;
		    	  	    	  int denominator=b*d;
		    	  	    	  exc[0]=expression(a,b)+'×'+expression(c,d)+'=';
		    	  	    	  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }
		    	  		  else{ 
		    	  			i--;
		    	  		  }
		    	  	  }
		    	  	  if(Symbol==4) {
		    	  		  if(((a*d>=0&&b*c>0)||(a*d<0&&b*c<0))&&c!=0){
		    	  			  int numerator=a*d;
		    	  			  int denominator=b*c;
		    	  			  exc[0]=expression(a,b)+'÷'+expression(c,d)+'=';
		    	  			  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }
		    	  		  else{
		    	  			  i--;
		    	  		  } 
		    	  	  }	
		    	}
		    	else if(b==0&&d!=0){//如果分母b为0，则把b换为1，让a/b直接为整数a
		    		b=1;
		    		if(Symbol==1){
		    	  		  if(((a*d-b*c>=0)&&b*d>0)||((a*d-b*c<0)&&b*d<0)){
		    	  			  int numerator=a*d+b*c;
		    	  	    	  int denominator=b*d;
		    	  	    	  exc[0]=expression(a,b)+'+'+expression(c,d)+'=';
		    	  	    	  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }
		    	  		  else{ 
		    	  			i--;
		    	  		  }
		    	  	  }
		    	  	  if(Symbol==2) {
		    	  		  if(((a*d-b*c>=0)&&b*d>0)||((a*d-b*c<0)&&b*d<0)){
		    	  			  int numerator=a*d-b*c;
		    	  	    	  int denominator=b*d;
		    	  	    	  exc[0]=expression(a,b)+'-'+expression(c,d)+'=';
		    	  	    	  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }	
		    	  		  else{
		    	  			i--;
		    	  		  }
		    	  	  }
		    	  	  if(Symbol==3) {
		    	  		  if(((a*d-b*c>=0)&&b*d>0)||((a*d-b*c<0)&&b*d<0)){
		    	  			  int numerator=a*c;
		    	  	    	  int denominator=b*d;
		    	  	    	  exc[0]=expression(a,b)+'×'+expression(c,d)+'=';
		    	  	    	  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }
		    	  		  else{ 
		    	  			i--;
		    	  		  }
		    	  	  }
		    	  	  if(Symbol==4) {
		    	  		  if(((a*d>=0&&b*c>0)||(a*d<0&&b*c<0))&&c!=0){
		    	  			  int numerator=a*d;
		    	  			  int denominator=b*c;
		    	  			  exc[0]=expression(a,b)+'÷'+expression(c,d)+'=';
		    	  			  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }
		    	  		  else{
		    	  			  i--;
		    	  		  } 
		    	  	  }	
		    	}
		    	else if(b!=0&&d==0){//如果分母d为0，则把d换为1，让c/d直接为整数c
		    		d=1;
		    		if(Symbol==1){
		    	  		  if(((a*d-b*c>=0)&&b*d>0)||((a*d-b*c<0)&&b*d<0)){
		    	  			  int numerator=a*d+b*c;
		    	  	    	  int denominator=b*d;
		    	  	    	  exc[0]=expression(a,b)+'+'+expression(c,d)+'=';
		    	  	    	  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }
		    	  		  else{ 
		    	  			i--;
		    	  		  }
		    	  	  }
		    	  	  if(Symbol==2) {
		    	  		  if(((a*d-b*c>=0)&&b*d>0)||((a*d-b*c<0)&&b*d<0)){
		    	  			  int numerator=a*d-b*c;
		    	  	    	  int denominator=b*d;
		    	  	    	  exc[0]=expression(a,b)+'-'+expression(c,d)+'=';
		    	  	    	  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }	
		    	  		  else{
		    	  			i--;
		    	  		  }
		    	  	  }
		    	  	  if(Symbol==3) {
		    	  		  if(((a*d-b*c>=0)&&b*d>0)||((a*d-b*c<0)&&b*d<0)){
		    	  			  int numerator=a*c;
		    	  	    	  int denominator=b*d;
		    	  	    	  exc[0]=expression(a,b)+'×'+expression(c,d)+'=';
		    	  	    	  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }
		    	  		  else{ 
		    	  			i--;
		    	  		  }
		    	  	  }
		    	  	  if(Symbol==4) {
		    	  		  if(((a*d>=0&&b*c>0)||(a*d<0&&b*c<0))&&c!=0){
		    	  			  int numerator=a*d;
		    	  			  int denominator=b*c;
		    	  			  exc[0]=expression(a,b)+'÷'+expression(c,d)+'=';
		    	  			  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }
		    	  		  else{
		    	  			  i--;
		    	  		  } 
		    	  	  }	
		    	}
		    	else{//如果分母b为0，则把b换为1，让a/b直接为整数a;如果分母d为0，则把d换为1，让c/d直接为整数c
		    		b=1;
		    		d=1;
		    		if(Symbol==1){
		    	  		  if(((a*d-b*c>=0)&&b*d>0)||((a*d-b*c<0)&&b*d<0)){
		    	  			  int numerator=a*d+b*c;
		    	  	    	  int denominator=b*d;
		    	  	    	  exc[0]=expression(a,b)+'+'+expression(c,d)+'=';
		    	  	    	  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }
		    	  		  else{ 
		    	  			i--;
		    	  		  }
		    	  	  }
		    	  	  if(Symbol==2) {
		    	  		  if(((a*d-b*c>=0)&&b*d>0)||((a*d-b*c<0)&&b*d<0)){
		    	  			  int numerator=a*d-b*c;
		    	  	    	  int denominator=b*d;
		    	  	    	  exc[0]=expression(a,b)+'-'+expression(c,d)+'=';
		    	  	      	  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }	
		    	  		  else{
		    	  			i--;
		    	  		  }
		    	  	  }
		    	  	  if(Symbol==3) {
		    	  		  if(((a*d-b*c>=0)&&b*d>0)||((a*d-b*c<0)&&b*d<0)){
		    	  			  int numerator=a*c;
		    	  	    	  int denominator=b*d;
		    	  	    	  exc[0]=expression(a,b)+'×'+expression(c,d)+'=';
		    	  	    	  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }
		    	  		  else{ 
		    	  			i--;
		    	  		  }
		    	  	  }
		    	  	  if(Symbol==4) {
		    	  		  if(((a*d>=0&&b*c>0)||(a*d<0&&b*c<0))&&c!=0){
		    	  			  int numerator=a*d;
		    	  			  int denominator=b*c;
		    	  			  exc[0]=expression(a,b)+'÷'+expression(c,d)+'=';
		    	  			  exc[1]=expression(c,d)+'+'+expression(a,b)+'=';
		    	  	    	  if(false==(CheckRepetition(ExercisesContainer,exc[0]))){
		    	  	    		  //如果生成的题目与题库里的不相同才生成，否则继续生成随机数产生新的题目
		    	  	    		ExercisesContainer.add(exc[0]);
			    	  	    	ExercisesContainer.add(exc[1]);
			    	  	    	Logfile.addToLog("四则运算题目"+(i+1)+"："+exc[0]);
			    	  	    	results[i]=reductionFraction(numerator, denominator);
		    	  	    	  }
		    	  	    	  else{
		    	  	    		  i--;
		    	  	    	  }
		    	  		  }
		    	  		  else{
		    	  			  i--;
		    	  		  } 
		    	  	  }	
		    	}
		}
		return results;
	}
	
	public static boolean IsInt(String str){//判断字符串能否转化为int型
		try{
			Integer.valueOf(str);
			return true;
		}catch(Exception e){
			return false;
		}	
		
	}
		
    public static void main(String[] args) throws IOException{
    	String maxSizeString; // 数值允许的最大值字符串
		String numString; // 需要生成题目的个数字符串
		String[] parms; // 接受参数

		if (args.length != 0) {
			parms = args;
		} else {
			System.out.println("请输入题目中数值允许的最大值(格式：-r 范围)，请输入需要生成题目的个数(格式：-n 个数)(参数之间逗号隔开)");
			Scanner scanner = new Scanner(System.in);
			parms = scanner.nextLine().split(",");
			if (parms.length < 2) {
				System.out.println("输入的内容不符合要求，请重新输入哦(`-w-`)");
				System.exit(0);
			}
			scanner.close();
		}

		maxSizeString = parms[0]; 
		numString=parms[1];
    	
        String str1 = maxSizeString.substring(3);
        String str2 = numString.substring(3);
        int maxSize=0; 
        int num=0;
        if(true==IsInt(str1)){
    	    maxSize=Integer.parseInt(str1);
        }
        else{
    	    System.out.println("小朋友你输入题目范围的格式有误，请重新输入(`-w-`)");
        }
        if(true==IsInt(str2)){
    	    num=Integer.parseInt(str2);
        } 
        else{
    	    System.out.println("小朋友你输入题目个数的格式有误，请重新输入(`-w-`)");
        }

        String ResultfilePath="C://Users//HASEE//workspace//Arithmetic//src//cn//program2";
        String TopicfilePath="C://Users//HASEE//workspace//Arithmetic//src//cn//program2";
        String GradefilePath="C://Users//HASEE//workspace//Arithmetic//src//cn//program2";
	    String[] results=ProductExercise(maxSize,num,TopicfilePath);
	    Logfile logResult = new Logfile(ResultfilePath, "Answers");
	    for(int i=0;i<num;i++){
		    logResult.addToLog("答案"+(i+1)+"："+results[i]);
		    System.out.println("答案"+(i+1)+"："+results[i]);
	    }
      }
   }

