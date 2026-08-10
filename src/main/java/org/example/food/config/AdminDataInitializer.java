package org.example.food.config;

import org.example.food.model.Admin;
import org.example.food.model.Role;
import org.example.food.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminDataInitializer
        implements CommandLineRunner {

    private static final String DEFAULT_USERNAME =
            "admin";

    private static final String DEFAULT_PASSWORD =
            "admin123";

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    public AdminDataInitializer(
            AdminRepository adminRepository,
            PasswordEncoder passwordEncoder
    ) {

        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (adminRepository.count() == 0) {

            Admin admin = new Admin();

            admin.setFullName("Super Admin");

            admin.setUsername(
                    DEFAULT_USERNAME
            );

            admin.setPassword(
                    passwordEncoder.encode(
                            DEFAULT_PASSWORD
                    )
            );

            admin.setRole(
                    Role.SUPER_ADMIN
            );

            admin.setEnabled(true);

            adminRepository.save(admin);
        }
    }
}