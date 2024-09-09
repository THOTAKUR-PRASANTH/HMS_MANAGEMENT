package Finial.HMS.DOCTOR_MODULE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Doctor_controller {

    @Autowired
    private DoctorService doctorService;


    @GetMapping("/add")
    public String processAddDoctorForm(@ModelAttribute("doctor") DoctorEntity doctor) {
        return "registerDoctor";
    }

    @PostMapping(value = "/addDoctor")
    public ResponseEntity<String> addDoctor(@ModelAttribute DoctorEntity doctorEntity)
    {
        if(doctorService.addDoctor(doctorEntity))
             return ResponseEntity.ok("addDoctor success");
        else
            return ResponseEntity.ok("addDoctor failed");
    }


}
