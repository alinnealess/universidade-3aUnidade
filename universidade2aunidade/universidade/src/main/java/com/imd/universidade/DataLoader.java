package com.imd.universidade;

import com.imd.universidade.enums.UserRoles;
import com.imd.universidade.model.UserEntity;
import com.imd.universidade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            UserEntity admin = new UserEntity("admin", passwordEncoder.encode("admin"), UserRoles.ADMIN);
            repository.save(admin);
        }
    }
}
