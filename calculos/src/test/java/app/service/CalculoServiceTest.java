package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.entity.Entrada;
import app.entity.Saida;
import app.repository.CalculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculoServiceTest {

	@Autowired
	CalculoService calculoService;

	@Mock
	private CalculoRepository calculoRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("TESTE UNITÁRIO DE SOMA DE 2 + 3")
	void testarSoma() {

		List<Integer> lista = new ArrayList<>();
		lista.add(2);
		lista.add(3);


		int retorno = this.calculoService.somar(lista);

		assertEquals(5, retorno);

	}

	@Test
	@DisplayName("TESTE UNITÁRIO DE LANÇAMENTO DE EXCEÇÃO/ERRO")
	void testarSoma2() {

		List<Integer> lista = new ArrayList<>();
		lista.add(null);
		lista.add(4);

		assertThrows(Exception.class, () -> {
			int retorno = this.calculoService.somar(lista);
		});


	}

	
	
	@Test
	@DisplayName("TESTANDO MAIOR NÚMERO ENTRE 4,5,8,1")
	void testarMaiorNumero() {
		
		List<Integer> lista = new ArrayList<>();
		lista.add(4);
		lista.add(5);
		lista.add(8);
		lista.add(1);
		
		int retorno = this.calculoService.buscarMaiorNumero(lista);
		
		assertEquals(8, retorno);
		
	}
	@Test
	@DisplayName("TESTANDO O MÉTODO CALCULAR COM ENTRADA VÁLIDA")
	void testarCalcularComEntradaValida() {
		List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
		Entrada entrada = new Entrada();
		entrada.setLista(lista);

		Saida saida = calculoService.calcular(entrada);

		assertEquals(15, saida.getSoma());
		assertEquals(5, saida.getMaiorNumeroLista());
	}

	@Test
	@DisplayName("TESTANDO O MÉTODO CALCULAR COM LISTA NULA")
	void testarCalcularComListaNula() {
		Entrada entrada = new Entrada();
		entrada.setLista(null);

		IllegalArgumentException thrown = assertThrows(
				IllegalArgumentException.class,
				() -> calculoService.calcular(entrada),
				"A lista de entrada não pode ser nula ou vazia"
		);

		assertEquals("A lista de entrada não pode ser nula ou vazia", thrown.getMessage());
	}

	@Test
	@DisplayName("TESTANDO O MÉTODO CALCULAR COM LISTA VAZIA")
	void testarCalcularComListaVazia() {
		Entrada entrada = new Entrada();
		entrada.setLista(new ArrayList<>());

		IllegalArgumentException thrown = assertThrows(
				IllegalArgumentException.class,
				() -> calculoService.calcular(entrada),
				"A lista de entrada não pode ser nula ou vazia"
		);

		assertEquals("A lista de entrada não pode ser nula ou vazia", thrown.getMessage());
	}


}
