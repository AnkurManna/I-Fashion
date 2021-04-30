package mystore.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mystore.domainmodel.models.*;
import mystore.service.repository.*;


@RestController
public class RegisterController {
	
	@Autowired
	UserRepository repository;
	
	@PostMapping("/adduser")
	public String saveItem(@RequestBody User user)
	{
		//Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
		/*Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
		CharSequence x = user.getPassword();
		String password = encoder.encode(x);
				
		user.setPassword(password);*/
		repository.save(user);
		/*System.out.print(book.getId());
		System.out.print(book.getAuthorName());*/
		return "user added";
	}
}
