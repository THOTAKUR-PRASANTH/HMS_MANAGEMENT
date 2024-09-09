package Finial.HMS.DOCTOR_MODULE;

import Finial.HMS.ADMIN_MODULE.EMAIL.EmailEntity;
import Finial.HMS.Users_Profiles_Images.Profiles_Images;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctorentity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private Integer age; // Doctor's age

    @Column
    private String gender; // Doctor's gender (e.g., Male, Female, Non-binary)

    @Column(name = "phone_number")
    private String phoneNumber; // Doctor's phone number

    @Column
    private String address; // Doctor's address

    @Column(nullable = false)
    private String specialty; // Doctor's medical specialty

    @Column
    private Integer yearsOfExperience; // Number of years the doctor has practiced

    @Column
    private String qualifications; // Doctor's qualifications (e.g., MBBS, MD)

    @Column
    private String department; // Department the doctor works in

    @Column
    private String position = "Doctor"; // Doctor's job position or title


    @Column
    private String role = "ROLE_DOCTOR";

    @OneToOne(mappedBy = "doctorEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private EmailEntity emailEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="profile_id")
    private Profiles_Images imagesEntity;

    @Override
    public String toString() {
        return "DoctorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", specialty='" + specialty + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                ", qualifications='" + qualifications + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
