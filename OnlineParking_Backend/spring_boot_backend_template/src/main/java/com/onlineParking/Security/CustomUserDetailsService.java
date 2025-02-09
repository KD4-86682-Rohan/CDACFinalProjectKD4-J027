//package com.onlineParking.Security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.onlineParking.DTO.ApiResponse;
//import com.onlineParking.Dao.UserDao;
//import com.onlineParking.Pojos.User;
//
//@Service
//@Transactional
//public class CustomUserDetailsService implements UserDetailsService {
//	@Autowired
//	private UserDao userDao;
//
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		// invoke dao's method
//		User user = userDao.findByEmail(email).orElseThrow();
//		//=> user email exists - user : persistent
//		/*
//		 * In case of email found -- this method has to ret UserDetails object filled with details lifted from DB
//		 */
//		return new CustomUserDetails(user);
//	}
//
//}
