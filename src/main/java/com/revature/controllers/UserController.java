package com.revature.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.CognitoAuth;
import com.revature.dto.CognitoRegisterResponse;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.utils.CognitoUtil;
import com.revature.utils.ResponseMap;
@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private CognitoUtil iUtil;
	
	@CognitoAuth(highestRole="user")
	@GetMapping()
	public ResponseEntity<Map<String,Object>> findAll(){
		List<User> userList=  userService.findAll();
		String emailTest = iUtil.extractTokenEmail();
		System.out.println(emailTest);
		if (userList == null) {
			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("No users found."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(userList,"Here's all your users."));
	}
	
	//need to change this to unique end point
	@GetMapping("id/{id}")
	@CognitoAuth(highestRole="user")
//	@Logging()
	//Might need to change?
	public ResponseEntity<Map<String,Object>> findOneById(@PathVariable int id){
		User user =  userService.findOneById(id);
		if (user == null) {
			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("User not found."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(user,"Here is your users."));
	}
	
	@GetMapping("email/{email}/")
	@CognitoAuth(highestRole="user")
	public ResponseEntity<Map<String,Object>> findOneByEmail(@PathVariable String email){
		email.toLowerCase();
		User user =  userService.findOneByEmail(email);
		if (user == null) {
			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("User not found."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(user,"Here is your users."));
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////

	//FINDING USER BY USERNAME. DELETE IF NOT NEEDEED - AN THANH TA
//	@GetMapping("username/{username}")
//	public ResponseEntity<Map<String,Object>> findOneByUsername(@PathVariable String username){
//		User user =  userService.findOneByUsername(username);
//		if (user == null) {
//			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("User not found."));
//		}
//		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(user,"Here is your users."));
//	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////	
	
	@GetMapping("info/{info}")
	@CognitoAuth(highestRole="user")
	public ResponseEntity<Map<String,Object>> userInfo(HttpServletRequest req){
		User user =  userService.userInfo(req);
		if (user == null) {
			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("User not found."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(user,"Here is your users."));
	}
	
	
	@GetMapping("cohorts/{id}")
	@CognitoAuth(highestRole="user")
//	@Logging()
	public ResponseEntity<Map<String,Object>> findAllByCohortId(@PathVariable int id){
		List<User> userList=  userService.findAllByCohortId(id);
		if (userList == null) {
			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Users not found."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(userList,"Here is your users."));
	}
	
	
	@PostMapping()
	@CognitoAuth(highestRole="user")
	public ResponseEntity<Map<String,Object>> saveUser(@RequestBody User u) throws IOException, URISyntaxException{
		User checkUser = userService.findOneByUsername(u.getUsername());
		User tempUser = null;
		String error = "";
		if (checkUser == null) {
			if( iUtil.registerUser(u.getEmail())) {
				tempUser = userService.saveUser(u);
				return tempUser != null ?  
						ResponseEntity.ok().body(ResponseMap.getGoodResponse(tempUser,"Saved user")) : 
							ResponseEntity.badRequest().body(ResponseMap.getBadResponse("user cannot be saved"));
			}
		}
		error = (checkUser != null) ? "User already in database" : "User could not register for cognito";
		return ResponseEntity.badRequest().body(ResponseMap.getBadResponse(error)); 
	
	}
	
	
	@PatchMapping("update/profile")
	@CognitoAuth(highestRole="user")
	public ResponseEntity<Map<String,Object>> updateProfile(@RequestBody User u){
	    User user =  userService.updateProfile(u);
	    //UserDto or JSON ignore
		
	    if (user == null) {
			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Users not saved."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(user,"Saved user"));
	}
}
