<h1>🚀 Mini Rede Social (Java + MySQL)</h1>

<h2>📝 Resumo</h2>
<p>Esta aplicação é uma evolução robusta de uma rede social baseada em terminal (CLI), focada em <b>Persistência de Dados Relacional e Segurança da Informação</b>. O sistema utiliza a API <b>JDBC (Java Database Connectivity)</b> para integração com o motor <b>MySQL</b>, permitindo o gerenciamento escalável de usuários, autenticação blindada e postagens vinculadas por integridade referencial, superando os limites de armazenamento em arquivos planos.</p>

<h2>🚩 Desafio Proposto</h2>
<p>Migrar a inteligência de armazenamento do sistema para um banco de dados relacional, implementando o padrão de <b>Single Source of Truth (SSOT)</b>. O objetivo principal foi garantir a persistência atômica das informações, eliminar colisões de identificadores através de <code>AUTO_INCREMENT</code> nativo e proteger a aplicação contra vulnerabilidades críticas de segurança em sistemas orientados a dados.</p>

<h3>🚀 Arquitetura e Persistência Profissional</h3>

<p>O projeto foi desenvolvido sob uma arquitetura de camadas, priorizando o desacoplamento entre a interface de usuário e a lógica de acesso ao banco de dados.</p>



<details open>
<summary><b>🗄️ Persistência Relacional e Integridade (MySQL)</b></summary>
<ul>
<li><b>Relacionamentos e Chaves Estrangeiras:</b> Implementação de <code>FOREIGN KEY</code> para garantir a consistência dos dados, assegurando que postagens estejam obrigatoriamente vinculadas a usuários existentes.</li>
<li><b>Normalização de Dados:</b> Estruturação de tabelas otimizadas com tipos de dados precisos (<code>VARCHAR</code>, <code>TIMESTAMP</code>, <code>INT</code>), garantindo economia de disco e velocidade de consulta.</li>
<li><b>Generated Keys:</b> Recuperação dinâmica de IDs gerados pelo motor do banco via <code>Statement.RETURN_GENERATED_KEYS</code>, mantendo a sincronia imediata entre o registro físico e o objeto Java em memória RAM.</li>
</ul>
</details>



<details open>
<summary><b>📜 Automação de Banco de Dados (Schema SQL)</b></summary>
<ul>
<li><b>Dicionário de Dados:</b> O projeto acompanha um arquivo <code>schema.sql</code> contendo todos os comandos DDL (Data Definition Language) necessários para a criação imediata do ambiente.</li>
<li><b>Constraints de Negócio:</b> Implementação de <code>UNIQUE</code> no campo de e-mail e <code>NOT NULL</code> em campos críticos diretamente no motor do banco, garantindo uma segunda camada de validação além da aplicação.</li>
<li><b>Rastreabilidade Temporal:</b> Uso do tipo <code>TIMESTAMP</code> para o registro preciso de postagens, permitindo consultas cronológicas nativas e eficientes.</li>
</ul>
</details>

<details open>
<summary><b>🛡️ Segurança e Blindagem (JDBC Core)</b></summary>
<ul>
<li><b>PreparedStatement (Anti-SQL Injection):</b> Proteção total contra ataques de injeção de SQL através da parametrização de queries, tratando as entradas do usuário de forma sanitizada e tipada.</li>
<li><b>Isolamento de Credenciais:</b> Gerenciamento de variáveis de ambiente via arquivos <code>.properties</code> protegidos por <code>.gitignore</code>, impedindo o vazamento de segredos do banco de dados para o controle de versão.</li>
<li><b>Soft Delete Relacional:</b> Implementação de exclusão lógica através de sinalizadores de estado (<code>statusUser = 'ACTIVE'/'DISABLE'</code>), permitindo a retenção de dados históricos e auditoria sem comprometer a integridade do banco.</li>
</ul>
</details>



<details open>
<summary><b>⚙️ Gestão de Recursos e Performance</b></summary>
<ul>
<li><b>Singleton Connection:</b> Gerenciamento centralizado da conexão via <code>dbConnect</code>, otimizando o ciclo de vida do socket de rede entre a aplicação e o servidor MySQL.</li>
<li><b>Try-With-Resources (JDBC):</b> Garantia de fechamento automático de <code>PreparedStatement</code> e <code>ResultSet</code>, blindando a aplicação contra <i>Connection Leaks</i> e esgotamento de recursos do servidor.</li>
<li><b>Filtros de Origem:</b> Otimização de performance movendo a lógica de filtragem da RAM para o motor SQL (cláusulas <code>WHERE</code>), reduzindo drasticamente o tráfego de rede e o processamento no Java.</li>
</ul>
</details>

<details open>
<summary><b>⚡ Estruturas de Dados e UX</b></summary>
<ul>
<li><b>Cache In-Memory O(1):</b> Utilização de <code>HashSet</code> mapeado via Streams para validação instantânea de duplicidade de e-mails, eliminando gargalos de I/O desnecessários no banco de dados.</li>
<li><b>Cronologia Atômica:</b> Uso da <b>Java Time API</b> integrada ao tipo <code>TIMESTAMP</code> do MySQL para garantir ordenação cronológica precisa no Feed Global através de <code>Comparator.comparing()</code>.</li>
</ul>
</details>

<hr>

<h2>🔐 Nota sobre Segurança de Credenciais</h2>
<p>A arquitetura deste projeto foi desenhada para separar o código-fonte das configurações sensíveis. As credenciais de acesso ao MySQL são injetadas em tempo de execução via arquivo <code>db.properties</code>, que <b>nunca deve ser versionado</b>. Um modelo (<code>db.properties.example</code>) é fornecido para facilitar o deploy, mas a segurança real reside no isolamento das variáveis de ambiente na máquina host.</p>

<p><b>Conceitos Dominados:</b> JDBC (Connection, PreparedStatement, ResultSet), SQL (DDL, DML, Constraints), Integridade Referencial, Anti-SQL Injection, Singleton Pattern, Java 8+ Streams, Lambda & Method References, In-Memory Cache (HashSet), Try-With-Resources, Soft Delete, Algoritmos de Ordenação, Java Time API e Regex.</p>

<hr>

<p align="center">
    <b>Desenvolvido com foco em Engenharia de Software e Persistência Profissional.</b>
</p>