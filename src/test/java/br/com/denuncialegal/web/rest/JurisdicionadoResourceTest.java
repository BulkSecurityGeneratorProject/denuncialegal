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
import br.com.denuncialegal.domain.Jurisdicionado;
import br.com.denuncialegal.repository.JurisdicionadoRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the JurisdicionadoResource REST controller.
 *
 * @see JurisdicionadoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class JurisdicionadoResourceTest {

    private static final String DEFAULT_NOME = "SAMPLE_TEXT";
    private static final String UPDATED_NOME = "UPDATED_TEXT";

    private static final Integer DEFAULT_TIPO_JURISDICIONADO = 0;
    private static final Integer UPDATED_TIPO_JURISDICIONADO = 1;

    @Inject
    private JurisdicionadoRepository jurisdicionadoRepository;

    private MockMvc restJurisdicionadoMockMvc;

    private Jurisdicionado jurisdicionado;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        JurisdicionadoResource jurisdicionadoResource = new JurisdicionadoResource();
        ReflectionTestUtils.setField(jurisdicionadoResource, "jurisdicionadoRepository", jurisdicionadoRepository);
        this.restJurisdicionadoMockMvc = MockMvcBuilders.standaloneSetup(jurisdicionadoResource).build();
    }

    @Before
    public void initTest() {
        jurisdicionado = new Jurisdicionado();
        jurisdicionado.setNome(DEFAULT_NOME);
        jurisdicionado.setTipoJurisdicionado(DEFAULT_TIPO_JURISDICIONADO);
    }

    @Test
    @Transactional
    public void createJurisdicionado() throws Exception {
        // Validate the database is empty
        assertThat(jurisdicionadoRepository.findAll()).hasSize(0);

        // Create the Jurisdicionado
        restJurisdicionadoMockMvc.perform(post("/api/jurisdicionados")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(jurisdicionado)))
                .andExpect(status().isOk());

        // Validate the Jurisdicionado in the database
        List<Jurisdicionado> jurisdicionados = jurisdicionadoRepository.findAll();
        assertThat(jurisdicionados).hasSize(1);
        Jurisdicionado testJurisdicionado = jurisdicionados.iterator().next();
        assertThat(testJurisdicionado.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testJurisdicionado.getTipoJurisdicionado()).isEqualTo(DEFAULT_TIPO_JURISDICIONADO);
    }

    @Test
    @Transactional
    public void getAllJurisdicionados() throws Exception {
        // Initialize the database
        jurisdicionadoRepository.saveAndFlush(jurisdicionado);

        // Get all the jurisdicionados
        restJurisdicionadoMockMvc.perform(get("/api/jurisdicionados"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(jurisdicionado.getId().intValue()))
                .andExpect(jsonPath("$.[0].nome").value(DEFAULT_NOME.toString()))
                .andExpect(jsonPath("$.[0].tipoJurisdicionado").value(DEFAULT_TIPO_JURISDICIONADO));
    }

    @Test
    @Transactional
    public void getJurisdicionado() throws Exception {
        // Initialize the database
        jurisdicionadoRepository.saveAndFlush(jurisdicionado);

        // Get the jurisdicionado
        restJurisdicionadoMockMvc.perform(get("/api/jurisdicionados/{id}", jurisdicionado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(jurisdicionado.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.tipoJurisdicionado").value(DEFAULT_TIPO_JURISDICIONADO));
    }

    @Test
    @Transactional
    public void getNonExistingJurisdicionado() throws Exception {
        // Get the jurisdicionado
        restJurisdicionadoMockMvc.perform(get("/api/jurisdicionados/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJurisdicionado() throws Exception {
        // Initialize the database
        jurisdicionadoRepository.saveAndFlush(jurisdicionado);

        // Update the jurisdicionado
        jurisdicionado.setNome(UPDATED_NOME);
        jurisdicionado.setTipoJurisdicionado(UPDATED_TIPO_JURISDICIONADO);
        restJurisdicionadoMockMvc.perform(post("/api/jurisdicionados")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(jurisdicionado)))
                .andExpect(status().isOk());

        // Validate the Jurisdicionado in the database
        List<Jurisdicionado> jurisdicionados = jurisdicionadoRepository.findAll();
        assertThat(jurisdicionados).hasSize(1);
        Jurisdicionado testJurisdicionado = jurisdicionados.iterator().next();
        assertThat(testJurisdicionado.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testJurisdicionado.getTipoJurisdicionado()).isEqualTo(UPDATED_TIPO_JURISDICIONADO);
    }

    @Test
    @Transactional
    public void deleteJurisdicionado() throws Exception {
        // Initialize the database
        jurisdicionadoRepository.saveAndFlush(jurisdicionado);

        // Get the jurisdicionado
        restJurisdicionadoMockMvc.perform(delete("/api/jurisdicionados/{id}", jurisdicionado.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Jurisdicionado> jurisdicionados = jurisdicionadoRepository.findAll();
        assertThat(jurisdicionados).hasSize(0);
    }
}
