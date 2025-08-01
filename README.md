# 🧪 junit-mockito

Repositório criado com o objetivo de estudar testes automatizados em Java utilizando **JUnit 4**, **Mockito** e ferramentas complementares como **DataBuilders** e **PowerMock**.

---

## 🎯 Objetivo

Aprofundar o conhecimento sobre testes de unidade e de integração no ecossistema Java, aplicando boas práticas de escrita de testes automatizados em cenários reais, como:

- Validação de regras de negócio  
- Mock de dependências externas  
- Isolamento de testes  
- Tratamento de exceções  

---

## 🛠 Tecnologias e Ferramentas

- **Java 8**
- **JUnit 4**: Framework principal de testes unitários  
- **Mockito**: Simulação de objetos e comportamentos  
- **PowerMock**: Mock de métodos estáticos e construtores  
- **DataBuilders**: Criação fluente de objetos de teste
  
---

## 📁 Estrutura

O projeto é dividido em:

- `entities/` – Entidades como `Usuario`, `Filme`, `Locacao`  
- `services/` – Lógica de negócio, como `LocacaoService`  
- `exceptions/` – Exceções customizadas  
- `daos/` – Simulação de persistência com DAO Fake  
- `test/` – Testes com cobertura de regras, exceções e integração  

---

## 📚 Conteúdo Estudado

- Testes com **JUnit**: estrutura, assertions, exceções  
- Criação de objetos com **DataBuilder Pattern**  
- Uso de **Mockito** para mocks e spies  
- Mock de métodos estáticos com **PowerMock**  
- Análise de **cobertura de testes**  

---

## 🚀 Como executar

```bash
# Clone o projeto
git clone https://github.com/queirogaraffael/junit-mockito.git

# Navegue até o diretório
cd junit-mockito

# Compile e execute os testes
mvn clean test
```

## 📌 Observação

Este projeto tem fins exclusivamente educacionais e não possui integração com banco de dados real ou APIs externas.
