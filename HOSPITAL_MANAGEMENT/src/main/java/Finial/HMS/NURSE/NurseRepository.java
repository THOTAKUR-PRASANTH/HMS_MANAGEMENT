package Finial.HMS.NURSE;

import Finial.HMS.ADMIN_MODULE.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface NurseRepository extends JpaRepository<AdminEntity, Long> {

    AdminEntity findByemail(String email);

    boolean existsByemail(String email);
}
