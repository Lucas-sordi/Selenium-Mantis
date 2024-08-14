# Selenium - Mantis

Projeto de automação de testes para o Mantis utilizando Selenium.

## Tecnologias
- [Java](https://www.java.com/pt-BR/)

- [Selenium](https://www.selenium.dev/)

- [Cucumber](https://cucumber.io/)
  
- [JUnit5](https://junit.org/junit5/)

## Execução
É necessário ter um arquivo .env com dados de login e senha. O .env deve estar no mesmo formato do arquivo `example.env`, disponível no repositório.

Para executar os testes:

```bash
  mvn clean test
```

## Report 

O report dos testes fica disponível em `target/cucumber-reports.html`
