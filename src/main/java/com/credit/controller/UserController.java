package com.credit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credit.model.User;
import com.credit.repository.UserRepository;

/*All api's related to User in this controller */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("rest")
public class UserController {
	int  code;
	
	
	
	@Autowired
	UserRepository userRepository;
	

	
	Map<Integer,Integer> map = new HashMap<>();
	
	@PostMapping("/create-user")
	public User createUser(@RequestBody User user) {
		//go to repo(User) and use save method to insert in DB
		System.out.println("User just saved is : " + userRepository.save(user));
		return userRepository.save(user);
	}
	
	
	@GetMapping("/get-all-users")
	public List<User> getUsers() {
		//go to repo and fetch all users 
		return userRepository.findAll();
	}
	@GetMapping("/get-one-user/{id}")
	public User getUser(@PathVariable("id") int id) {
		//go to repo and fetch user based on id.
		return userRepository.getOne(id);
	}

	
	@GetMapping("/transfer-credit/{idFrom}/{idTo}/{credit}")
	public String transferCredit(@PathVariable("idFrom") int idFrom,@PathVariable("idTo") int idTo, @PathVariable("credit") int credit) {
		//go to repo and fetch existing user based on id
		int flag=0;
		
		User u1 = userRepository.getOne(idFrom);
		User u2 = userRepository.getOne(idTo);
		if(Integer.valueOf(u1.getCredit())<=0) {
			flag=1;
		}
		else if(Integer.valueOf(u1.getCredit()) < credit)
		{
			flag=2;
		}
			
		else {
			u1.setCredit(Integer.toString(Integer.valueOf(u1.getCredit())-credit));
			
			u2.setCredit(Integer.toString(Integer.valueOf(u2.getCredit())+credit));
			//save u in repo
			userRepository.save(u1);
			userRepository.save(u2);
		}
		
		if(flag==1) { 
			return "No Balance";
		}
		else if(flag==2) {
return "Insufficient Credits : More " +( credit - Integer.valueOf(u1.getCredit())) + " Required";
		}
		else {
			if(credit<10)
			return "Successfully Transfered " + credit + " credit from " + u1.getName() + " To " + u2.getName();
			else
			return "Successfully Transfered " + credit + " credits from " + u1.getName() + " To " + u2.getName();
				
		}
	}
	
	@DeleteMapping("/delete-user/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		//go to repo and delete based on id
		userRepository.deleteById(id);
	}
	
	
	

}











//
//for(Map.Entry<Integer,Integer> e : map.entrySet()){
//	if(e.getKey()== user.getId()){
//		if(e.getValue()==code){
//			map.remove(user.getId());
//			return true;
//		}
//	}
//}
//return false;



