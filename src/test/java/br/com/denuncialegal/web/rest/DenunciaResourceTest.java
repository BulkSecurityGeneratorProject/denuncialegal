package br.com.denuncialegal.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import br.com.denuncialegal.Application;
import br.com.denuncialegal.domain.Denuncia;
import br.com.denuncialegal.repository.DenunciaRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DenunciaResource REST controller.
 *
 * @see DenunciaResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DenunciaResourceTest {

    private static final String DEFAULT_TITULO = "SAMPLE_TEXT";
    private static final String UPDATED_TITULO = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRICAO = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRICAO = "UPDATED_TEXT";

    @Inject
    private DenunciaRepository denunciaRepository;

    private MockMvc restDenunciaMockMvc;

    private Denuncia denuncia;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DenunciaResource denunciaResource = new DenunciaResource();
        ReflectionTestUtils.setField(denunciaResource, "denunciaRepository", denunciaRepository);
        this.restDenunciaMockMvc = MockMvcBuilders.standaloneSetup(denunciaResource).build();
    }

    @Before
    public void initTest() {
        denuncia = new Denuncia();
        denuncia.setTitulo(DEFAULT_TITULO);
        denuncia.setDescricao(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createDenuncia() throws Exception {
        // Validate the database is empty
        assertThat(denunciaRepository.findAll()).hasSize(0);

        // Create the Denuncia
        restDenunciaMockMvc.perform(post("/api/denuncias")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(denuncia)))
                .andExpect(status().isOk());

        // Validate the Denuncia in the database
        List<Denuncia> denuncias = denunciaRepository.findAll();
        assertThat(denuncias).hasSize(1);
        Denuncia testDenuncia = denuncias.iterator().next();
        assertThat(testDenuncia.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testDenuncia.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void getAllDenuncias() throws Exception {
        // Initialize the database
        denunciaRepository.saveAndFlush(denuncia);

        // Get all the denuncias
        restDenunciaMockMvc.perform(get("/api/denuncias"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(denuncia.getId().intValue()))
                .andExpect(jsonPath("$.[0].titulo").value(DEFAULT_TITULO.toString()))
                .andExpect(jsonPath("$.[0].descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getDenuncia() throws Exception {
        // Initialize the database
        denunciaRepository.saveAndFlush(denuncia);

        // Get the denuncia
        restDenunciaMockMvc.perform(get("/api/denuncias/{id}", denuncia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(denuncia.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDenuncia() throws Exception {
        // Get the denuncia
        restDenunciaMockMvc.perform(get("/api/denuncias/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDenuncia() throws Exception {
        // Initialize the database
        denunciaRepository.saveAndFlush(denuncia);

        // Update the denuncia
        denuncia.setTitulo(UPDATED_TITULO);
        denuncia.setDescricao(UPDATED_DESCRICAO);
        restDenunciaMockMvc.perform(post("/api/denuncias")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(denuncia)))
                .andExpect(status().isOk());

        // Validate the Denuncia in the database
        List<Denuncia> denuncias = denunciaRepository.findAll();
        assertThat(denuncias).hasSize(1);
        Denuncia testDenuncia = denuncias.iterator().next();
        assertThat(testDenuncia.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testDenuncia.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void deleteDenuncia() throws Exception {
        // Initialize the database
        denunciaRepository.saveAndFlush(denuncia);

        // Get the denuncia
        restDenunciaMockMvc.perform(delete("/api/denuncias/{id}", denuncia.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Denuncia> denuncias = denunciaRepository.findAll();
        assertThat(denuncias).hasSize(0);
    }
}
