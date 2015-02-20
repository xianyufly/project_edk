package com.aiyounet.basicFunc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

public class FileUtil {
	public static void copyInputStreamToFile(InputStream in,String folderName, String fileName){
		File folder = new File(folderName);
		if(!folder.exists()){
				folder.mkdir();
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(folderName,fileName));
			int c;
			byte buffer[]=new byte[1024];
			while((c=in.read(buffer))!=-1){
				for(int i=0;i<c;i++)
					fos.write(buffer[i]);        
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        
	}
}
