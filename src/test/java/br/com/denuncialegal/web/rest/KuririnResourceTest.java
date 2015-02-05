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
import br.com.denuncialegal.domain.Kuririn;
import br.com.denuncialegal.repository.KuririnRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the KuririnResource REST controller.
 *
 * @see KuririnResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class KuririnResourceTest {


    private static final Boolean DEFAULT_MORRESEMPRE = false;
    private static final Boolean UPDATED_MORRESEMPRE = true;

    @Inject
    private KuririnRepository kuririnRepository;

    private MockMvc restKuririnMockMvc;

    private Kuririn kuririn;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        KuririnResource kuririnResource = new KuririnResource();
        ReflectionTestUtils.setField(kuririnResource, "kuririnRepository", kuririnRepository);
        this.restKuririnMockMvc = MockMvcBuilders.standaloneSetup(kuririnResource).build();
    }

    @Before
    public void initTest() {
        kuririn = new Kuririn();
        kuririn.setMorresempre(DEFAULT_MORRESEMPRE);
    }

    @Test
    @Transactional
    public void createKuririn() throws Exception {
        // Validate the database is empty
        assertThat(kuririnRepository.findAll()).hasSize(0);

        // Create the Kuririn
        restKuririnMockMvc.perform(post("/api/kuririns")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(kuririn)))
                .andExpect(status().isOk());

        // Validate the Kuririn in the database
        List<Kuririn> kuririns = kuririnRepository.findAll();
        assertThat(kuririns).hasSize(1);
        Kuririn testKuririn = kuririns.iterator().next();
        assertThat(testKuririn.getMorresempre()).isEqualTo(DEFAULT_MORRESEMPRE);
    }

    @Test
    @Transactional
    public void getAllKuririns() throws Exception {
        // Initialize the database
        kuririnRepository.saveAndFlush(kuririn);

        // Get all the kuririns
        restKuririnMockMvc.perform(get("/api/kuririns"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(kuririn.getId().intValue()))
                .andExpect(jsonPath("$.[0].morresempre").value(DEFAULT_MORRESEMPRE.booleanValue()));
    }

    @Test
    @Transactional
    public void getKuririn() throws Exception {
        // Initialize the database
        kuririnRepository.saveAndFlush(kuririn);

        // Get the kuririn
        restKuririnMockMvc.perform(get("/api/kuririns/{id}", kuririn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(kuririn.getId().intValue()))
            .andExpect(jsonPath("$.morresempre").value(DEFAULT_MORRESEMPRE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingKuririn() throws Exception {
        // Get the kuririn
        restKuririnMockMvc.perform(get("/api/kuririns/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKuririn() throws Exception {
        // Initialize the database
        kuririnRepository.saveAndFlush(kuririn);

        // Update the kuririn
        kuririn.setMorresempre(UPDATED_MORRESEMPRE);
        restKuririnMockMvc.perform(post("/api/kuririns")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(kuririn)))
                .andExpect(status().isOk());

        // Validate the Kuririn in the database
        List<Kuririn> kuririns = kuririnRepository.findAll();
        assertThat(kuririns).hasSize(1);
        Kuririn testKuririn = kuririns.iterator().next();
        assertThat(testKuririn.getMorresempre()).isEqualTo(UPDATED_MORRESEMPRE);
    }

    @Test
    @Transactional
    public void deleteKuririn() throws Exception {
        // Initialize the database
        kuririnRepository.saveAndFlush(kuririn);

        // Get the kuririn
        restKuririnMockMvc.perform(delete("/api/kuririns/{id}", kuririn.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Kuririn> kuririns = kuririnRepository.findAll();
        assertThat(kuririns).hasSize(0);
    }
}
