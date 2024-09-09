package Finial.HMS.ADMIN_MODULE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    AdminEntity findByemail(String email);
    void deleteByEmail(String email);
    boolean existsByemail(String email);
}
