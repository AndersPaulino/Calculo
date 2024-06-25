package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import app.entity.Entrada;
import app.service.CalculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Saida;

@SpringBootTest
public class CalculoControllerTest {
	
	@Autowired
	CalculoController calculoController;

	@MockBean
	private CalculoService calculoService;

	@BeforeEach
	void setup() {

		List<Saida> lista = new ArrayList<>();
		lista.add(new Saida(1L, 10, 3));
		lista.add(new Saida(2L, 20, 6));

		when(calculoService.findAll()).thenReturn(lista);

		Entrada entrada = new Entrada();
		entrada.setLista(List.of(1, 2, 3, 4, 5));
		Saida saida = new Saida();
		saida.setSoma(15);
		saida.setMaiorNumeroLista(5);

		when(calculoService.calcular(any(Entrada.class))).thenReturn(saida);
	}

	@Test
	@DisplayName("TESTE DE INTEGRAÇÃO MOCANDO O SERVIÇO PARA O MÉTODO FINDALL")
	void testarFindAll() {
		ResponseEntity<List<Saida>> response = calculoController.findAll();
		List<Saida> lista = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, lista.size());
		assertEquals(10, lista.get(0).getSoma());
		assertEquals(20, lista.get(1).getSoma());
	}

	@Test
	@DisplayName("TESTE DE INTEGRAÇÃO MOCANDO O SERVIÇO PARA O MÉTODO CALCULAR")
	void testarCalcular() {
		Entrada entrada = new Entrada();
		entrada.setLista(List.of(1, 2, 3, 4, 5));

		ResponseEntity<Saida> response = calculoController.calcular(entrada);
		Saida saida = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(15, saida.getSoma());
		assertEquals(5, saida.getMaiorNumeroLista());
	}

	@Test
	@DisplayName("TESTE DE INTEGRAÇÃO PARA O MÉTODO CALCULAR LANÇANDO EXCEÇÃO")
	void testarCalcularComExcecao() {
		Entrada entrada = new Entrada();
		entrada.setLista(new ArrayList<>());  // Lista vazia para lançar exceção

		when(calculoService.calcular(any(Entrada.class))).thenThrow(new IllegalArgumentException("A lista de entrada não pode ser nula ou vazia"));

		ResponseEntity<Saida> response = calculoController.calcular(entrada);

		assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste findAll com exceção")
	void testFindAllException() {
		doThrow(RuntimeException.class).when(calculoService).findAll();

		ResponseEntity<List<Saida>> response = calculoController.findAll();

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(null, response.getBody());
	}

}
