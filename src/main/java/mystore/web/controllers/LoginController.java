package mystore.web.controllers;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mystore.domainmodel.models.*;
import mystore.service.repository.*;


@RestController
public class LoginController {
	
	@Autowired
	UserRepository repository;
	
	@GetMapping("/login")
	public String login(@RequestParam("mail") String mail,@RequestParam("password") String password,HttpServletResponse response)
	{
		Optional<User> candidate = repository.findByMail(mail);
		if(candidate.isPresent())
		{
			User x = candidate.get();
			if(x.getPassword().equals(password))
			{
				Cookie ck = new Cookie("loggedIn",mail);
				Cookie gen = new Cookie("gender",candidate.get().getGender());
				ck.setMaxAge(12000);
				System.out.print("injecting cookie");
				response.addCookie(ck);
				response.addCookie(gen);
				return "logged In";
			}
			else
			{
				return "Invalid password";
			}
		}
		else
		{
			return "invalid credentials";
		}
		
	}
	@GetMapping("/logout")
	public void logout(HttpServletResponse response)
	{
		Cookie ck=new Cookie("loggedIn","");//deleting value of cookie  
		ck.setMaxAge(0);//changing the maximum age to 0 seconds  
		response.addCookie(ck);
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
