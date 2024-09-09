package Finial.HMS.ADMIN_MODULE;

import Finial.HMS.ADMIN_MODULE.EMAIL.EmailEntity;
import Finial.HMS.Users_Profiles_Images.Profiles_Images;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "adminentity")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdminEntity {

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
    private Integer age; // Admin's age

    @Column
    private String gender; // Admin's gender (e.g., Male, Female, Non-binary)

    @Column(name = "phone_number")
    private String phoneNumber; // Admin's phone number

    @Column
    private String address; // Admin's address

    @Column
    private String department; // Department the admin works in

    @Column
    private String position; // Admin's job position or title

    @Column
    private String Authorities = "ADMIN";

    @Column
    private String Role = "ROLE_ADMIN";

    @OneToOne(mappedBy = "adminEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private EmailEntity emailEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Profile_id")
    private Profiles_Images imagesEntity;

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminEntity{" +
                "Authorities='" + Authorities + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
