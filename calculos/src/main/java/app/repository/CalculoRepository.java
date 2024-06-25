package app.repository;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Saida;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculoRepository extends JpaRepository<Saida, Long> {

}
