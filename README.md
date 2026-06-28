# 🚀 Stratek API — Sistema de Gestão de Ordens de Serviço

[![Java](https://shields.io)](https://oracle.com)
[![Spring Boot](https://shields.io)](https://spring.io)
[![MySQL](https://shields.io)](https://mysql.com)

API REST robusta focada na gestão completa de **Ordens de Serviço (OS)**, cobrindo fluxos operacionais e financeiros complexos de uma assistência técnica. Este projeto foi desenvolvido com foco em alta maturidade técnica, padrões arquiteturais modernos e isolamento de regras de negócio.

---

## 🎯 Objetivos do Projeto

- **Clean Code & Tipagem Rígida:** Uso de recursos modernos do Java 21 (como `records` para DTOs imutáveis e Enums estruturados).
- **Design de Software:** Blindagem de endpoints e isolamento total das regras de negócio.
- **Portfólio Profissional:** Construção de uma solução pronta para o mercado (nível corporativo), pronta para integração com front-ends modernos.

---

## 🧠 Arquitetura do Sistema

O projeto adota uma **Arquitetura em Camadas inspirada nos princípios da Arquitetura Hexagonal e Clean Architecture**. O núcleo de domínio e os casos de uso (*usecases*) são isolados de dependências e tecnologias externas, facilitando a manutenibilidade e testes isolados.

### 📦 Estrutura de Responsabilidades (Pacotes)

- **`domain` / `entity`** ➔ Modelo de domínio puro e mapeamento de entidades JPA (tabelas do banco).
- **`usecase` / `service`** ➔ Contratos (Portas) e implementações das regras de negócio isoladas.
- **`controller`** ➔ Adaptadores de entrada REST (Spring MVC) responsáveis pela exposição dos endpoints.
- **`dto`** ➔ Objetos de Transferência de Dados imutáveis (`records`) que blindam a entrada e saída da API.
- **`mapper`** ➔ Camada dedicada à conversão limpa entre objetos de banco (`entity`) e transporte (`DTO`).
- **`exception`** ➔ Manipulador global com `@RestControllerAdvice` para ocultação de stacktraces e padronização de erros.
- **`config` / `security`** ➔ Configurações gerais da aplicação e infraestrutura.
- **`enums`** ➔ Domínios fechados e controlados (evitando strings livres para status e prioridades).

---

## 🛠️ Stack Tecnológica

- **Linguagem:** Java 21 (LTS)
- **Framework Principal:** Spring Boot 3
- **Persistência & ORM:** Spring Data JPA / Hibernate
- **Validação:** Bean Validation (`jakarta.validation`)
- **Banco de Dados:** MySQL
- **Utilitários:** Lombok / Maven
- **Documentação:** Swagger UI (OpenAPI 3)

---

## ⚙️ Regras de Negócio Implementadas

- **Cálculos no Backend:** Todos os totais financeiros (produtos + mão de obra) são calculados no servidor, garantindo precisão matemática absoluta com `BigDecimal`.
- **Controle de Estados Independente:** Separação rígida entre o **Status Operacional** da OS e o **Status Financeiro** do pagamento.
- **Endpoints de Propósito Único:** Atualizações críticas (como alteração de status ou baixa em pagamentos) possuem rotas específicas, eliminando o risco de sobreescrita de dados acidental.
- **Entidades Dependentes:** Equipamentos e itens de serviços são gerenciados de forma ciclo-dependente da Ordem de Serviço principal.

---

## 📦 Módulos do Domínio

- **`Customer` & `Address`:** Cadastro completo de clientes com seus respectivos endereços vinculados.
- **`Product` & `Service`:** Controle de catálogo de peças/produtos em estoque e precificação de serviços de mão de obra.
- **`Order Service` (Principal):** Agrupador central do fluxo que unifica cliente, equipamentos, produtos utilizados, serviços prestados e cálculos operacionais.

### 🔁 Fluxo de Estados da OS
```text
CREATED ➔ IN_PROGRESS ➔ COMPLETED ➔ DELIVERED
```

---

## 🔄 Endpoints Principais (API Rest)

### 🧾 Gestão de Ordens de Serviço
- `POST /api/v1/orders` ➔ Abertura de uma nova Ordem de Serviço.
- `GET /api/v1/orders` ➔ Listagem paginada de ordens.
- `GET /api/v1/orders/{id}` ➔ Detalhes completos de uma OS específica (via `UUID`).
- `DELETE /api/v1/orders/{id}` ➔ Cancelamento/Exclusão do registro.

### 🎯 Atualizações Específicas (Isolamento de Estado)

| Método | Endpoint | Função / Responsabilidade |
| :--- | :--- | :--- |
| **PUT** | `/api/v1/orders/{id}` | Atualização de dados cadastrais básicos da OS |
| **PUT** | `/api/v1/orders/{id}/status` | Transição controlada do status operacional (`Enum`) |
| **PUT** | `/api/v1/orders/{id}/payment` | Atualização do status financeiro independente |

---

## 📖 Documentação da API

Com a aplicação rodando localmente, a especificação dos endpoints e testes em tempo real podem ser acessados via Swagger:

```text
http://localhost:8080/swagger-ui.html
```

---

## ▶️ Como Executar o Projeto Localmente

1. Clone o repositório:
```bash
git clone https://github.com/d97gomes/stratek-os.git
```

2. Certifique-se de ter o **Java 21** instalado e configure as credenciais do seu banco de dados **MySQL** no arquivo `src/main/resources/application.properties` (ou `yml`).

3. Compile e execute a aplicação via Maven:
```bash
mvn clean install
mvn spring-boot:run
```
