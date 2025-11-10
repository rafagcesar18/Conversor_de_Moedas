# Conversor_de_Moedas
É uma pequena aplicação de conversão de cambio entre quatro moedas distintas; USD/BRL/ARS/COP.

Conversor de Moedas em Java

Um simples e funcional conversor de moedas desenvolvido em Java 17+, que consome a API pública da ExchangeRate API
 para obter taxas de câmbio atualizadas em tempo real.

Funcionalidades

Conversão entre moedas:

Dólar (USD) ↔ Real Brasileiro (BRL)

Dólar (USD) ↔ Peso Argentino (ARS)

Dólar (USD) ↔ Peso Colombiano (COP)

Interface via console interativa e intuitiva.

Consultas em tempo real através da ExchangeRate API.

Tratamento de erros e mensagens informativas ao usuário.

Estrutura do Projeto:

ConversorApp.java        -> Classe principal (menu e interação com o usuário)
Conversor.java           -> Classe de serviço responsável por consultar a API
ExchangeRateApi.java     -> Record que modela o retorno da API

Tecnologias Utilizadas:

Java 17 ou superior

Gson (Google JSON Library) para parsear o JSON da API

ExchangeRate API para obter as taxas de câmbio atualizadas

HTTP Client nativo do Java (java.net.http)

Instalação e Execução
🔹 1. Clone o repositório
git clone https://github.com/rafagcesar18/conversor_de_moedas.git
cd conversor_de_moedas

🔹 2. Baixe a biblioteca Gson

Caso não use Maven, baixe o .jar do Gson:

gson-2.10.1.jar (download direto)

Salve o arquivo na mesma pasta dos .java.

🔹 3. Compile o projeto
javac -cp gson-2.10.1.jar *.java

🔹 4. Execute o conversor

No Windows:

java -cp .;gson-2.10.1.jar ConversorApp


No Linux / macOS:

java -cp .:gson-2.10.1.jar ConversorApp

Exemplo de Uso
########## CONVERSOR DE MOEDAS ##########
1 - Dólar (USD) --> Peso Argentino (ARS)
2 - Peso Argentino (ARS) --> Dólar (USD)
3 - Dólar (USD) --> Real Brasileiro (BRL)
4 - Real Brasileiro (BRL) --> Dólar (USD)
5 - Dólar (USD) --> Peso Colombiano (COP)
6 - Peso Colombiano (COP) --> Dólar (USD)
7 - Sair
Escolha uma opção: 3
Digite o valor em USD: 10
O valor de 10.00 USD convertido para BRL é: 55.32
********************************

Conceitos Aplicados

Programação Orientada a Objetos (POO)

Consumo de APIs REST em Java

Manipulação de JSON com Gson

Estrutura modular e reutilizável

Uso de Records (Java 16+)

Licença

Este projeto é distribuído sob a licença MIT License

Autor

Rafael Gomes Cesar
