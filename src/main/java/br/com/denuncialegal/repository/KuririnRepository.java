package br.com.denuncialegal.repository;

import br.com.denuncialegal.domain.Kuririn;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Kuririn entity.
 */
public interface KuririnRepository extends JpaRepository<Kuririn,Long>{

}
