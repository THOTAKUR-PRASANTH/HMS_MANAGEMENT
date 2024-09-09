package Finial.HMS.Users_Profiles_Images;

import Finial.HMS.ADMIN_MODULE.AdminEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profiles_Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_images", columnDefinition = "LONGBLOB")
    private byte[] image1;

    @OneToOne(mappedBy = "imagesEntity",cascade = CascadeType.ALL)
    private AdminEntity adminImage;

}
