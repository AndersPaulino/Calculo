package app.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EntradaTest {
    @Test
    @DisplayName("Teste do construtor sem argumentos")
    void testNoArgsConstructor() {
        Entrada entrada = new Entrada();
        assertNotNull(entrada);
    }

    @Test
    @DisplayName("Teste do construtor com argumentos")
    void testAllArgsConstructor() {
        List<Integer> lista = Arrays.asList(1, 2, 3);
        Entrada entrada = new Entrada(lista);

        assertNotNull(entrada);
        assertEquals(0, entrada.getId());
        assertEquals(lista, entrada.getLista());
    }

    @Test
    @DisplayName("Teste dos getters e setters")
    void testGettersAndSetters() {
        List<Integer> lista = Arrays.asList(4, 5, 6);
        Entrada entrada = new Entrada();
        entrada.setLista(lista);

        assertEquals(0, entrada.getId());
        assertEquals(lista, entrada.getLista());
    }
}
