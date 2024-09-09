package Finial.HMS.DOCTOR_MODULE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Boolean addDoctor(DoctorEntity doctorEntity) {
        doctorRepository.save(doctorEntity);
        return true;
    }
}
