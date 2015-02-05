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
import br.com.denuncialegal.domain.Pessoa;
import br.com.denuncialegal.repository.PessoaRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PessoaResource REST controller.
 *
 * @see PessoaResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PessoaResourceTest {

    private static final String DEFAULT_NOME = "SAMPLE_TEXT";
    private static final String UPDATED_NOME = "UPDATED_TEXT";

    private static final Integer DEFAULT_IDADE = 0;
    private static final Integer UPDATED_IDADE = 1;

    @Inject
    private PessoaRepository pessoaRepository;

    private MockMvc restPessoaMockMvc;

    private Pessoa pessoa;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PessoaResource pessoaResource = new PessoaResource();
        ReflectionTestUtils.setField(pessoaResource, "pessoaRepository", pessoaRepository);
        this.restPessoaMockMvc = MockMvcBuilders.standaloneSetup(pessoaResource).build();
    }

    @Before
    public void initTest() {
        pessoa = new Pessoa();
        pessoa.setNome(DEFAULT_NOME);
        pessoa.setIdade(DEFAULT_IDADE);
    }

    @Test
    @Transactional
    public void createPessoa() throws Exception {
        // Validate the database is empty
        assertThat(pessoaRepository.findAll()).hasSize(0);

        // Create the Pessoa
        restPessoaMockMvc.perform(post("/api/pessoas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pessoa)))
                .andExpect(status().isOk());

        // Validate the Pessoa in the database
        List<Pessoa> pessoas = pessoaRepository.findAll();
        assertThat(pessoas).hasSize(1);
        Pessoa testPessoa = pessoas.iterator().next();
        assertThat(testPessoa.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPessoa.getIdade()).isEqualTo(DEFAULT_IDADE);
    }

    @Test
    @Transactional
    public void getAllPessoas() throws Exception {
        // Initialize the database
        pessoaRepository.saveAndFlush(pessoa);

        // Get all the pessoas
        restPessoaMockMvc.perform(get("/api/pessoas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(pessoa.getId().intValue()))
                .andExpect(jsonPath("$.[0].nome").value(DEFAULT_NOME.toString()))
                .andExpect(jsonPath("$.[0].idade").value(DEFAULT_IDADE));
    }

    @Test
    @Transactional
    public void getPessoa() throws Exception {
        // Initialize the database
        pessoaRepository.saveAndFlush(pessoa);

        // Get the pessoa
        restPessoaMockMvc.perform(get("/api/pessoas/{id}", pessoa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(pessoa.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.idade").value(DEFAULT_IDADE));
    }

    @Test
    @Transactional
    public void getNonExistingPessoa() throws Exception {
        // Get the pessoa
        restPessoaMockMvc.perform(get("/api/pessoas/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePessoa() throws Exception {
        // Initialize the database
        pessoaRepository.saveAndFlush(pessoa);

        // Update the pessoa
        pessoa.setNome(UPDATED_NOME);
        pessoa.setIdade(UPDATED_IDADE);
        restPessoaMockMvc.perform(post("/api/pessoas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pessoa)))
                .andExpect(status().isOk());

        // Validate the Pessoa in the database
        List<Pessoa> pessoas = pessoaRepository.findAll();
        assertThat(pessoas).hasSize(1);
        Pessoa testPessoa = pessoas.iterator().next();
        assertThat(testPessoa.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPessoa.getIdade()).isEqualTo(UPDATED_IDADE);
    }

    @Test
    @Transactional
    public void deletePessoa() throws Exception {
        // Initialize the database
        pessoaRepository.saveAndFlush(pessoa);

        // Get the pessoa
        restPessoaMockMvc.perform(delete("/api/pessoas/{id}", pessoa.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Pessoa> pessoas = pessoaRepository.findAll();
        assertThat(pessoas).hasSize(0);
    }
}
