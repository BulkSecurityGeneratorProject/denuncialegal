package br.com.denuncialegal.repository;

import br.com.denuncialegal.domain.Jurisdicionado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Jurisdicionado entity.
 */
public interface JurisdicionadoRepository extends JpaRepository<Jurisdicionado,Long>{

}
