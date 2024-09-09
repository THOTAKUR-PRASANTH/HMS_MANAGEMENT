package Finial.HMS.ADMIN_MODULE;


import Finial.HMS.DOCTOR_MODULE.DoctorEntity;
import Finial.HMS.DOCTOR_MODULE.DoctorRepository;
import Finial.HMS.Users_Profiles_Images.Profiles_Images;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@Data
@Slf4j
public class Adminservice {

    @Autowired
    AdminRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private DoctorRepository doctorRepository;

    /*Adding_Admin*/
   public boolean addAdmin(String name, String email, String password, Integer age,
                           String gender, String phoneNumber, String address, String department, String position, MultipartFile file) throws IOException {
       if(name!=null && email!=null && password!=null && age!=null && gender!=null && phoneNumber!=null &&
               address!=null && department!=null && file!=null)
       {
           Profiles_Images profile = new Profiles_Images();
           profile.setImage1(file.getBytes());
           AdminEntity admin = new AdminEntity();
           admin.setName(name);
           admin.setEmail(email);
           admin.setPassword(passwordEncoder.encode(password));
           admin.setAge(age);
           admin.setGender(gender);
           admin.setPhoneNumber(phoneNumber);
           admin.setAddress(address);
           admin.setDepartment(department);
           admin.setPosition(position);
           admin.setImagesEntity(profile);
           repo.save(admin);
           return true;
       }
       return false;
   }

    /*Deleting_AdminAccount*/
    public boolean deleteAdmin(String email) {
        if (repo.existsByemail(email)) {
            repo.deleteByEmail(email);
            return true;
        } else {
            return false;
        }
    }

    /*Change_Password*/
    public boolean changePassword(String email, String password, String newPassword) {
        if (repo.existsByemail(email)) {
            AdminEntity adminEntity = repo.findByemail(email);
            String hashedPassword =  adminEntity.getPassword();
            boolean matches = passwordEncoder.matches(password, hashedPassword);
            if (matches) {
                adminEntity.setPassword(passwordEncoder.encode(newPassword));
                repo.save(adminEntity);
                return true;
            }
            else {return false;}
        } else
            return false;
    }

    /*Update_Admin_Details*/
    public boolean updateDetails(String email, String name, String phoneNumber,
                                 String address, String Department,MultipartFile file) throws IOException {

        if (repo.existsByemail(email)) {
            AdminEntity adminEntity = repo.findByemail(email);

            if(name!=null)
                adminEntity.setName(name);
            if(phoneNumber!=null)
                adminEntity.setPhoneNumber(phoneNumber);
            if(address!=null)
                adminEntity.setAddress(address);
            if(!Department.isEmpty())
                adminEntity.setDepartment(Department);
            if(file!=null)
            {
                Profiles_Images profile = adminEntity.getImagesEntity();
                profile.setImage1(file.getBytes());
                adminEntity.setImagesEntity(profile);
            }
            repo.save(adminEntity);
            return true;
        }
        return false;
    }

    public boolean setNewPassword(String email, String password) {

        if(repo.existsByemail(email)) {
            AdminEntity adminEntity = repo.findByemail(email);
            adminEntity.setPassword(passwordEncoder.encode(password));
            repo.save(adminEntity);
            return  true;
        }
        if(doctorRepository.existsByemail(email)) {
            DoctorEntity doctorEntity = doctorRepository.findByemail(email);
            doctorEntity.setPassword(passwordEncoder.encode(password));
            doctorRepository.save(doctorEntity);
            return  true;
        }

        return false;
    }

  AdminEntity getAdminByEmail(String email) {
        return repo.findByemail(email);
  }

}