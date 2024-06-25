package app.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SaidaEntityTest {
    @Test
    @DisplayName("Teste do getId e setId")
    void testGetSetId() {
        Saida saida = new Saida();

        saida.setId(1L);
        assertEquals(1L, saida.getId());

        assertEquals(1L, saida.getId());

        saida.setId(10L);
        assertEquals(10L, saida.getId());
    }
}
