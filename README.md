<h1>Mini Rede Social (CLI)</h1>
<h2>📝 Resumo </h2>
<p>Este projeto é uma simulação de uma rede social baseada em terminal (Command Line Interface), desenvolvida puramente em Java. O sistema permite o cadastro de novos usuários, autenticação segura, criação de postagens e a exibição de uma "Linha do Tempo" (Global Feed). Mais do que uma simples aplicação de terminal, este projeto é um laboratório prático de <b>Arquitetura de Software, Estruturas de Dados de Alta Performance e Persistência de Dados (Java I/O)</b>, lidando com desafios reais de sincronização entre Memória RAM e Disco Rígido.</p>

<h2>🚩 Desafio Proposto</h2>
<p>Construir uma aplicação completa com fluxo de navegação contínuo (menus interativos), persistência física garantindo a integridade dos dados (prevenção de colisões de ID e corrupção de arquivos), e implementar um motor de busca otimizado utilizando estratégias de Cache em memória para evitar gargalos de leitura em disco.</p>

<h3>🚀 Arquitetura e Funcionalidades do Sistema</h3>

<p>O projeto foi desenvolvido com foco estrito em boas práticas de Engenharia de Software, aplicando conceitos de Clean Code, Separação de Responsabilidades (SoC) e Gestão de Ciclo de Vida da Aplicação.</p>

<details open>
<summary><b>🛠️ Arquitetura e Ciclo de Vida (Boot Sequence)</b></summary>
<ul>
<li><b>Separação em Camadas (MVC-Like):</b> Organização estrita do projeto em pacotes especialistas (<code>application</code>, <code>entities</code>, <code>services</code>, <code>views</code>, <code>utilities</code>).</li>
<li><b>Boot Sequence Controlado:</b> A inicialização do <code>App.java</code> segue o fluxo de sistemas de alta disponibilidade: 1º Alocação de Memória -> 2º Leitura do Banco de Dados (Disco) -> 3º Aquecimento de Cache (RAM) -> 4º Liberação da Interface (UI).</li>
<li><b>Princípio DRY (Don't Repeat Yourself):</b> Uso de constantes <code>static final</code> em uma classe de configuração centralizada (<code>PathDocuments</code>) para gerenciar os caminhos absolutos do sistema.</li>
</ul>
</details>

<details open>
<summary><b>💾 Integridade de Dados e Padrões de Banco (Java I/O)</b></summary>
<ul>
<li><b>Soft Delete (Exclusão Lógica):</b> Usuários deletados não são apagados fisicamente do banco para não quebrar a integridade de seus posts antigos. O sistema altera o status para <code>DISABLE</code>, reescrevendo o arquivo <code>.txt</code> de forma segura e expurgando o usuário do Cache em tempo real.</li>
<li><b>Simulação de AUTO_INCREMENT:</b> Criação de um motor de geração de IDs (<code>GenerateID</code>) que varre o disco para encontrar a última chave primária absoluta, prevenindo colisões de ID (Desync) entre dados na RAM e dados persistidos após um Soft Delete.</li>
<li><b>Prevenção de "Phantom Lines":</b> Lógica de reescrita de buffer blindada contra a inserção de quebras de linha fantasmas no final do arquivo, evitando o letal <code>ArrayIndexOutOfBoundsException</code> durante a reinicialização.</li>
</ul>
</details>

<details open>
<summary><b>⚡ Estruturas de Dados e Alta Performance (Cache In-Memory)</b></summary>
<ul>
<li><b>Otimização de Busca:</b> Substituição da varredura de listas (<code>ArrayList</code>) pela implementação de um <b>Cache em Memória</b> utilizando <code>HashSet</code> para a validação de e-mails no momento do cadastro.</li>
<li><b>Redução de Gargalo de I/O:</b> O sistema constrói a Tabela Hash uma única vez no Boot. Validações subsequentes ocorrem via "teletransporte matemático" na RAM (<code>.contains()</code>), eliminando a necessidade de ler o disco ou fazer laços de repetição durante o uso contínuo.</li>
</ul>
</details>

<details open>
<summary><b>⏱️ Motor do Feed Global e Java Time API</b></summary>
<ul>
<li><b>Agrupamento Dinâmico:</b> Algoritmo de junção que extrai postagens de entidades individuais e as unifica em uma lista global (Global Feed).</li>
<li><b>Ordenação Cronológica:</b> Aplicação do <code>.sort()</code> atrelado ao <code>Comparator.comparing()</code>, utilizando Method References (<code>Posts::getDateHour</code>) para organização matemática baseada no tempo.</li>
<li><b>Java Time API:</b> Uso intensivo do <code>LocalDateTime</code> e <code>DateTimeFormatter</code> (padrão ISO 8601 adaptado para visualização pt-BR).</li>
</ul>
</details>

<details open>
<summary><b>🛡️ Blindagem Defensiva e UX</b></summary>
<ul>
<li><b>State Management (Navegação CLI):</b> Pilha de navegação controlada por laços <code>while (control)</code> para trânsito fluido entre menus.</li>
<li><b>Micro-Loops de Retenção:</b> A classe utilitária <code>VerifyType</code> blinda o sistema contra o erro <code>InputMismatchException</code> usando laços infinitos e limpeza de buffer de Scanner (<code>sc.nextLine()</code>) em blocos <code>try/catch</code>.</li>
<li><b>Validação com Regex:</b> Expressões regulares garantindo o padrão exato de e-mails válidos.</li>
<li><b>Encoding CP850 / UTF-8:</b> Sincronização entre o charset do terminal e o charset de gravação de arquivos para evitar corrupção de caracteres especiais.</li>
</ul>
</details>

<hr>

<h2>🔐 Nota sobre Arquitetura e Segurança (Débito Técnico Consciente)</h2>
<p>Neste projeto, as senhas dos usuários estão sendo salvas em texto limpo (<i>plaintext</i>) dentro dos arquivos <code>.txt</code>. Tenho plena consciência de que esta <b>não é uma prática segura</b> para sistemas em produção. Esta escolha arquitetural foi feita exclusivamente com fins didáticos, para focar no domínio de <b>leitura/escrita de arquivos (I/O)</b>, Estruturas de Dados e Algoritmos. Em um cenário corporativo real, as credenciais seriam submetidas a funções de Hash Criptográfico (como <b>BCrypt</b>) e armazenadas em Bancos de Dados Relacionais sob padrão DAO/Repository.</p>

<p><b>Conceitos Dominados:</b> Arquitetura MVC-Like, In-Memory Cache (Set/Hash), Soft Delete, Simulação de Constraints (Auto Increment), Algoritmos de Ordenação, Java Time API, Tratamento de Exceções Defensivo, Expressões Regulares (Regex) e Gestão de Fluxo (State Management).</p>