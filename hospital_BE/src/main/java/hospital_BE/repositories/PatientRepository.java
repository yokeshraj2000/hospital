package hospital_BE.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital_BE.model.PatientDetailsEntity;

public interface PatientRepository extends JpaRepository<PatientDetailsEntity, Long> {

    public List<PatientDetailsEntity> findAll();

    public List<PatientDetailsEntity> findByMobileAndEmail(String mobile, String email);

}
