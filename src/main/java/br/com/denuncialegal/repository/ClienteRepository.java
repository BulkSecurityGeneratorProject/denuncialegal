package br.com.denuncialegal.repository;

import br.com.denuncialegal.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Cliente entity.
 */
public interface ClienteRepository extends JpaRepository<Cliente,Long>{

}
