package br.com.denuncialegal.repository;

import br.com.denuncialegal.domain.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Denuncia entity.
 */
public interface DenunciaRepository extends JpaRepository<Denuncia,Long>{

}
