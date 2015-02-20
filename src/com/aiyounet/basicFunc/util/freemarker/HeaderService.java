package com.aiyounet.basicFunc.util.freemarker;


/** 
 * @author ajun 
 * @http://blog.csdn.net/ajun_studio   
 **/  
public class HeaderService {  
  
    private static Header h = new Header();  
      
    static{  
        h.setAddress("北京朝阳CBD");  
        h.setCompanyName("上海唐秀！！！");  
    }  
      
    public static void update(String address,String companyName){  
        h.setAddress(address);  
        h.setCompanyName(companyName);  
    }  
      
    public static Header getHeader(){  
        return h;  
    }  
}  