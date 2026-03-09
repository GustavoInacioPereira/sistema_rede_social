<h1>Mini Rede Social (CLI)</h1>
<h2>📝 Resumo </h2>
<p>Este projeto é uma simulação de uma rede social baseada em terminal (Command Line Interface), desenvolvida puramente em Java. O sistema permite o cadastro de novos usuários, autenticação de login segura contra falhas de digitação, criação de postagens e a exibição de uma "Linha do Tempo" (Global Feed) ordenada cronologicamente, persistindo todos os dados em arquivos de texto locais.</p>

<h2>🚩 Desafio Proposto</h2>
<p>Construir uma aplicação completa com fluxo de navegação contínuo (menus interativos), persistência de dados em disco (I/O) lidando com problemas de Encoding (acentuação), e estabelecer o relacionamento estrutural entre diferentes entidades (Usuários e Postagens), mantendo o código limpo, modularizado e à prova de quebras.</p>

<h3>🚀 Arquitetura e Funcionalidades do Sistema</h3>

<p>Este projeto foi desenvolvido com foco em boas práticas de Engenharia de Software, aplicando conceitos de Clean Code e separação de responsabilidades (SoC).</p>

<details open>
<summary><b>🛠️ Arquitetura e Design Pattern (MVC-Like)</b></summary>
<ul>
<li><b>Separação em Camadas:</b> Organização estrita do projeto em pacotes para isolar responsabilidades (<code>application</code>, <code>entities</code>, <code>services</code>, <code>views</code>, <code>utilities</code>).</li>
<li><b>Princípio DRY (Don't Repeat Yourself):</b> Uso de constantes <code>static final</code> em uma classe de configuração centralizada (<code>PathDocuments</code>) para gerenciar os caminhos dos arquivos do banco de dados simulado.</li>
<li><b>Relacionamentos (1:N):</b> Implementação orientada a objetos onde a classe <code>User</code> gerencia sua própria <code>List&lt;Posts&gt;</code> de forma encapsulada.</li>
</ul>
</details>

<details open>
<summary><b>💾 Persistência de Dados (Java I/O) e Encoding</b></summary>
<ul>
<li><b>Banco de Dados Simulado:</b> Leitura e gravação de dados em disco utilizando <code>FileReader</code>, <code>BufferedReader</code>, <code>FileWriter</code> e <code>BufferedWriter</code> em arquivos <code>.txt</code>.</li>
<li><b>Conversão Dinâmica:</b> Transformação de linhas de texto (separadas por <code>;</code>) em Objetos Java instanciados na Memória RAM durante o boot do sistema.</li>
<li><b>Controle Rigoroso de Encoding:</b> Resolução de conflitos de caracteres (acentos corrompidos) forçando a leitura do <code>Scanner</code> no padrão do terminal Windows (<code>CP850</code>) e garantindo a gravação no formato universal <code>StandardCharsets.UTF_8</code>.</li>
</ul>
</details>

<details open>
<summary><b>⏱️ Motor do Feed Global e Java Time API</b></summary>
<ul>
<li><b>Agrupamento Dinâmico:</b> Algoritmo capaz de varrer a lista de usuários, extrair postagens individuais e unificá-las em uma única lista global (Linha do Tempo).</li>
<li><b>Ordenação Cronológica:</b> Aplicação do método <code>.sort()</code> com <code>Comparator.comparing()</code> para organizar matematicamente as postagens das mais antigas para as mais recentes.</li>
<li><b>Manipulação de Tempo:</b> Uso da Java Time API (<code>LocalDateTime</code> e <code>DateTimeFormatter</code>) para carimbar o momento exato do post e exibi-lo no padrão brasileiro (dd/MM/yyyy HH:mm).</li>
</ul>
</details>

<details open>
<summary><b>🛡️ Blindagem, UX e State Management</b></summary>
<ul>
<li><b>State Management (Navegação CLI):</b> Implementação de uma "Pilha de Navegação" utilizando laços <code>while (control)</code> para manter o programa rodando continuamente, transitando entre telas sem encerrar a execução abruptamente.</li>
<li><b>Try-Catch Defensivo:</b> Criação da classe utilitária <code>VerifyType</code>, que blinda as entradas numéricas contra o erro letal <code>InputMismatchException</code> através de micro-loops de repetição e limpeza de buffer.</li>
<li><b>Regex (Expressões Regulares):</b> Validação rigorosa garantindo que apenas emails em formatos estruturalmente corretos sejam cadastrados no sistema.</li>
</ul>
</details>

<hr>

<h2>🔐 Nota sobre Arquitetura e Segurança (Débito Técnico Consciente)</h2>
<p>Neste projeto, as senhas dos usuários estão sendo salvas em texto limpo (<i>plaintext</i>) dentro dos arquivos <code>.txt</code>. Tenho plena consciência de que esta <b>não é uma prática segura</b> para sistemas em produção. Esta escolha arquitetural foi feita exclusivamente com fins didáticos, para focar no aprendizado do fluxo de <b>leitura e escrita de arquivos (Java I/O)</b> e manipulação de Strings.</p>
<p>Em um cenário corporativo real, essas credenciais seriam criptografadas utilizando algoritmos de Hash (como o <b>BCrypt</b>) e armazenadas em um Banco de Dados Relacional (SQL) ou NoSQL devidamente protegido.</p>

<p><b>Conceitos Dominados:</b> Arquitetura em Camadas (MVC-Like), Manipulação de Arquivos (I/O), Coleções Dinâmicas, Algoritmos de Ordenação, Tratamento de Exceções Defensivo, Expressões Regulares (Regex), Controle de Estado em CLI e Configurações de Encoding.</p>