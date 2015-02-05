package br.com.denuncialegal.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.denuncialegal.domain.Cliente;
import br.com.denuncialegal.repository.ClienteRepository;
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
 * REST controller for managing Cliente.
 */
@RestController
@RequestMapping("/api")
public class ClienteResource {

    private final Logger log = LoggerFactory.getLogger(ClienteResource.class);

    @Inject
    private ClienteRepository clienteRepository;

    /**
     * POST  /clientes -> Create a new cliente.
     */
    @RequestMapping(value = "/clientes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Cliente cliente) {
        log.debug("REST request to save Cliente : {}", cliente);
        clienteRepository.save(cliente);
    }

    /**
     * GET  /clientes -> get all the clientes.
     */
    @RequestMapping(value = "/clientes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Cliente> getAll() {
        log.debug("REST request to get all Clientes");
        return clienteRepository.findAll();
    }

    /**
     * GET  /clientes/:id -> get the "id" cliente.
     */
    @RequestMapping(value = "/clientes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Cliente> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Cliente : {}", id);
        Cliente cliente = clienteRepository.findOne(id);
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    /**
     * DELETE  /clientes/:id -> delete the "id" cliente.
     */
    @RequestMapping(value = "/clientes/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Cliente : {}", id);
        clienteRepository.delete(id);
    }
}
