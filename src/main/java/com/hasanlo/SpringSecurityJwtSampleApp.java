package com.hasanlo;


import com.hasanlo.entity.User;
import com.hasanlo.entity.UserRole;
import com.hasanlo.repository.UserRepository;
import com.hasanlo.repository.UserRolesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

@SpringBootApplication
public class SpringSecurityJwtSampleApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtSampleApp.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository
            , UserRolesRepository userRolesRepository) {
        return (args) -> {

            User user = new User("user1", new ShaPasswordEncoder(256).encodePassword("123456", null)
                    , "milad.hasanlo@gmail.com", true);
            userRepository.save(user);
            userRolesRepository.save(new UserRole("ROLE_USER", user));


            System.out.println(userRolesRepository.findRoleByUserUserName("user1"));
        };
    }
}
