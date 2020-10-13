package cn.program2;

import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;  
  
public class Logfile {  
    //日志保存路径  
    private String filePath = null;  
    //日志名称  
    private String fileName = null;  
      
    private static BufferedWriter bw;  
      
    private File logFile;  
       
    //日志功能是否开启  
    public static boolean state = false;  
      
    public Logfile(String filePath,String fileName){  
        this.filePath = filePath;  
        this.fileName = fileName;  
        logFile = new File(filePath,fileName+".txt");  
        start();  
        this.state = true;  
    }  
      
    private void start(){  
        try {  
            bw = new BufferedWriter(new FileWriter(logFile));  
            //System.out.println("日志创建成功");  
        } catch (IOException e) {  
            System.out.println("日志创建失败，请检查指定目录与文件名是否正确");  
        }  
    }  
    /**
     * 写入日志信息，为了保证文件信息正确，需要同步 
     * 开启日志功能则可正常写入信息 
     */  
    public static synchronized void addToLog(String info){  
        if(state)  
        {  
            try {  
                bw.write(info);  
                bw.newLine();  
                bw.flush();  
            } catch (IOException e) {  
                System.out.println("信息录入失败");  
            }  
        }  
    }  
}  