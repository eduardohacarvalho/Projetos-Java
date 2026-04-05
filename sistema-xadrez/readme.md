# ♟️ Sistema de Xadrez

Jogo de xadrez completo desenvolvido em Java, executado via terminal com interface colorida.

## 🚀 Tecnologias

- Java
- Programação Orientada a Objetos
- Maven

## 📁 Estrutura do Projeto

src/main/java/com/projeto_java/Sistema_Xadrez 

├── chess # Lógica do xadrez (partida, peças, posições) 

│ └── pieces # Peças do jogo

├── boardgame # Tabuleiro e posições genéricas 

└── UI.java # Interface do terminal

## ♟️ Peças Implementadas

| Símbolo | Peça |
|---------|------|
| R | Rei (King) |
| D | Dama (Queen) |
| T | Torre (Rook) |
| B | Bispo (Bishop) |
| C | Cavalo (Knight) |
| P | Peão (Pawn) |

## 🎮 Funcionalidades

- Tabuleiro 8x8 com interface colorida no terminal
- Brancas (branco) vs Pretas (amarelo)
- Movimentos possíveis destacados em azul
- Detecção de cheque e xeque-mate
- Peças capturadas exibidas durante a partida
- Movimentos especiais:
    - Roque (Castling)
    - En Passant
    - Promoção do Peão (escolha entre Dama, Torre, Bispo ou Cavalo)

## ⚙️ Pré-requisitos

- Java 17+
- Maven
- Terminal com suporte a cores ANSI

## 🔧 Como rodar

1. Clone o repositório: git clone https://github.com/SEU_USUARIO/sistema-xadrez.git

2. Compile o projeto: mvn compile

3. Execute: mvn spring-boot:run

## 🕹️ Como jogar

1. O jogo exibe o tabuleiro no terminal
2. Digite a posição de origem da peça (ex: e2)
3. Os movimentos possíveis são destacados em azul
4. Digite a posição de destino (ex: e4)
5. Em caso de promoção do peão, escolha a peça: B (Bispo), C (Cavalo), D (Dama) ou T (Torre)
6. O jogo termina com CHECKMATE quando um rei não tem mais movimentos

## 📐 Arquitetura

O projeto segue uma arquitetura em camadas separando as responsabilidades: boardgame contém a lógica genérica do tabuleiro sem conhecimento das regras do xadrez, enquanto chess contém toda a lógica específica do xadrez, como movimentos especiais, cheque e xeque-mate.

## 👨‍💻 Autor

Eduardo — https://github.com/eduardohacarvalho

Projeto desenvolvido durante o curso Java COMPLETO Programação Orientada a Objetos + Projetos ministrado por Nelio Alves.