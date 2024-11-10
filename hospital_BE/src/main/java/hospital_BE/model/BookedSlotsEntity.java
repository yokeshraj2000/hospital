package hospital_BE.model;

import java.time.LocalDate;

import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

@Table(name = "booked_Slots")
public class BookedSlotsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bookedDate")
    private LocalDate bookedDate;

    @Column(name = "bookedSlot")
    private String bookedSlot;

    @Column(name = "patient_id")
    private Long patient_id;

}
