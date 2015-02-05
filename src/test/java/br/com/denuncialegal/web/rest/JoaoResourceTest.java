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
import br.com.denuncialegal.domain.Joao;
import br.com.denuncialegal.repository.JoaoRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the JoaoResource REST controller.
 *
 * @see JoaoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class JoaoResourceTest {


    private static final Integer DEFAULT_IDADE = 0;
    private static final Integer UPDATED_IDADE = 1;
    private static final String DEFAULT_SEXUALIDADE = "SAMPLE_TEXT";
    private static final String UPDATED_SEXUALIDADE = "UPDATED_TEXT";

    @Inject
    private JoaoRepository joaoRepository;

    private MockMvc restJoaoMockMvc;

    private Joao joao;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        JoaoResource joaoResource = new JoaoResource();
        ReflectionTestUtils.setField(joaoResource, "joaoRepository", joaoRepository);
        this.restJoaoMockMvc = MockMvcBuilders.standaloneSetup(joaoResource).build();
    }

    @Before
    public void initTest() {
        joao = new Joao();
        joao.setIdade(DEFAULT_IDADE);
        joao.setSexualidade(DEFAULT_SEXUALIDADE);
    }

    @Test
    @Transactional
    public void createJoao() throws Exception {
        // Validate the database is empty
        assertThat(joaoRepository.findAll()).hasSize(0);

        // Create the Joao
        restJoaoMockMvc.perform(post("/api/joaos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(joao)))
                .andExpect(status().isOk());

        // Validate the Joao in the database
        List<Joao> joaos = joaoRepository.findAll();
        assertThat(joaos).hasSize(1);
        Joao testJoao = joaos.iterator().next();
        assertThat(testJoao.getIdade()).isEqualTo(DEFAULT_IDADE);
        assertThat(testJoao.getSexualidade()).isEqualTo(DEFAULT_SEXUALIDADE);
    }

    @Test
    @Transactional
    public void getAllJoaos() throws Exception {
        // Initialize the database
        joaoRepository.saveAndFlush(joao);

        // Get all the joaos
        restJoaoMockMvc.perform(get("/api/joaos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(joao.getId().intValue()))
                .andExpect(jsonPath("$.[0].idade").value(DEFAULT_IDADE))
                .andExpect(jsonPath("$.[0].sexualidade").value(DEFAULT_SEXUALIDADE.toString()));
    }

    @Test
    @Transactional
    public void getJoao() throws Exception {
        // Initialize the database
        joaoRepository.saveAndFlush(joao);

        // Get the joao
        restJoaoMockMvc.perform(get("/api/joaos/{id}", joao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(joao.getId().intValue()))
            .andExpect(jsonPath("$.idade").value(DEFAULT_IDADE))
            .andExpect(jsonPath("$.sexualidade").value(DEFAULT_SEXUALIDADE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingJoao() throws Exception {
        // Get the joao
        restJoaoMockMvc.perform(get("/api/joaos/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJoao() throws Exception {
        // Initialize the database
        joaoRepository.saveAndFlush(joao);

        // Update the joao
        joao.setIdade(UPDATED_IDADE);
        joao.setSexualidade(UPDATED_SEXUALIDADE);
        restJoaoMockMvc.perform(post("/api/joaos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(joao)))
                .andExpect(status().isOk());

        // Validate the Joao in the database
        List<Joao> joaos = joaoRepository.findAll();
        assertThat(joaos).hasSize(1);
        Joao testJoao = joaos.iterator().next();
        assertThat(testJoao.getIdade()).isEqualTo(UPDATED_IDADE);
        assertThat(testJoao.getSexualidade()).isEqualTo(UPDATED_SEXUALIDADE);
    }

    @Test
    @Transactional
    public void deleteJoao() throws Exception {
        // Initialize the database
        joaoRepository.saveAndFlush(joao);

        // Get the joao
        restJoaoMockMvc.perform(delete("/api/joaos/{id}", joao.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Joao> joaos = joaoRepository.findAll();
        assertThat(joaos).hasSize(0);
    }
}
