package com.example.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
	
	@GetMapping("/login")
	public void login(@RequestParam("mail") String mail,@RequestParam("password") String password,HttpServletResponse response)
	{
		System.out.print(password);
		if(password.equals("admin123"))
		{
			Cookie ck = new Cookie("loggedIn",mail);
			ck.setMaxAge(1200);
			System.out.print("injecting cookie");
			response.addCookie(ck);
		}
	}
	
	
	@GetMapping("/check")
	public void check(HttpServletRequest req)
	{
	    Cookie ck[]=req.getCookies();  
	    try
	    {
		    for(int i=0;i<ck.length;i++){  
			     System.out.println(ck[i].getName()+" "+ck[i].getValue());//printing name and value of cookie  
			    }  
	    }
	    catch(Exception e) {
	    	System.out.println("zero cookie");
	    }

	}
	
	@GetMapping("/session")
    public int count(HttpSession session) {

        Integer counter = (Integer) session.getAttribute("count");

        if (counter == null) {
            counter = 1;
        } else {
            counter++;
        }

        session.setAttribute("count", counter);
        /*
         * The _id is a UUID that will be Base64-encoded by the DefaultCookieSerializer and set as a value in the SESSION cookie. 
         * Also, note that the attr attribute contains the actual value of our counter.
         * https://www.baeldung.com/spring-session-mongodb
         * */

        return counter;
    }
	
}
