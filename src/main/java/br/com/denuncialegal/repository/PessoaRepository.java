package br.com.denuncialegal.repository;

import br.com.denuncialegal.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Pessoa entity.
 */
public interface PessoaRepository extends JpaRepository<Pessoa,Long>{

}
