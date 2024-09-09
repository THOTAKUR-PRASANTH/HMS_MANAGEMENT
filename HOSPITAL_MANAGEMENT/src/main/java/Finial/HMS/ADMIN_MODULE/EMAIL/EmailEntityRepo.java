package Finial.HMS.ADMIN_MODULE.EMAIL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface EmailEntityRepo  extends JpaRepository<EmailEntity, Long> {

    void deleteByOTP(Integer otp);

    EmailEntity findByOTPAndEmail(Integer otp, String email);
    void deleteByEmail(String email);
}
