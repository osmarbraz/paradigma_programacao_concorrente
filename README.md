# Cálculo de Área e Perímetro de um Triângulo — Programação Concorrente

## 1. Sobre o projeto

Este projeto apresenta uma implementação em Java para calcular a área e o perímetro de um triângulo, utilizando conceitos do **paradigma de programação concorrente**.

O programa solicita ao usuário os três lados de um triângulo, verifica se os valores informados atendem às condições de existência de um triângulo e, caso sejam válidos, realiza o cálculo da área e do perímetro utilizando duas threads independentes.

Diferentemente de uma implementação sequencial, na qual as operações são executadas uma após a outra, nesta implementação os cálculos da área e do perímetro são executados por tarefas concorrentes.

O objetivo é demonstrar como a concorrência pode ser utilizada para dividir o processamento de um programa em tarefas independentes, permitindo que diferentes operações sejam executadas de forma concorrente.

## 2. Paradigma de programação concorrente

A programação concorrente é um paradigma que permite estruturar um programa em diferentes tarefas que podem progredir durante períodos de tempo sobrepostos.

Essas tarefas podem ser executadas de maneira intercalada ou, dependendo do hardware e do ambiente de execução, simultaneamente.

Em Java, uma das formas de implementar concorrência é por meio da classe `Thread`, que permite criar e executar tarefas independentes.

Entre seus principais conceitos estão:

- **Thread:** representa uma linha de execução dentro de um programa.
- **Concorrência:** permite que diferentes tarefas tenham sua execução intercalada ou sobreposta.
- **Execução independente:** possibilita dividir um programa em tarefas que realizam operações distintas.
- **Método start():** inicia uma nova thread e permite que sua execução ocorra independentemente da thread que a criou.
- **Método join():** faz com que a thread que realizou a chamada aguarde o término de outra thread.

Neste projeto, são criadas duas threads: uma responsável pelo cálculo do perímetro e outra responsável pelo cálculo da área do triângulo.

É importante diferenciar concorrência de paralelismo. A concorrência trata da organização de tarefas que podem progredir de forma sobreposta. O paralelismo ocorre quando diferentes tarefas são efetivamente executadas ao mesmo tempo, por exemplo, em diferentes núcleos do processador.

## 3. Funcionalidades

O programa realiza as seguintes operações:

1. Solicita ao usuário os valores dos três lados do triângulo.
2. Verifica se os lados são positivos.
3. Verifica se os lados atendem às condições de existência de um triângulo.
4. Cria uma thread para calcular o perímetro.
5. Cria outra thread para calcular a área utilizando a fórmula de Heron.
6. Inicia a execução das duas threads.
7. Aguarda o término das duas tarefas.
8. Apresenta uma mensagem informando que os cálculos foram finalizados.

## 4. Regras matemáticas utilizadas

### 4.1. Condição de existência de um triângulo

Para que três segmentos formem um triângulo, é necessário que:

- `a > 0`, `b > 0` e `c > 0`;
- `a < b + c`;
- `b < a + c`;
- `c < a + b`.

Se todas essas condições forem satisfeitas, o triângulo é considerado válido.

### 4.2. Cálculo do perímetro

O perímetro corresponde à soma dos três lados:

`P = a + b + c`

### 4.3. Cálculo da área — Fórmula de Heron

Primeiramente, calcula-se o semiperímetro:

`s = (a + b + c) / 2`

Em seguida, aplica-se a fórmula de Heron:

`A = √[s × (s - a) × (s - b) × (s - c)]`

## 5. Tecnologias utilizadas

- **Linguagem:** Java.
- **Gerenciamento e construção:** Apache Maven.
- **Entrada de dados:** classe `Scanner`, da biblioteca `java.util`.
- **Concorrência:** classe `Thread`, da biblioteca `java.lang`.
- **Expressões lambda:** utilizadas para definir as tarefas executadas pelas threads.
- **Cálculos matemáticos:** classe `Math`, utilizada para calcular a raiz quadrada.

## 6. Estrutura do projeto

O projeto segue a estrutura convencional de aplicações Java com Maven:

```
projeto-triangulo-concorrente/
│
├── src/
│   └── main/
│       └── java/
│           └── Principal.java
│
├── pom.xml
├── .gitignore
├── README.md
└── LICENSE
```

**Descrição dos arquivos:**

- `Principal.java`: contém a implementação do programa, a criação das threads e o método principal (`main`).
- `pom.xml`: arquivo de configuração do Maven, responsável pelo gerenciamento e pela construção do projeto.
- `.gitignore`: define os arquivos e diretórios que não devem ser versionados pelo Git.
- `README.md`: apresenta a documentação do projeto.
- `LICENSE`: informa os termos de licenciamento do projeto.

## 7. Organização do código

A implementação utiliza a classe `Thread` para executar os cálculos da área e do perímetro em tarefas independentes.

