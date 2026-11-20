# Sistema de Gestão de Biblioteca Universitária

- **Disciplina:** Projeto de Bloco: Desenvolvimento Back-End [26E4_5] · Instituto Infnet
- **Aluno:** Lidiano de Jesus Santos
- **Professor:** Orlando Fonseca Guilarte
- **Trilha:** Java + Spring Boot

Sistema para gerenciar o acervo de uma biblioteca universitária (livros e exemplares), os membros da comunidade acadêmica (alunos, professores e funcionários) e as operações de consulta, empréstimo, devolução, reserva e cobrança de multas por atraso.

O projeto evolui ao longo do bloco: começa como um programa standalone de console (TP1) e chega a um serviço web com API, persistência e autenticação em Spring Boot.

## Etapa atual: TP1 – Requisitos, casos de uso e programa inicial

| Entrega | Onde está |
|---|---|
| Documento de requisitos (RF, RNF, regras de negócio), casos de uso e justificativa de escopo | [`docs/TP1_Requisitos_Casos_de_Uso.docx`](docs/TP1_Requisitos_Casos_de_Uso.docx) |
| Diagrama de casos de uso | Figura 1 do documento (seção 3.1) |
| Configuração do ambiente (JDK 21) | [`docs/img/ambiente-jdk.png`](docs/img/ambiente-jdk.png) |
| Programa standalone inicial | [`src/Main.java`](src/Main.java) |

### O programa

Programa de console, ainda **sem orientação a objetos**, que aplica tipos primitivos, operadores e controle de fluxo sobre as regras de negócio do domínio:

| Opção | Função | Regra |
|---|---|---|
| 1 | Verificar se o membro pode fazer um novo empréstimo | RN03 – máximo de 3 empréstimos ativos |
| 2 | Calcular o prazo de devolução, com virada de mês e de ano | RN01 – prazo de 14 dias corridos |
| 3 | Calcular a multa por atraso | RN02 – multa por dia corrido de atraso |

Entradas inválidas (valores negativos, datas inexistentes, texto no lugar de número) são recusadas com mensagem explicativa (RNF01). As demais regras de negócio dependem de estado persistente e serão implementadas a partir do TP2.

## Como executar

**Requisito:** JDK 21 (LTS).

**Pelo IntelliJ IDEA:** abra a pasta do projeto, marque `src` como *Sources Root* e execute `Main`.

**Pelo terminal**, a partir da pasta do projeto:

```bash
javac -d out src/Main.java
java -cp out Main
```

## Estrutura

```
├── docs/   documento de requisitos, casos de uso e imagens
└── src/    código-fonte do programa standalone
```
