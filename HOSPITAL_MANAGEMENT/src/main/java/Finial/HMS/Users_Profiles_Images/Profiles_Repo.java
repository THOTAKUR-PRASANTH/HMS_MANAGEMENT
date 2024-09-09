package Finial.HMS.Users_Profiles_Images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Profiles_Repo extends JpaRepository<Profiles_Images, Integer> {

}
