# Projeto de Software — Assinatura de Feira

## 📌 Descrição

Este projeto tem como objetivo modelar e implementar um sistema de assinatura semanal de entrega de frutas, legumes e verduras, com base no caso de uso "Assinar Serviço de Feira".

O sistema permite que um assinante:
- realize autenticação via celular (simulada)
- escolha um plano de assinatura
- selecione produtos (frutas, legumes e verduras)
- monte uma cesta semanal
- informe endereço de entrega
- realize pagamento via cartão de crédito
- receba confirmação com número de protocolo

---

## 🎯 Objetivo

Aplicar conceitos de Engenharia de Software e Orientação a Objetos, incluindo:

- Modelagem UML
- Diagrama de Classes de Domínio
- Encapsulamento
- Alta coesão
- Baixo acoplamento
- Mapeamento de modelo UML para código Java

---

## 🧱 Estrutura do Projeto

O projeto foi desenvolvido em Java e contém:

- Classes de domínio:
  - Assinante
  - Assinatura
  - Plano
  - Cesta
  - ItemCesta
  - Produto (Fruta, Legume, Verdura)
  - EnderecoEntrega
  - Pagamento
  - CartaoCredito
  - Protocolo

- Enumerações:
  - StatusAssinatura
  - StatusCesta
  - StatusPagamento

- Classe principal:
  - Main (simulação do fluxo principal)

---

## 🔄 Fluxo Principal Implementado

O sistema simula o fluxo principal do caso de uso:

1. Assinante informa celular
2. Sistema envia código (simulado)
3. Assinante valida código
4. Escolha de plano
5. Seleção de produtos (frutas, legumes e verduras)
6. Montagem da cesta
7. Informar endereço de entrega
8. Pagamento via cartão de crédito
9. Aprovação da assinatura
10. Geração de protocolo

---

## ▶️ Como Executar

1. Certifique-se de ter o Java instalado (JDK 8 ou superior)

2. Compile o código:

```bash
javac Main.java