package com.revature.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.CognitoAuth;
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
	private CognitoUtil cUtil;

	Logger log = Logger.getRootLogger();

	@CognitoAuth(role = "user")
	@GetMapping()
	public ResponseEntity<Map<String, Object>> findAll() {
		List<User> userList = userService.findAll();
		String emailTest = cUtil.extractTokenEmail();
		System.out.println(emailTest);
		if (userList == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("No users found."));
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(userList, "Here's all your users."));
	}

	// need to change this to unique end point
	@GetMapping("id/{id}")
	@CognitoAuth(role = "user")
//	@Logging()
	// Might need to change?
	public User findOneById(@PathVariable int id) {
		User user = userService.findOneById(id);
		 
		return user;
	}

	@GetMapping("email/{email}/")
	@CognitoAuth(role = "user")
	public ResponseEntity<Map<String, Object>> findOneByEmail(@PathVariable String email) {
		email.toLowerCase();
		User user = userService.findOneByEmail(email);
		if (user == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("User not found."));
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(user, "Here is your users."));
	}

//////////////////////////////////////////////////////////////////////////////////////////////////

	// FINDING USER BY USERNAME. DELETE IF NOT NEEDEED - AN THANH TA
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
	@CognitoAuth(role = "user")
	public ResponseEntity<Map<String, Object>> userInfo(HttpServletRequest req) {
		User user = userService.userInfo(req);
		if (user == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("User not found."));
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(user, "Here is your users."));
	}

	@GetMapping("cohorts/{id}")
	@CognitoAuth(role = "user")
//	@Logging()
	public ResponseEntity<Map<String, Object>> findAllByCohortId(@PathVariable int id) {
		List<User> userList = userService.findAllByCohortId(id);
		if (userList == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Users not found."));
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(userList, "Here is your users."));
	}

	@PostMapping()
	@CognitoAuth(role = "user")
	public ResponseEntity<Map<String, Object>> saveUser(@RequestBody User u, HttpServletRequest req)
			throws IOException, URISyntaxException {
		User checkUser = userService.findOneByUsername(u.getUsername());
		User tempUser = null;
		String error = "";
		if (checkUser == null) {
			if (cUtil.registerUser(u.getEmail(), req)) {
				tempUser = userService.saveUser(u);
				return tempUser != null ? ResponseEntity.ok().body(ResponseMap.getGoodResponse(tempUser, "Saved user"))
						: ResponseEntity.badRequest().body(ResponseMap.getBadResponse("user cannot be saved"));
			}
		}
		error = (checkUser != null) ? "User already in database" : "User could not register for cognito";
		return ResponseEntity.badRequest().body(ResponseMap.getBadResponse(error));

	}

	@PostMapping("emails")
	@CognitoAuth(role = "staging-manager")
	public ResponseEntity<Map<String, Object>> saveUserFromEmails(@RequestBody String[] emailListArray,
			HttpServletRequest req) throws IOException, URISyntaxException {

		List<String> emailList = Arrays.asList(emailListArray);
		List<String> rejectedEmails = new ArrayList<>();

		for (String email : emailList) {
			if (userService.findOneByEmail(email) == null) {
				if (cUtil.registerUser(email, req)) {
					if (userService.saveUser(new User(null, email, null, null, null)) != null) {
						log.info("\\n Email " + email + " succesfully saved.");
						continue;
					} else {
						log.info("\n User could not be succesfully saved");
					}
				} else {
					log.info("\n Email could not be registered for cognito");
				}
			} else {
				log.info("\n User with email already exists");
			}
			log.info("\n Email " + email + " rejected");
			rejectedEmails.add(email);
			emailList.remove(email);

		}
		if (rejectedEmails.size() == 0) {
			return ResponseEntity.ok()
					.body(ResponseMap.getGoodResponse(emailList, "All emails were succesfully saved."));
		} else {
			Map<String, Object> emailMap = new HashMap<>();
			emailMap.put("succesfulEmails", emailList);
			emailMap.put("rejectedEmails", rejectedEmails);
			return ResponseEntity.status(206)
					.body(ResponseMap.getGoodResponse(emailMap, "Some emails were not succesfully saved."));
		}

	}

	@PatchMapping("update/profile")
	@CognitoAuth(role = "user")
	public ResponseEntity<Map<String, Object>> updateProfile(@RequestBody User u) {
		User user = userService.updateProfile(u);
		// UserDto or JSON ignore

		if (user == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Users not saved."));
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(user, "Saved user"));
	}
}
