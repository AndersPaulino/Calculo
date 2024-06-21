# Calculo

Este projeto é uma API Spring Boot que fornece operações de cálculo simples. A API recebe uma lista de números inteiros e retorna a soma dos números e o maior número na lista. O projeto inclui endpoints REST para processar as requisições, bem como testes unitários e de integração para validar as funcionalidades.

## Atividade 03 - Testes Automatizados

Atividade de implementação de testes de integração em Controller e testes de unidades em Service.

## Estrutura do Projeto
O projeto está estruturado da seguinte forma:

* `Controller`: Contém os endpoints da API.
* `Entity`: Contém as classes que representam os dados do sistema.
* `Repository`: Interface de persistência de dados.
* `Service`: Contém a lógica de negócios.
* `Tests`: Contém os testes unitários e de integração.

## Endpoints
### CalculoController

`POST /api/calculo`
 * Descrição: Calcula a soma e o maior número de uma lista fornecida.
 * Request Body: Objeto JSON do tipo `Entrada`.
 * Response: Objeto JSON do tipo `Saida` com a soma e o maior número.
    
  Exmeplo Request:
    
  ```yaml
        {
          "id": 1,
          "lista": [1, 2, 3, 4, 5]
        }

  ```
  Exmeplo Response:
    
  ```yaml
        {
          "id": 1,
          "soma": 15,
          "maiorNumeroLista": 5
        }

  ```
`GET /api/calculo`
* Descrição: Retorna todos os cáculos realizados.
* Response: Lista de objetos JSON do tipo `Saida`.
  
#

## Classes
### Entrada
Representa a entrada da API com uma lista de números inteiros.

```java
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public class Entrada {
      private long id;
      private List<Integer> lista;
  }  
```
### Saida
Representa a saída da API com a soma e o maior número da lista.

```java
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Entity
  public class Saida {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private long id;
      private int soma;
      private int maiorNumeroLista;
  }
```
### CalculoService
Contém a lógica de negócios para calcular a soma e o maior número de uma lista.
```java
  @Service
  public class CalculoService {
      @Autowired
      private CalculoRepository calculoRepository;
  
      public Saida calcular(Entrada entrada) {
          if (entrada == null || entrada.getLista() == null || entrada.getLista().isEmpty()) {
              throw new IllegalArgumentException("A lista de entrada não pode ser nula ou vazia");
          }
  
          Saida saida = new Saida();
          saida.setSoma(this.somar(entrada.getLista()));
          saida.setMaiorNumeroLista(this.buscarMaiorNumero(entrada.getLista()));
          return saida;
      }
  
      public List<Saida> findAll() {
          return this.calculoRepository.findAll();
      }
  
      public int somar(List<Integer> lista) {
          return lista.stream().mapToInt(Integer::intValue).sum();
      }
  
      public int buscarMaiorNumero(List<Integer> lista) {
          return lista.stream().mapToInt(Integer::intValue).max().orElseThrow();
      }
  }
```
#

## Testes
### CalculoControllerTest
Testa os endpoints da API.

### CalculoServiceTest
Testa os métodos de serviço.

## Como Executar
1. Clone o repositório.
2. Navegue até o diretório do projeto.
3. Execute `mvn spring-boot:run`para iniciar a aplicação.
4. Acesse a API em `http://localhost:8080/api/calculo`.

#

## Requisitos
* Java 17+
* Maven 3.6+
* Spring Boot 2.6+
* PostgreSQL

## Dependências
* Spring Boot Starter Web
* Spring Boot Starter Data JPA
* Lombok
* PostgreSQL Driver
  
[Inicio](#calculo)<br>
