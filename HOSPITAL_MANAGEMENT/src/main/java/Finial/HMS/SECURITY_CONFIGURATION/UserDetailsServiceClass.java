package Finial.HMS.SECURITY_CONFIGURATION;

import Finial.HMS.ADMIN_MODULE.AdminEntity;
import Finial.HMS.ADMIN_MODULE.AdminRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
@Data
@Slf4j
public class UserDetailsServiceClass implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AdminEntity adminEntity = adminRepository.findByemail(email);
        if (adminEntity != null) {
            List<GrantedAuthority> authorities = Collections.singletonList(
                    new SimpleGrantedAuthority(adminEntity.getRole())
            );
            return new User(adminEntity.getEmail(), adminEntity.getPassword(), authorities);
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}

