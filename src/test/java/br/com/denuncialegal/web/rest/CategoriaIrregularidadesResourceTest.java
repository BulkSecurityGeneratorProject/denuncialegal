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
import br.com.denuncialegal.domain.CategoriaIrregularidades;
import br.com.denuncialegal.repository.CategoriaIrregularidadesRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CategoriaIrregularidadesResource REST controller.
 *
 * @see CategoriaIrregularidadesResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CategoriaIrregularidadesResourceTest {

    private static final String DEFAULT_NOME = "SAMPLE_TEXT";
    private static final String UPDATED_NOME = "UPDATED_TEXT";

    @Inject
    private CategoriaIrregularidadesRepository categoriaIrregularidadesRepository;

    private MockMvc restCategoriaIrregularidadesMockMvc;

    private CategoriaIrregularidades categoriaIrregularidades;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CategoriaIrregularidadesResource categoriaIrregularidadesResource = new CategoriaIrregularidadesResource();
        ReflectionTestUtils.setField(categoriaIrregularidadesResource, "categoriaIrregularidadesRepository", categoriaIrregularidadesRepository);
        this.restCategoriaIrregularidadesMockMvc = MockMvcBuilders.standaloneSetup(categoriaIrregularidadesResource).build();
    }

    @Before
    public void initTest() {
        categoriaIrregularidades = new CategoriaIrregularidades();
        categoriaIrregularidades.setNome(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createCategoriaIrregularidades() throws Exception {
        // Validate the database is empty
        assertThat(categoriaIrregularidadesRepository.findAll()).hasSize(0);

        // Create the CategoriaIrregularidades
        restCategoriaIrregularidadesMockMvc.perform(post("/api/categoriaIrregularidadess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(categoriaIrregularidades)))
                .andExpect(status().isOk());

        // Validate the CategoriaIrregularidades in the database
        List<CategoriaIrregularidades> categoriaIrregularidadess = categoriaIrregularidadesRepository.findAll();
        assertThat(categoriaIrregularidadess).hasSize(1);
        CategoriaIrregularidades testCategoriaIrregularidades = categoriaIrregularidadess.iterator().next();
        assertThat(testCategoriaIrregularidades.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void getAllCategoriaIrregularidadess() throws Exception {
        // Initialize the database
        categoriaIrregularidadesRepository.saveAndFlush(categoriaIrregularidades);

        // Get all the categoriaIrregularidadess
        restCategoriaIrregularidadesMockMvc.perform(get("/api/categoriaIrregularidadess"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(categoriaIrregularidades.getId().intValue()))
                .andExpect(jsonPath("$.[0].nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getCategoriaIrregularidades() throws Exception {
        // Initialize the database
        categoriaIrregularidadesRepository.saveAndFlush(categoriaIrregularidades);

        // Get the categoriaIrregularidades
        restCategoriaIrregularidadesMockMvc.perform(get("/api/categoriaIrregularidadess/{id}", categoriaIrregularidades.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(categoriaIrregularidades.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCategoriaIrregularidades() throws Exception {
        // Get the categoriaIrregularidades
        restCategoriaIrregularidadesMockMvc.perform(get("/api/categoriaIrregularidadess/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategoriaIrregularidades() throws Exception {
        // Initialize the database
        categoriaIrregularidadesRepository.saveAndFlush(categoriaIrregularidades);

        // Update the categoriaIrregularidades
        categoriaIrregularidades.setNome(UPDATED_NOME);
        restCategoriaIrregularidadesMockMvc.perform(post("/api/categoriaIrregularidadess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(categoriaIrregularidades)))
                .andExpect(status().isOk());

        // Validate the CategoriaIrregularidades in the database
        List<CategoriaIrregularidades> categoriaIrregularidadess = categoriaIrregularidadesRepository.findAll();
        assertThat(categoriaIrregularidadess).hasSize(1);
        CategoriaIrregularidades testCategoriaIrregularidades = categoriaIrregularidadess.iterator().next();
        assertThat(testCategoriaIrregularidades.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void deleteCategoriaIrregularidades() throws Exception {
        // Initialize the database
        categoriaIrregularidadesRepository.saveAndFlush(categoriaIrregularidades);

        // Get the categoriaIrregularidades
        restCategoriaIrregularidadesMockMvc.perform(delete("/api/categoriaIrregularidadess/{id}", categoriaIrregularidades.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CategoriaIrregularidades> categoriaIrregularidadess = categoriaIrregularidadesRepository.findAll();
        assertThat(categoriaIrregularidadess).hasSize(0);
    }
}
