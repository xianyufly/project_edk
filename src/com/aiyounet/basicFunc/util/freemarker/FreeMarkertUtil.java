package com.aiyounet.basicFunc.util.freemarker;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkertUtil {

	private static  Configuration config = new Configuration(); 
	
	/**
	 * @param templateName 模板名字
	 * @param root 模板根 用于在模板内输出结果集
	 * @param out 输出对象 具体输出到哪里
	 */
	public static void processTemplate(String templateName, Map<?,?> root, Writer out){
		try{
			//获得模板
			Template template=config.getTemplate(templateName,"utf-8");
			//生成文件（out决定是生成html还是respone）
			template.process(root, out);   
			//控制台输出
//			template.process(root, new PrintWriter(System.out));
		    out.flush();   
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}finally{
			 try {
				out.close();
				out=null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 初始化模板配置
	 * @param servletContext javax.servlet.ServletContext
	 * @param templateDir 模板位置
	 */
	public static void initConfig(ServletContext servletContext,String templateDir){
		 	config.setLocale(Locale.CHINA);
		    config.setDefaultEncoding("utf-8");
		    config.setEncoding(Locale.CHINA, "utf-8");
		    config.setServletContextForTemplateLoading(servletContext, templateDir);
//		    config.setClassForTemplateLoading(ProcessClient.class, "/ftl");
		    config.setObjectWrapper(new DefaultObjectWrapper());
	}
	/**
	 * 初始化模板配置
	 * @param servletContext javax.servlet.ServletContext
	 * @param templateDir 模板位置
	 */
/*	public static void initConfig(String templateDir){
		 	config.setLocale(Locale.CHINA);
		    config.setDefaultEncoding("utf-8");
		    config.setEncoding(Locale.CHINA, "utf-8");
//		    config.setServletContextForTemplateLoading(servletContext, templateDir);
		    config.setClassForTemplateLoading(ProcessClient.class, "/ftl");
		    config.setObjectWrapper(new DefaultObjectWrapper());
	}*/
}
