# Blockchain P2P em Java

Este é um projeto acadêmico que implementa uma blockchain simples com comunicação peer-to-peer usando Java puro.

## Funcionalidades:
- Mineração de blocos com prova de trabalho
- Transações entre pares
- Comunicação via sockets (P2P)
  
## ProblemaAtual_SuportaSomente2Computadores:
- Cada nó tem sua própria blockchain local
- O nó aceita conexões e imprime as mensagens recebidas, mas não faz nada para sincronizar sua blockchain com os outros nós
- As transações são locais a cada nó e não são compartilhadas automaticamente
