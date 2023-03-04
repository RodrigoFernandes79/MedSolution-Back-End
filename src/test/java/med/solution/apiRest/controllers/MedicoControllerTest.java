package med.solution.apiRest.controllers;

import med.solution.apiRest.models.endereco.DadosEndereco;
import med.solution.apiRest.models.endereco.Endereco;
import med.solution.apiRest.models.medico.DadosCadastraisMedico;
import med.solution.apiRest.models.medico.DadosDetalhamentoMedico;
import med.solution.apiRest.models.medico.enums.Especialidade;
import med.solution.apiRest.repositories.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<DadosCadastraisMedico> dadosCadastraisMedicoJacksonTester;
    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJacksonTester;
    @MockBean
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Deveria retornar codigo http 400 quando informaçoes estao invalidas")
    @WithMockUser
    void cadastrarMedico_cenario1() throws Exception {
        var response = mockMvc.perform(post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar codigo http 200 OK quando informaçoes estao validas")
    @WithMockUser
    void cadastrarMedico_cenario2() throws Exception {
        var especialidade = Especialidade.GINECOLOGIA;
        var dadosEndereco = new DadosEndereco("Rua Augusta", null,
                null, "Perdizes", "Sao Paulo", "SP", "51302569");
        var response = mockMvc.perform(post(
                        "/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastraisMedicoJacksonTester.write(
                                new DadosCadastraisMedico(
                                        "Medico", "medico@med.com", "84996547589",
                                        "154562", especialidade, dadosEndereco)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoMedicoJacksonTester.write(
                new DadosDetalhamentoMedico(null, "Medico", "medico@med.com",
                        "154562", "84996547589", especialidade, new Endereco(dadosEndereco))
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }
}