### 7.1. Método verificaTriangulo()

```
public static boolean verificaTriangulo(double a, double b, double c) {
    return a > 0 && b > 0 && c > 0
            && a < b + c
            && b < a + c
            && c < a + b;
}
```

Verifica se os três lados são positivos e atendem às condições matemáticas de existência de um triângulo.

O método retorna `true` quando o triângulo é válido e `false` caso contrário.

### 7.2. Criação da thread para calcular o perímetro

```
Thread tarefaPerimetro = new Thread(() -> {
    double p = a + b + c;
    System.out.println("Perimetro: " + p);
});
```

Cria uma thread denominada `tarefaPerimetro`, responsável por calcular e apresentar o perímetro do triângulo.

A expressão lambda define as instruções que serão executadas pela nova thread.

### 7.3. Criação da thread para calcular a área

```
Thread tarefaArea = new Thread(() -> {
    double s = (a + b + c) / 2;
    double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
    System.out.println("Area: " + area);
});
```

Cria uma segunda thread, denominada `tarefaArea`, responsável por calcular e apresentar a área do triângulo utilizando a fórmula de Heron.

Essa tarefa é independente do cálculo do perímetro.

### 7.4. Inicialização das threads

```
tarefaPerimetro.start();
tarefaArea.start();
```

O método `start()` inicia a execução de cada thread.

Após essas chamadas, as duas tarefas podem progredir concorrentemente, sem que o programa principal precise executar diretamente os cálculos.

A ordem em que os resultados aparecem na saída não é garantida, pois depende do escalonamento das threads.

### 7.5. Sincronização com join()

```
tarefaPerimetro.join();
tarefaArea.join();
```

O método `join()` faz com que a thread principal aguarde o término das duas tarefas.

Dessa forma, a mensagem de finalização somente será apresentada depois que os cálculos da área e do perímetro forem concluídos.

```
System.out.println("Calculos finalizados!");
```

Essa etapa é importante para garantir que o programa não apresente a mensagem de finalização antes da conclusão das tarefas concorrentes.

## 8. Como executar o projeto

### Pré-requisitos

Para executar o projeto, é necessário ter instalado:

- Java JDK.
- Apache Maven.
- Git (opcional, para clonar o repositório).

### Clonar o repositório

```
git clone URL_DO_REPOSITORIO
```

Acesse o diretório do projeto:

```
cd NOME_DO_PROJETO
```

### Compilar o projeto

Execute o comando:

```
mvn clean compile
```

O Maven realizará a limpeza dos arquivos de compilação anteriores e compilará os arquivos-fonte do projeto.

### Executar o programa

Considerando que a classe `Principal` não possui declaração de pacote, o programa poderá ser executado com:

```
mvn exec:java -Dexec.mainClass="Principal"
```

Esse comando utiliza o plugin Exec do Maven.

Caso o plugin ainda não esteja configurado no projeto, será necessário adicioná-lo ao `pom.xml`.

## 9. Exemplo de execução

### Exemplo 1 — Triângulo válido

**Entrada:**

```
Lado A: 3
Lado B: 4
Lado C: 5
```

**Saída possível:**

```
Triangulo valido!
Perimetro: 12.0
Area: 6.0
Calculos finalizados!
```

**Observação:** a ordem das mensagens `Perimetro` e `Area` pode variar, pois as duas threads são executadas concorrentemente.

### Exemplo 2 — Triângulo inválido

**Entrada:**

```
Lado A: 2
Lado B: 3
Lado C: 8
```

**Saída:**

```
Triangulo invalido!
```

## 10. Conceitos de programação demonstrados

Este projeto permite estudar e identificar os seguintes conceitos:

- Paradigma de programação concorrente.
- Threads e linhas de execução.
- Criação de tarefas concorrentes.
- Classe `Thread`.
- Expressões lambda.
- Método `start()`.
- Método `join()`.
- Concorrência e paralelismo.
- Execução independente de tarefas.
- Entrada e saída de dados com `Scanner`.
- Estruturas condicionais.
- Métodos e modularização.
- Operações matemáticas.

## 11. Considerações finais

A implementação demonstra a utilização da programação concorrente na resolução de um problema matemático simples.

Ao dividir o processamento em duas threads independentes, o programa permite que os cálculos da área e do perímetro sejam executados de forma concorrente.

O método `start()` inicia as tarefas, enquanto o método `join()` garante que o programa principal aguarde a conclusão de ambas antes de apresentar a mensagem final.

Embora os cálculos deste exemplo sejam simples e não proporcionem necessariamente ganho de desempenho com a utilização de threads, a implementação permite compreender os fundamentos da concorrência em Java.

Este projeto também servirá como referência para comparar diferentes paradigmas de programação, permitindo observar como o mesmo problema pode ser resolvido por abordagens distintas, como programação estruturada, funcional, orientada a eventos e orientada a objetos.