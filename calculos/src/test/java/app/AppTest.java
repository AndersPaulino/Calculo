package app;


import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AppTest {
    @Autowired
    private ApplicationContext applicationContext;
    @Test
    void contextLoads() {
        assertTrue(true);

        assertNotNull(applicationContext, "O contexto da aplicação não deve ser nulo");

        App appBean = applicationContext.getBean(App.class);
        assertNotNull(appBean, "A aplicação deve iniciar e o bean App deve estar presente no contexto");
    }
    @Test
    void mainMethodTest() {
        App.main(new String[] {});
        assertTrue(true, "O método main executou sem erros.");
    }
}
