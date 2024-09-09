package Finial.HMS.Users_Profiles_Images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Profiles_Service {

    @Autowired
    Profiles_Repo repo;
    
    public void addImage(MultipartFile file)
    {

    }

}
