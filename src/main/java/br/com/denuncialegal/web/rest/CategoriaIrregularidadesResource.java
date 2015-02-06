package br.com.denuncialegal.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.denuncialegal.domain.CategoriaIrregularidades;
import br.com.denuncialegal.repository.CategoriaIrregularidadesRepository;
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
 * REST controller for managing CategoriaIrregularidades.
 */
@RestController
@RequestMapping("/api")
public class CategoriaIrregularidadesResource {

    private final Logger log = LoggerFactory.getLogger(CategoriaIrregularidadesResource.class);

    @Inject
    private CategoriaIrregularidadesRepository categoriaIrregularidadesRepository;

    /**
     * POST  /categoriaIrregularidadess -> Create a new categoriaIrregularidades.
     */
    @RequestMapping(value = "/categoriaIrregularidadess",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody CategoriaIrregularidades categoriaIrregularidades) {
        log.debug("REST request to save CategoriaIrregularidades : {}", categoriaIrregularidades);
        categoriaIrregularidadesRepository.save(categoriaIrregularidades);
    }

    /**
     * GET  /categoriaIrregularidadess -> get all the categoriaIrregularidadess.
     */
    @RequestMapping(value = "/categoriaIrregularidadess",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CategoriaIrregularidades> getAll() {
        log.debug("REST request to get all CategoriaIrregularidadess");
        return categoriaIrregularidadesRepository.findAll();
    }

    /**
     * GET  /categoriaIrregularidadess/:id -> get the "id" categoriaIrregularidades.
     */
    @RequestMapping(value = "/categoriaIrregularidadess/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CategoriaIrregularidades> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get CategoriaIrregularidades : {}", id);
        CategoriaIrregularidades categoriaIrregularidades = categoriaIrregularidadesRepository.findOne(id);
        if (categoriaIrregularidades == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoriaIrregularidades, HttpStatus.OK);
    }

    /**
     * DELETE  /categoriaIrregularidadess/:id -> delete the "id" categoriaIrregularidades.
     */
    @RequestMapping(value = "/categoriaIrregularidadess/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete CategoriaIrregularidades : {}", id);
        categoriaIrregularidadesRepository.delete(id);
    }
}
