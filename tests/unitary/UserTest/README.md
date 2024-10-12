# Teste para Função de Criação de Usuário

## Descrição

Este teste verifica o funcionamento da função `create`, localizada na classe `UserTestFacade`, que deve permitir a criação de um novo usuário no sistema. O teste inclui diferentes cenários para garantir que a função funcione corretamente e que as informações do usuário sejam armazenadas corretamente no banco de dados.

## Cenários de Teste

1. **Verificação de Usuário Existente**: Antes de criar um novo usuário, o teste verifica se já existe um usuário com o email fornecido. A expectativa é que não exista usuário com o email `test@example.com` antes da criação.

2. **Criação de Usuário**: O teste tenta criar um novo usuário com as seguintes informações:
   - Login: `login`
   - Nome: `testUser`
   - Email: `test@example.com`
   - Senha: `teste123`
   - CPF: `123456789`
   - Admin: `false`
   
   A expectativa é que a criação do usuário seja bem-sucedida, retornando `true`.

3. **Verificação de Dados do Usuário Criado**: Após a criação do usuário, o teste verifica se os dados armazenados correspondem aos valores fornecidos durante a criação:
   - Login deve ser igual a `login`.
   - Nome deve ser igual a `testUser`.
   - Email deve ser igual a `test@example.com`.
   - Senha deve ser igual a `teste123`.
   - CPF deve ser igual a `123456789`.
   - Admin deve ser igual a `false`.

Esses cenários garantem que a funcionalidade de criação de usuário não apenas funcione corretamente, mas também que os dados sejam salvos e recuperados corretamente do banco de dados.
