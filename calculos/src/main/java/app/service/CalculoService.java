package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Entrada;
import app.entity.Saida;
import app.repository.CalculoRepository;

@Service
public class CalculoService {
	
	@Autowired
	private CalculoRepository calculoRepository;
	
	
	
	public Saida calcular(Entrada entrada) {

		if (entrada.getLista().isEmpty()) {
			throw new IllegalArgumentException("A lista de entrada não pode ser nula ou vazia");
		}

		Saida saida = new Saida();
		saida.setSoma(this.somar(entrada.getLista()));
		saida.setMaiorNumeroLista(this.buscarMaiorNumero(entrada.getLista()));
		
		
		return saida;
	}
	
	
	public List<Saida> findAll(){
		return calculoRepository.findAll();
	}
	
	public int somar(List<Integer> lista) {
		
		int soma = 0;
		
		for (int i = 0; i < lista.size(); i++) {
			soma+= lista.get(i);
		}
		
		return soma;
	}
	
	public int buscarMaiorNumero(List<Integer> lista) {
		int maiorNumero = 0;
		
		for (int i = 0; i < lista.size(); i++) {
			if(lista.get(i) > maiorNumero)
				maiorNumero = lista.get(i);
		}
		
		return maiorNumero;
		
	}
	

}
