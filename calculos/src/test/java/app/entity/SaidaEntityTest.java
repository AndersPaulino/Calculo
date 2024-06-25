package app.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SaidaEntityTest {
    @Test
    @DisplayName("Teste do getId")
    void testGetId() {
        Saida saida = new Saida();

        assertEquals(0, saida.getId());
    }
}
