package Finial.HMS.DOCTOR_MODULE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    DoctorEntity findByemail(String email);

    boolean existsByemail(String email);
}
