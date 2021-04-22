package mystore.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@SuppressWarnings("deprecation")
@Component
public class ItemAccessInterceptor extends HandlerInterceptorAdapter{
	
	 @Override
	    public boolean preHandle(
	      HttpServletRequest req, 
	      HttpServletResponse response, 
	      Object handler) {
		 
		 System.out.println("in prehandle");
		 if(req.getMethod().equals("OPTIONS"))
			 return true;
		 
		 Cookie ck[]=req.getCookies();  
		    try
		    {
			    for(int i=0;i<ck.length;i++){  
				     //System.out.println(ck[i].getName()+" "+ck[i].getValue());//printing name and value of cookie  
			    		if(ck[i].getName().equals("loggedIn"))
			    			{
			    			System.out.println("cookie present");
			    			return true;
			    			}
			    }  
		    }
		    catch(Exception e) {
		    	System.out.println("zero cookie");
		    }
		    System.out.println("no cookie");
		    
		    return false;

	        
	    }
	 
	 @Override
	    public void afterCompletion(
	      HttpServletRequest request, 
	      HttpServletResponse response, 
	      Object handler, 
	      Exception ex) {
	        //
		 
		 System.out.println(response);
	    }
	
}
