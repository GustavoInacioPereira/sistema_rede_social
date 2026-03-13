<h1>Mini Rede Social (Legado)</h1>
<h2>📝 Resumo </h2>
<p>Este projeto é uma simulação de uma rede social baseada em terminal (Command Line Interface), desenvolvida puramente em Java. O sistema permite o cadastro de novos usuários, autenticação segura, criação de postagens e a exibição de uma "Linha do Tempo" (Global Feed). Mais do que uma simples aplicação de terminal, este projeto é um laboratório prático de <b>Arquitetura de Software, Paradigma Funcional (Java 8+), Estruturas de Dados de Alta Performance e Persistência de Dados (Java NIO.2)</b>, lidando com desafios reais de sincronização entre Memória RAM e Disco Rígido.</p>

<h2>🚩 Desafio Proposto</h2>
<p>Construir e refatorar uma aplicação completa saindo do paradigma Imperativo Clássico para o <b>Declarativo/Funcional</b>. O objetivo principal foi garantir um fluxo de navegação contínuo, persistência física à prova de vazamento de memória (Memory Leaks), prevenção de colisões de ID e a implementação de pipelines de dados eficientes para evitar gargalos de I/O em disco.</p>

<h3>🚀 Arquitetura e Funcionalidades do Sistema</h3>

<p>O projeto foi desenvolvido com foco estrito em boas práticas de Engenharia de Software, aplicando conceitos de Clean Code, Separação de Responsabilidades (SoC) e modernização de sintaxe.</p>

<details open>
<summary><b>🌊 Paradigma Funcional e Java Moderno (Streams API)</b></summary>
<ul>
<li><b>Pipelines de Processamento:</b> Substituição completa de laços <code>for/while</code> por <i>Streams</i> encadeadas (<code>filter</code>, <code>map</code>, <code>collect</code>), promovendo imutabilidade e leitura declarativa.</li>
<li><b>FlatMap e Transformação de Dados:</b> Utilização de <code>flatMap</code> para "esmagar" e unificar listas de posts aninhadas em uma única esteira de dados de alta performance.</li>
<li><b>Redução Matemática:</b> Simulação do <code>AUTO_INCREMENT</code> de banco de dados utilizando <code>IntStream</code> e funções de agregação como <code>mapToInt().max()</code> direto na leitura do arquivo.</li>
<li><b>Expressões Lambda e Method References:</b> Código altamente enxuto utilizando funções anônimas (<code>-></code>) e referências diretas a métodos (ex: <code>Posts::getDateHour</code>, <code>User::getEmail</code>).</li>
</ul>
</details>

<details open>
<summary><b>💾 Persistência de Dados Moderna (Java NIO.2)</b></summary>
<ul>
<li><b>Lazy Evaluation (Avaliação Preguiçosa):</b> Uso de <code>Files.lines()</code> para abrir fluxos de leitura de arquivos sob demanda, evitando o carregamento de arquivos inteiros na memória RAM e prevenindo <i>Out Of Memory Errors</i>.</li>
<li><b>Safe I/O e AutoCloseable:</b> Implementação rígida do bloco <code>try-with-resources</code>, garantindo que o sistema operacional feche as "torneiras" dos arquivos automaticamente, blindando a aplicação contra <i>Resource Leaks</i>.</li>
<li><b>Soft Delete Mutável:</b> Remoção lógica de usuários (alteração para <code>DISABLE</code>) feita diretamente na <i>Stream</i> com atualizações condicionais, seguida da reescrita instantânea do arquivo em bloco via <code>Files.write()</code>.</li>
</ul>
</details>

<details open>
<summary><b>⚡ Estruturas de Dados e Alta Performance (Cache In-Memory)</b></summary>
<ul>
<li><b>Otimização de Busca:</b> Construção de um <b>Cache em Memória</b> utilizando <code>HashSet</code> para a validação de e-mails no momento do cadastro, mapeado diretamente via Streams.</li>
<li><b>Redução de Gargalo de I/O:</b> Validações subsequentes ocorrem via "teletransporte matemático" na RAM (<code>.contains()</code> em complexidade O(1)), eliminando a necessidade de ler o disco dezenas de vezes.</li>
</ul>
</details>

<details open>
<summary><b>⏱️ Motor do Feed Global e Java Time API</b></summary>
<ul>
<li><b>Ordenação Cronológica:</b> Aplicação de <code>.sorted(Comparator.comparing())</code> atrelada diretamente ao pipeline de Streams para organizar os posts de todos os usuários baseados no tempo real.</li>
<li><b>Java Time API:</b> Uso intensivo do <code>LocalDateTime</code> e <code>DateTimeFormatter</code> (padrão ISO 8601 adaptado para visualização pt-BR e parseamento na leitura do <code>.txt</code>).</li>
</ul>
</details>

<details open>
<summary><b>🛡️ Blindagem Defensiva e UX</b></summary>
<ul>
<li><b>Sanitização de Dados Silenciosa:</b> Tratamento preventivo em todas as leituras de disco utilizando <code>.filter(line -> !line.trim().isEmpty())</code> para ignorar quebras de linha geradas por padrões de Sistemas Operacionais.</li>
<li><b>Micro-Loops de Retenção:</b> A classe utilitária <code>VerifyType</code> blinda o sistema contra o erro <code>InputMismatchException</code> usando laços infinitos e limpeza de buffer de Scanner.</li>
<li><b>Validação com Regex:</b> Expressões regulares garantindo o padrão exato de e-mails válidos.</li>
</ul>
</details>

<hr>

<h2>🔐 Nota sobre Arquitetura e Segurança (Débito Técnico Consciente)</h2>
<p>Neste projeto, as senhas dos usuários estão sendo salvas em texto limpo (<i>plaintext</i>) dentro dos arquivos <code>.txt</code>. Tenho plena consciência de que esta <b>não é uma prática segura</b> para sistemas em produção. Esta escolha arquitetural foi feita exclusivamente com fins didáticos, para focar no domínio absoluto de <b>leitura/escrita de arquivos modernos (NIO.2)</b>, Manipulação de Streams e Estruturas de Dados. Em um cenário corporativo real, as credenciais seriam submetidas a funções de Hash Criptográfico (como <b>BCrypt</b>) e armazenadas em Bancos de Dados Relacionais sob o padrão Repository.</p>

<p><b>Conceitos Dominados:</b> Arquitetura MVC-Like, Java 8+ Streams API (map, filter, flatMap, reduce), Lambdas & Method References, Java NIO.2 (Files.lines/write), In-Memory Cache (HashSet O(1)), Lazy Evaluation, Try-With-Resources, Soft Delete, Algoritmos de Ordenação, Java Time API, Tratamento de Exceções Defensivo e Regex.</p>