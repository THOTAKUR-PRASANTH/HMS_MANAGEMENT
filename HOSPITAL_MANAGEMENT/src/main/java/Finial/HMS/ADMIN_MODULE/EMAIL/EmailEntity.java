package Finial.HMS.ADMIN_MODULE.EMAIL;

import Finial.HMS.ADMIN_MODULE.AdminEntity;
import Finial.HMS.DOCTOR_MODULE.DoctorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Integer OTP;
    private Date time;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private AdminEntity adminEntity;

    @OneToOne
    @JoinColumn(name = "Doctor_id")
    private DoctorEntity doctorEntity;


    @Override
    public String toString() {
        return "EmailEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", OTP=" + OTP +
                ", time=" + time +
                '}';
    }
}
