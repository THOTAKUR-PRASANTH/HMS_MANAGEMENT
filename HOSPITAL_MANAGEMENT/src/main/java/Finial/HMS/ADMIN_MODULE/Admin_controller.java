package Finial.HMS.ADMIN_MODULE;

import Finial.HMS.ADMIN_MODULE.EMAIL.OtpGeneration;
import Finial.HMS.SECURITY_CONFIGURATION.AdminDetails;
import Finial.HMS.Users_Profiles_Images.Profiles_Images;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Data
@Slf4j
public class Admin_controller {

    @Autowired
    Adminservice adminservice;
    @Autowired
    OtpGeneration otpGeneration;

    @Autowired
    AdminDetails adminDetails;

    AdminEntity adminEntity = null;
    @GetMapping("/sucess")
    public String sucess(Model model) {
        String username = adminDetails.getAuthenticatedUsername();
        if (username == null) {
            return "error";
        }
         adminEntity = adminservice.getAdminByEmail(username);
        if (adminEntity != null) {
            Profiles_Images profileImageEntity = adminEntity.getImagesEntity();
            if (profileImageEntity != null && profileImageEntity.getImage1() != null) {
                String base64Image = Base64.getEncoder().encodeToString(profileImageEntity.getImage1());
                model.addAttribute("profileImage", base64Image);
            }
            model.addAttribute("adminDetails", adminEntity);
        }
        return "sucess";
    }


    @GetMapping("/AdminProfilePage")
    public String AdminProfilePage(Model model) {
        if (adminEntity != null) {
            Profiles_Images profileImageEntity = adminEntity.getImagesEntity();
            if (profileImageEntity != null && profileImageEntity.getImage1() != null) {
                String base64Image = Base64.getEncoder().encodeToString(profileImageEntity.getImage1());
                model.addAttribute("profileImage", base64Image);
            }
            model.addAttribute("adminDetails", adminEntity);
        }
        return "AdminProfilePage";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        Model model) {
        if ("true".equals(error)) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login";
    }


    /*Adding_Admin*/
    @PostMapping(value = "/addAdmin", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity addAdmin(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("age") int age,
            @RequestParam("gender") String gender,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("address") String address,
            @RequestParam("department") String department,
            @RequestParam("position") String position,
            @RequestParam("file") MultipartFile file) throws IOException {

        boolean addsucess = adminservice.addAdmin(name,email,password,age,gender,phoneNumber,address,department,position,file);

        if(addsucess)
            return ResponseEntity.ok("successfully added admin");
        else
            return ResponseEntity.status(HttpStatus.CREATED).body("admin not added");
    }

    /*Delete_Admin_Account*/

    @GetMapping("/deleteAdmin")
    public String deleteAdmin() {
        if (adminservice.deleteAdmin(adminDetails.getAuthenticatedUsername())) {
            return "index";
        } else {
            return "error";
        }
    }

    /*Change_Password*/
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String current_password,
                                 @RequestParam String new_password,
                                 RedirectAttributes redirectAttributes
                                ) {
         String email = adminDetails.getAuthenticatedUsername();
        if(adminservice.changePassword(email,current_password,new_password))
            redirectAttributes.addFlashAttribute("success", "Password changed successfully");
        else
            redirectAttributes.addFlashAttribute("error", "Password Does Not Match to Previous");
        return "redirect:/Settings";
    }

    /*updateDetails*/

    @GetMapping("/Settings")
    public String settings(Model model) {
        if (adminEntity != null) {
            Profiles_Images profileImageEntity = adminEntity.getImagesEntity();
            if (profileImageEntity != null && profileImageEntity.getImage1() != null) {
                String base64Image = Base64.getEncoder().encodeToString(profileImageEntity.getImage1());
                model.addAttribute("profileImage", base64Image);
            }
            model.addAttribute("adminDetails", adminEntity);
        }
        return "Settings";}

    @PostMapping(value ="/updateDetails",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public String updateDetails(@RequestParam String name,
                                           @RequestParam String department,
                                           @RequestParam String phone,
                                           @RequestParam String address,
                                           @RequestParam("file") MultipartFile file,
                                           RedirectAttributes redirectAttribute
                                           ) throws IOException{
        String email = adminDetails.getAuthenticatedUsername();

        if(adminservice.updateDetails(email,name,phone,address,department,file)) {
           redirectAttribute.addFlashAttribute("success", "successfully updated");
        }
        else {
            redirectAttribute.addFlashAttribute("error", "Failed to update details");
        }
        return "redirect:/Settings";
    }

   /*ForgetPassword*/
   @RequestMapping(value = "/Forget-Password", method = RequestMethod.POST)
   public String generateForgetPasswordOtp(@RequestParam String email, Model model) {
       boolean sentSuccess = otpGeneration.otpgeneration(email);
       if (sentSuccess) {
           model.addAttribute("sucess_message", "OTP generated successfully");
       } else {
           model.addAttribute("failure_message", "Invalid Email");
       }
       return "ForgetPassword_otp_sender"; // Make sure this matches the Thymeleaf template name
   }

    /*Email_Page*/
    @RequestMapping(value = "/ForgetPassword_otp_sender", method = RequestMethod.GET)
    public String Email_Page() {return "ForgetPassword_otp_sender";}

    @GetMapping("/Verify_Otp_Page")
    public String verify_Otp_page(){return "Verify_Otp_page";}


    /*Otp_Verifaction*/
    @PostMapping("/OtpVerification")
    public String otpVerification(@RequestParam String email,@RequestParam Integer otp,Model model)
    {
        boolean verifySucess = false;
        verifySucess = otpGeneration. otpVerification(email,otp);
        model.addAttribute("email", email);
        if(verifySucess)
           // model.addAttribute("sucess_message", "OTP Verified successfully");
            return "redirect:/New_Password_Page?email=" + email;
        else
            model.addAttribute("failure_message", "Invalid OTP");
        return "Verify_Otp_page";
    }

    /*Set_New_Password*/
    @GetMapping("/New_Password_Page")
    public String setNewPassword(@RequestParam String email,Model model)
    {
        model.addAttribute("email", email);
        return  "New_Password_Page";
    }
    @PostMapping("/SetNewPassword")
    public String SetNewPassword(@RequestParam String email,@RequestParam String password,Model model)
    {
        boolean updated = false;
        updated = adminservice.setNewPassword(email,password);
        if(updated)
            model.addAttribute("success_message", "Password Changed successfully");
        else
            model.addAttribute("failure_message", "Unable to change password Something went wrong");
        return "New_Password_Page";
    }






}
