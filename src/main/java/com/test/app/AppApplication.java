package com.test.app;

import com.test.app.entity.Role;
import com.test.app.entity.User;
import com.test.app.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }


//    @Bean
//    CommandLineRunner runner(PasswordEncoder encoder, UserRepo repo) {
//        return args -> {
//            var arr = Arrays.asList(
//                    User.builder()
//                            .name("Клавдия, сука, ивановна")
//                            .login("user")
//                            .passwordHash(encoder.encode("1324"))
//                            .role(new Role("ADMIN"))
//                            .build(),
//                    User.builder()
//                            .name("Сучка, сука, ивановна")
//                            .login("user2")
//                            .passwordHash(encoder.encode("1324"))
//                            .role(new Role("TEACHER"))
//                            .build(),
//                    User.builder()
//                            .name("Клавдия, сука, ивановна")
//                            .login("user3")
//                            .passwordHash(encoder.encode("1324"))
//                            .role(new Role("CANTEEN"))
//                            .build(),
//                    User.builder()
//                            .name("Клавдия, сука, ивановна")
//                            .login("user4")
//                            .passwordHash(encoder.encode("1324"))
//                            .role(new Role("TEACHER"))
//                            .build());
//
//            repo.saveAll(arr);
//
//
//        };
//    }

}
