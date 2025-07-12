# 💳 Bank-System

O **Bank-System** é uma aplicação de terminal desenvolvida em Java que simula as operações básicas de um sistema bancário. O projeto tem como objetivo aplicar conceitos de Programação Orientada a Objetos (POO), como herança, encapsulamento e polimorfismo, através da criação e gestão de contas bancárias com persistência local em ficheiros de texto.

## 🚀 Funcionalidades

- Registo e login de utilizadores
- Criação de contas normais e contas de estudante
- Depósitos e levantamentos
- Consulta de saldo
- Geração de faturas
- Área de administração para gestão de contas
- Armazenamento local em ficheiros `.txt`

## 🧠 Tecnologias Utilizadas

- Java (POO)
- Armazenamento local via ficheiros `.txt`
- Interface por linha de comandos (CLI)
- 💳 Bank-System
O Bank-System é uma aplicação de terminal desenvolvida em Java que simula as operações básicas de um sistema bancário. O projeto tem como objetivo aplicar conceitos de Programação Orientada a Objetos (POO), como herança, encapsulamento e polimorfismo, através da criação e gestão de contas bancárias com persistência local em ficheiros de texto.

🗂 **Estrutura do Projeto**
Bank-System/

src/ — Código-fonte Java
App.java — Classe principal que inicializa o sistema
Utilizador.java — Classe base com dados e métodos comuns a todos os utilizadores
Administrador.java — Subclasse de Utilizador com permissões administrativas
Conta.java — Classe que representa uma conta bancária genérica
ContaEstudante.java — Subclasse de Conta com regras específicas para estudantes
Fatura.java — Classe responsável por gerar e armazenar faturas
bin/ — Ficheiros compilados (.class)
Dados.txt — Ficheiro que guarda os dados das contas
logins.txt — Ficheiro que armazena os logins dos utilizadores


🧪 **Como Correr**

Compila os ficheiros Java:
javac src/*.java -d bin

Corre a aplicação:
java -cp bin App

Certifica-te de que tens o Java instalado corretamente (java -version).

🎯 **Objetivo do Projeto**

Este projeto foi desenvolvido com o objetivo de consolidar conhecimentos em Java e Programação Orientada a Objetos (POO). Ao simular um sistema bancário real, ele serve como exercício prático para reforçar conceitos como:

-Encapsulamento
-Herança
-Polimorfismo
-Leitura e escrita em ficheiros
-Lógica de autenticação e permissões

📚 **Possíveis Melhorias Futuras**

• Interface gráfica com JavaFX ou Swing
• Substituição de ficheiros .txt por base de dados (ex: SQLite ou MySQL)
• Validação de dados mais robusta
• Criptografia de passwords
• Exportação de relatórios de fatura em PDF

👤 **Autor**
Desenvolvido por Leandro Costa
