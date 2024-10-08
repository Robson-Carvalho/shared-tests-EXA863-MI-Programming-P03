# Testes da Classe User

Este README descreve os testes da classe UserTest, que verifica o funcionamento do controlador de usuários em um sistema de gerenciamento de usuários. Os testes são realizados na classe UserController, que gerencia operações como criação, recuperação, atualização e exclusão de usuários. Os cenários de teste incluem a verificação de duplicação de usuários, criação, deleção e atualização de dados de usuários.

### Estrutura dos Testes

Os testes são organizados da seguinte forma:

- **setUp():** Método executado antes de cada teste, responsável por inicializar os controladores necessários (EventController, TicketController, UserController).

- **tearDown():** Método executado após cada teste, que limpa os dados criados durante os testes, garantindo um ambiente limpo para os testes subsequentes.

### Cenários de Teste

- **duplicateUserInDataBaseTest** 

Verifica se a criação de um usuário com um login, e-mail ou CPF já existentes gera uma exceção com a mensagem apropriada.

- **createUserInDataBaseTest**

Verifica se um novo usuário pode ser criado e recuperado com sucesso.

- **createUsersInDataBaseTest**

Verifica se a criação de múltiplos usuários resulta na quantidade correta de usuários no banco de dados.

- **deleteUserTest**

Verifica se um usuário pode ser excluído corretamente.

- **updateUserTest**

Verifica se um usuário pode ser atualizado corretamente e se são geradas exceções ao tentar usar um e-mail já existente.