package br.com.denuncialegal.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.denuncialegal.domain.Pessoa;
import br.com.denuncialegal.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Pessoa.
 */
@RestController
@RequestMapping("/api")
public class PessoaResource {

    private final Logger log = LoggerFactory.getLogger(PessoaResource.class);

    @Inject
    private PessoaRepository pessoaRepository;

    /**
     * POST  /pessoas -> Create a new pessoa.
     */
    @RequestMapping(value = "/pessoas",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Pessoa pessoa) {
        log.debug("REST request to save Pessoa : {}", pessoa);
        pessoaRepository.save(pessoa);
    }

    /**
     * GET  /pessoas -> get all the pessoas.
     */
    @RequestMapping(value = "/pessoas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Pessoa> getAll() {
        log.debug("REST request to get all Pessoas");
        return pessoaRepository.findAll();
    }

    /**
     * GET  /pessoas/:id -> get the "id" pessoa.
     */
    @RequestMapping(value = "/pessoas/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Pessoa> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Pessoa : {}", id);
        Pessoa pessoa = pessoaRepository.findOne(id);
        if (pessoa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    /**
     * DELETE  /pessoas/:id -> delete the "id" pessoa.
     */
    @RequestMapping(value = "/pessoas/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Pessoa : {}", id);
        pessoaRepository.delete(id);
    }
}
