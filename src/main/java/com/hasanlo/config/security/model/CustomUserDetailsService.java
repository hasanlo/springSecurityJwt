package com.hasanlo.config.security.model;


import com.hasanlo.entity.User;
import com.hasanlo.repository.UserRepository;
import com.hasanlo.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	private final UserRepository userRepository;
	private final UserRolesRepository userRolesRepository;

	@Autowired
    public CustomUserDetailsService(UserRepository userRepository,UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository=userRolesRepository;
    }

	public UserDetails loadUserByUsername(String username) {
		User user=userRepository.findByUserName(username);
		if(null == user){
			throw new UsernameNotFoundException("No user present with username: "+username);
		}else{
			List<String> userRoles=userRolesRepository.findRoleByUserUserName(username);
			return new CustomUserDetails(user,userRoles);
		}
	}

}
