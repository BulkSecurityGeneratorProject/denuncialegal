package br.com.denuncialegal.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.denuncialegal.domain.Denuncia;
import br.com.denuncialegal.repository.DenunciaRepository;
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
 * REST controller for managing Denuncia.
 */
@RestController
@RequestMapping("/api")
public class DenunciaResource {

    private final Logger log = LoggerFactory.getLogger(DenunciaResource.class);

    @Inject
    private DenunciaRepository denunciaRepository;

    /**
     * POST  /denuncias -> Create a new denuncia.
     */
    @RequestMapping(value = "/denuncias",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Denuncia denuncia) {
        log.debug("REST request to save Denuncia : {}", denuncia);
        denunciaRepository.save(denuncia);
    }

    /**
     * GET  /denuncias -> get all the denuncias.
     */
    @RequestMapping(value = "/denuncias",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Denuncia> getAll() {
        log.debug("REST request to get all Denuncias");
        return denunciaRepository.findAll();
    }

    /**
     * GET  /denuncias/:id -> get the "id" denuncia.
     */
    @RequestMapping(value = "/denuncias/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Denuncia> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Denuncia : {}", id);
        Denuncia denuncia = denunciaRepository.findOne(id);
        if (denuncia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(denuncia, HttpStatus.OK);
    }

    /**
     * DELETE  /denuncias/:id -> delete the "id" denuncia.
     */
    @RequestMapping(value = "/denuncias/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Denuncia : {}", id);
        denunciaRepository.delete(id);
    }
}
