package hospital_BE.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital_BE.model.BookedSlotsEntity;

public interface BookedSlotsRepository extends JpaRepository<BookedSlotsEntity, Long> {

  public List<BookedSlotsEntity> findAll();
}
