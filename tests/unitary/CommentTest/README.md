# Testes da Classe CommentTest

Este README descreve os testes da classe CommentTest, que verifica o funcionamento do controlador de comentários em um sistema de gerenciamento de eventos e comentários. Os testes são realizados na classe CommentController, que gerencia operações como criação, recuperação, e exclusão de comentários.

### Estrutura dos Testes

Os testes são organizados da seguinte forma:

- **setUp():** Método executado antes de cada teste, responsável por inicializar os controladores necessários (EventController, CommentController, UserController).

- **tearDown():** Método executado após cada teste, que limpa os dados criados durante os testes, garantindo um ambiente limpo para os testes subsequentes.

### Cenários de Teste

- **commentCreateTest**

Verifica se um comentário pode ser criado corretamente e se seus dados podem ser recuperados conforme o esperado.

- **commentDeleteTest**

Verifica se um comentário pode ser excluído corretamente e se não pode ser recuperado após a exclusão.

- **testCreateFutureEventComment**

Verifica se é gerada uma exceção ao tentar adicionar um comentário para um evento que ainda não ocorreu, com a mensagem apropriada.
