package com.revature.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.JwtUserIsAdmin;
import com.revature.annotations.JwtUserIsSelf;
import com.revature.annotations.JwtUserIsSelfOrAdmin;
import com.revature.annotations.JwtVerify;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.utils.ResponseMap;
@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@GetMapping()
	//@JwtUserIsAdmin
	public ResponseEntity<Map<String,Object>> findAll(){
		List<User> userList=  userService.findAll();
		System.out.println(userList);
		if (userList == null) {
			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("No users found."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(userList,"Here's all your users."));
	}
	
	//need to change this to unique end point
	@GetMapping("id/{id}")
	//Might need to change?
	//@JwtUserIsSelf
	public ResponseEntity<Map<String,Object>> findOneById(@PathVariable int id){
		User user =  userService.findOneById(id);
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
	public ResponseEntity<Map<String,Object>> userInfo(HttpServletRequest req){
		User user =  userService.userInfo(req);
		if (user == null) {
			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("User not found."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(user,"Here is your users."));
	}
	
	
	@GetMapping("cohorts/{id}")
	public ResponseEntity<Map<String,Object>> findAllByCohortId(@PathVariable int id){
		List<User> userList=  userService.findAllByCohortId(id);
		if (userList == null) {
			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Users not found."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(userList,"Here is your users."));
	}
	
	
	@PostMapping()
	public ResponseEntity<Map<String,Object>> saveUser(@RequestBody User u){
		User user = userService.saveUser(u);
	    //UserDto or JSON ignore
		
	    if (u == null) {
			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Users not saved."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(user,"Saved user"));
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String,Object>> login(@RequestBody User u){
		Map<String,Object>  userJwtMap =  userService.login(u);
	    //UserDto or JSON ignore
		
	    if (userJwtMap == null) {
			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Users not saved."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(userJwtMap,"Saved user"));
	}
	
	@PatchMapping("update/profile")
	public ResponseEntity<Map<String,Object>> updateProfile(@RequestBody User u){
	    User user =  userService.updateProfile(u);
	    //UserDto or JSON ignore
		
	    if (user == null) {
			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Users not saved."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(user,"Saved user"));
	}
}
