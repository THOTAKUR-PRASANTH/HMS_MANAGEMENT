package Finial.HMS.ADMIN_MODULE.EMAIL;

import Finial.HMS.ADMIN_MODULE.AdminEntity;
import Finial.HMS.ADMIN_MODULE.AdminRepository;
import Finial.HMS.DOCTOR_MODULE.DoctorEntity;
import Finial.HMS.DOCTOR_MODULE.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

@Component
public class OtpGeneration {

    @Autowired
    private EmailEntityRepo repo;

    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private DoctorRepository docRepo;

    @Autowired
    private ForgetMailSender mailSender;


    public  boolean sentSucess(String email,Object entity)
    {
        Random rand = new Random();
        int min = 100000;
        int max = 999999;
        int otp = rand.nextInt((max - min) + 1) + min;

        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setEmail(email);
        emailEntity.setOTP(otp);

        if(entity instanceof AdminEntity){
                AdminEntity adminEntity = (AdminEntity)entity;
            if(adminEntity!=null)
                emailEntity.setAdminEntity(adminEntity);
        }
        if(entity instanceof DoctorEntity){
            DoctorEntity doctorEntity = (DoctorEntity)entity;
            if(doctorEntity!=null)
                emailEntity.setDoctorEntity(doctorEntity);
        }

        emailEntity.setTime(new Date(System.currentTimeMillis() + 70 * 1000));
        repo.save(emailEntity);
        mailSender.ForgetPassword(emailEntity);

        return true;
    }


    public boolean  otpgeneration(String email) {

        boolean check = false;
        if(adminRepo.existsByemail(email)) {
            AdminEntity adminEntity = adminRepo.findByemail(email);
            check = sentSucess(email,adminEntity);
        }
        else if(docRepo.existsByemail(email)) {
            DoctorEntity doctorEntity = docRepo.findByemail(email);
            check = sentSucess(email,doctorEntity);
        }
        else
            check = false ;
        return check;
    }


    public boolean otpVerification(String email, Integer otp) {
        EmailEntity emailEntity1 = repo.findByOTPAndEmail(otp, email);

        if (emailEntity1 == null) {
            repo.deleteByEmail(email);
            return false;
        }

        if(emailEntity1.getOTP().equals(otp)){
            repo.deleteByOTP(emailEntity1.getOTP());
            return true;}


        if (emailEntity1.getTime() == null || emailEntity1.getTime().before(Date.from(Instant.now()))) {
            repo.deleteByOTP(emailEntity1.getOTP());
            System.err.println("invalid otp");
            return false;
        }

        repo.deleteByOTP(emailEntity1.getOTP());
        return true;
    }
}
