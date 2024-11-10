package hospital_BE.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "patient_details")
public class PatientDetailsEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patient_id;

    @Column(name = "patient_name")
    private String name;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "type_of_appointment")
    private String typeOfAppointment;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "age")
    private Integer age;

    @Column(name = "doctor")
    private String doctor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    private List<BookedSlotsEntity> bookedSlotsEntities;
}
