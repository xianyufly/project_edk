package com.aiyounet.basicFunc.util.freemarker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
    	  
        /** 
         * 初始化模板配置，供以后获得模板，在init里加载也主要是为保证Configuration实例唯一 
         */  
//            String templateDir = config.getInitParameter("templateDir");  
//            FreeMarkertUtil.initConfig(null);  
            //html生成之后存放的路径  
            File path = new File("/templates/html");  
            //生成的文件的名字  
            String indexFileName = "index.html";  
    
                Writer out = null;
				try {
					out = new OutputStreamWriter(new FileOutputStream("d:/"+indexFileName),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}  
                //生成html文件  
                ProcessClient.processBody(out);  
}
 
}