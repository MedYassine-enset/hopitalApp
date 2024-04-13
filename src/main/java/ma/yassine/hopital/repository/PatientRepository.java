package ma.yassine.hopital.repository;

import ma.yassine.hopital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    //Page<Patient> findByN(String keyword, Pageable pageable);
    //Page<Patient> findPatientByNomContains(String keyword, Pageable pageable);
    Page<Patient> findByNomContains(String keyword, Pageable pageable);


}
