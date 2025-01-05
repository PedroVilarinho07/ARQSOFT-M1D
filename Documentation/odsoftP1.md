# ODSOFT TP1 - Análise da Pipeline e Jenkins

![pipeline local view](../Documentation/jenkins_prints/pipeline_local_view.png)

### 1. Checkout
- **Descrição**: Realiza o checkout do repositório especificado no GitHub utilizando credenciais fornecidas.
- **Logs Relevantes**:
    - `Selected Git installation does not exist. Using Default` indica que nenhuma instalação específica do Git foi configurada, sendo usado o cliente padrão.
    - `The recommended git tool is: NONE` sugere que a configuração recomendada para o Git no Jenkins precisa ser ajustada.
    - O commit carregado (`b92bb49d14e30c948c87b00886b672b8ddcf6165`) possui a mensagem: `"acceptance test working and pom dependencies added"`.

---

### 2. Clean
- **Descrição**: Remove arquivos gerados em builds anteriores.
- **Logs Relevantes**:
    - Problemas no `pom.xml`: Há duplicações do plugin `maven-surefire-plugin` nas linhas 273 e 281.
    - `BUILD SUCCESS` confirma a conclusão bem-sucedida da etapa.

---

### 3. Validate
- **Descrição**: Valida a estrutura do projeto e dependências no `pom.xml`.
- **Logs Relevantes**:
    - Os mesmos problemas de duplicação do `maven-surefire-plugin` persistem.
    - `BUILD SUCCESS` indica que, apesar dos avisos, o projeto foi validado corretamente.

---

### 4. Compile
- **Descrição**: Compila o código-fonte para garantir que ele está livre de erros.
- **Logs Relevantes**:
    - Foram detectados **avisos sobre propriedades não mapeadas** e métodos duplicados:
        - Exemplo: `Unmapped target property: "enabled"` no `EditUserMapper.java`.
        - `Not generating getTitle(): A method with that name already exists`.
    - `BUILD SUCCESS` indica que o código foi compilado com sucesso.

---

### 5. Checkstyle
- **Descrição**: Verifica o estilo do código em relação às regras configuradas.
- **Logs Relevantes**:
    - Foram detectados **4027 erros de estilo** com base no `sun_checks.xml`. Corrigir essas violações é essencial para melhorar a legibilidade e a manutenção do código.

---

### 6. SpotBugs
- **Descrição**: Analisa o código em busca de bugs potenciais.
- **Logs Relevantes**:
    - Nenhum problema crítico identificado.
    - As advertências sobre duplicação do plugin no `pom.xml` aparecem novamente.

---

### 7. Generate Javadoc
- **Descrição**: Gera a documentação do código-fonte.
- **Logs Relevantes**:
    - Diversos avisos sobre a **falta de comentários** em classes, métodos e parâmetros:
        - Exemplo: `No comment` no método `public OpenAPI openApi()` em `ApiConfig.java`.

---

### 8. Unit Tests
- **Descrição**: Executa os testes unitários para validar funcionalidades isoladas.
- **Logs Relevantes**:
    - Todos os testes passaram com sucesso:
        - `Tests run: 3, Failures: 0, Errors: 0, Skipped: 0`.
    - Nenhuma falha foi encontrada.

---

### 9. Integration Tests
- **Descrição**: Valida a integração entre os diferentes componentes do sistema.
- **Logs Relevantes**:
    - Nenhum teste de integração foi executado:
        - `Tests run: 0, Failures: 0, Errors: 0, Skipped: 0`.

---

### 10. Acceptance Tests
- **Descrição**: Testa os requisitos funcionais do sistema.
- **Logs Relevantes**:
    - Os testes passaram:
        - `Tests run: 3, Failures: 0, Errors: 0, Skipped: 0`.
    - Foi detectado um problema na criação da tabela `author`:
        - `"Syntax error in SQL statement 'create table author (pk bigint identity not null, author_number ... )'"`.
        - Indica um **erro de sintaxe SQL**, possivelmente relacionado ao uso de `identity` como palavra-chave reservada.

---

### 11. Mutation Coverage
- **Descrição**: Mede a cobertura dos testes em cenários simulados.
- **Logs**: Não fornecidos.

---

### 12. Surefire Reports
- **Descrição**: Gera relatórios detalhados dos testes unitários.
- **Logs**: Não fornecidos.

---

### 13. Jacoco Report
- **Descrição**: Analisa e apresenta a cobertura do código pelos testes.
- **Logs**: Não fornecidos.

---

### 14. Package
- **Descrição**: Empacota a aplicação em um arquivo `.jar` ou `.war`.
- **Logs**: Não fornecidos.

---

### 15. Verify
- **Descrição**: Verifica o pacote antes da instalação.
- **Logs**: Não fornecidos.

---

### 16. Install
- **Descrição**: Instala o pacote localmente.
- **Logs**: Não fornecidos.

---

### 17. Deploy Application
- **Descrição**: Implementa a aplicação num ambiente configurado.
- **Logs**: Não fornecidos.

---

## Problemas Identificados e Soluções de melhoria da Pipeline
1. **Duplicação do Plugin no `pom.xml`**:
    - Aparece em várias etapas (clean, validate, etc.).
    - **Solução**: Remover declarações duplicadas do `maven-surefire-plugin`.

2. **Erros de Estilo no Código**:
    - Foram identificados mais de 4000 erros.
    - **Solução**: Adotar um padrão de formatação e corrigir as violações.

3. **Avisos sobre Propriedades Não Mapeadas**:
    - Afetam a qualidade do código.
    - **Solução**: Ajustar os mapeamentos para incluir todas as propriedades.

4. **Falta de Comentários no Código**:
    - A ausência de documentação reduz a manutenibilidade.
    - **Solução**: Adicionar comentários descritivos às classes, métodos e parâmetros.
