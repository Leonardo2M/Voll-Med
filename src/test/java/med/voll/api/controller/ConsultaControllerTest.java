package med.voll.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
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

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.service.AgendaDeConsultas;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JacksonTester<DadosAgendamentoConsulta> agendamentoJson;
	
	@Autowired
	private JacksonTester<DadosDetalhamentoConsulta> detalhamentoJson;
	
	@MockBean
	private AgendaDeConsultas agendaDeConsultas;

	@Test
	@DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
	@WithMockUser
	void agendar_cenario1() throws Exception {
	    var response = mvc.perform(post("/consultas"))
	    		.andReturn().getResponse();
	    
	    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

	}
	
	@Test
	@DisplayName("Deveria devolver codigo http200 quando consulta for agendada")
	@WithMockUser
	void agendar_cenario2() throws Exception {
		
		var data = LocalDateTime.now().plusHours(1);
		var especialidade = Especialidade.CARDIOLOGIA;
		
		var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 1l, 1l, data);
		
		when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);
		
	    var response = mvc.perform(post("/consultas")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(agendamentoJson.write(new DadosAgendamentoConsulta(1l, 1l, data, especialidade))
	    		.getJson()))
	    		.andReturn().getResponse();
	    
	    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	    
	    var jsonEsperado = detalhamentoJson.write(dadosDetalhamento).getJson();
	    
	    assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
	    
	    		

	}
	

}
