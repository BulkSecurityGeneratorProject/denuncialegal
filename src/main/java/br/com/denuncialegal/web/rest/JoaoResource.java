package br.com.denuncialegal.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.denuncialegal.domain.Joao;
import br.com.denuncialegal.repository.JoaoRepository;
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
 * REST controller for managing Joao.
 */
@RestController
@RequestMapping("/api")
public class JoaoResource {

    private final Logger log = LoggerFactory.getLogger(JoaoResource.class);

    @Inject
    private JoaoRepository joaoRepository;

    /**
     * POST  /joaos -> Create a new joao.
     */
    @RequestMapping(value = "/joaos",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Joao joao) {
        log.debug("REST request to save Joao : {}", joao);
        joaoRepository.save(joao);
    }

    /**
     * GET  /joaos -> get all the joaos.
     */
    @RequestMapping(value = "/joaos",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Joao> getAll() {
        log.debug("REST request to get all Joaos");
        return joaoRepository.findAll();
    }

    /**
     * GET  /joaos/:id -> get the "id" joao.
     */
    @RequestMapping(value = "/joaos/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Joao> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Joao : {}", id);
        Joao joao = joaoRepository.findOne(id);
        if (joao == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(joao, HttpStatus.OK);
    }

    /**
     * DELETE  /joaos/:id -> delete the "id" joao.
     */
    @RequestMapping(value = "/joaos/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Joao : {}", id);
        joaoRepository.delete(id);
    }
}
