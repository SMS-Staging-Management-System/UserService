package com.revature.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.models.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	Page<User> findAll(Pageable pageable);

	public List<User> findAllByCohortsCohortId(int id);

	public User findByEmailIgnoreCase(String email);
	
	public User findByEmail(String email);

	// The following Query method is specifically added for the
	// findUserByEmail method in UserController method that handels
	// path = "email/partial'(ss)
	@Query("FROM User user WHERE user.email LIKE %:email%")
	public Page<User> findUsersByEmailIgnoreCase(@Param("email") String email, Pageable pageable);

	@Query("FROM User user WHERE LOWER(user.email) IN :emailList")
	public Page<User> findAllUserByEmailIgnoreCase(@Param("emailList") List<String> emailList, Pageable pageable);

	@Query("FROM User user WHERE LOWER(user.email) IN :emailList")
	public List<User> findAllUserByEmailIgnoreCase(@Param("emailList") List<String> emailList);

	@Query("FROM User user WHERE LOWER(user.email) IN :emailList")
	public List<User> findAllUserByEmailIgnoreCaseNotPageable(@Param("emailList") List<String> emailList);
	
	@Query("FROM User WHERE userStatus = 9")
	public List<User> findByStatus();
	
	//Query to get all dropped associates in the last week
	@Query(value="select * from sms.sms_users left join sms.status_history on users_id = sms_user_id where status_start between now() - INTERVAL '168 HOURS' and now()", nativeQuery=true)
	public List<User> findAllDroppedAssociate();

	@Query(value = "select s.* from sms.sms_users s left outer join sms.status st on s.user_status = st.status_id where st.general_status ='Staging'", nativeQuery = true) 	
	public List<User> findAllInStaging();

}
