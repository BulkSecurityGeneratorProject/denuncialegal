package br.com.denuncialegal.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.denuncialegal.domain.Kuririn;
import br.com.denuncialegal.repository.KuririnRepository;
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
 * REST controller for managing Kuririn.
 */
@RestController
@RequestMapping("/api")
public class KuririnResource {

    private final Logger log = LoggerFactory.getLogger(KuririnResource.class);

    @Inject
    private KuririnRepository kuririnRepository;

    /**
     * POST  /kuririns -> Create a new kuririn.
     */
    @RequestMapping(value = "/kuririns",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Kuririn kuririn) {
        log.debug("REST request to save Kuririn : {}", kuririn);
        kuririnRepository.save(kuririn);
    }

    /**
     * GET  /kuririns -> get all the kuririns.
     */
    @RequestMapping(value = "/kuririns",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Kuririn> getAll() {
        log.debug("REST request to get all Kuririns");
        return kuririnRepository.findAll();
    }

    /**
     * GET  /kuririns/:id -> get the "id" kuririn.
     */
    @RequestMapping(value = "/kuririns/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Kuririn> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Kuririn : {}", id);
        Kuririn kuririn = kuririnRepository.findOne(id);
        if (kuririn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(kuririn, HttpStatus.OK);
    }

    /**
     * DELETE  /kuririns/:id -> delete the "id" kuririn.
     */
    @RequestMapping(value = "/kuririns/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Kuririn : {}", id);
        kuririnRepository.delete(id);
    }
}
