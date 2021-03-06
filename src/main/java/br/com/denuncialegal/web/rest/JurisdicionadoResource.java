package br.com.denuncialegal.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.denuncialegal.domain.Jurisdicionado;
import br.com.denuncialegal.repository.JurisdicionadoRepository;
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
 * REST controller for managing Jurisdicionado.
 */
@RestController
@RequestMapping("/api")
public class JurisdicionadoResource {

    private final Logger log = LoggerFactory.getLogger(JurisdicionadoResource.class);

    @Inject
    private JurisdicionadoRepository jurisdicionadoRepository;

    /**
     * POST  /jurisdicionados -> Create a new jurisdicionado.
     */
    @RequestMapping(value = "/jurisdicionados",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Jurisdicionado jurisdicionado) {
        log.debug("REST request to save Jurisdicionado : {}", jurisdicionado);
        jurisdicionadoRepository.save(jurisdicionado);
    }

    /**
     * GET  /jurisdicionados -> get all the jurisdicionados.
     */
    @RequestMapping(value = "/jurisdicionados",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Jurisdicionado> getAll() {
        log.debug("REST request to get all Jurisdicionados");
        return jurisdicionadoRepository.findAll();
    }

    /**
     * GET  /jurisdicionados/:id -> get the "id" jurisdicionado.
     */
    @RequestMapping(value = "/jurisdicionados/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Jurisdicionado> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Jurisdicionado : {}", id);
        Jurisdicionado jurisdicionado = jurisdicionadoRepository.findOne(id);
        if (jurisdicionado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jurisdicionado, HttpStatus.OK);
    }

    /**
     * DELETE  /jurisdicionados/:id -> delete the "id" jurisdicionado.
     */
    @RequestMapping(value = "/jurisdicionados/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Jurisdicionado : {}", id);
        jurisdicionadoRepository.delete(id);
    }
}
