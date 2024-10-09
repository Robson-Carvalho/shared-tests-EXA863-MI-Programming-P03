# Testes da Classe EventTest
Este README descreve os testes da classe EventTest, que verifica o funcionamento do controlador de administradores no gerenciamento de eventos em um sistema de gerenciamento de usuários. Os testes são realizados na classe AdminController, que gerencia operações como criação, remoção e listagem de eventos.

## Estrutura dos Testes
Os testes são organizados da seguinte forma:

- setUp(): Método executado antes de cada teste, responsável por inicializar as dependências necessárias, como o EventService e o ReviewService, além de instanciar o AdminController. Um usuário administrador e um evento de teste também são criados.

- tearDown(): Método executado após cada teste, responsável por limpar os dados criados durante os testes, garantindo que o estado do sistema permaneça consistente.

## Cenários de Teste
### testCreateEvent
Verifica se um novo evento pode ser criado com sucesso pelo administrador. O evento é criado com todos os atributos necessários (nome, descrição e data) e verifica-se se o evento foi corretamente armazenado no EventStore.

### testCreateEventByUser
Verifica se a exceção correta é lançada quando um usuário comum tenta criar um evento. O teste assegura que apenas administradores têm permissão para criar eventos.

### testDeleteEvent
Verifica se um evento criado pode ser removido corretamente pelo administrador. Após a remoção, é garantido que o evento não está mais presente no EventStore.

### testListEvents
Verifica se o método para listar eventos funciona corretamente. O teste assegura que eventos criados pelo administrador podem ser recuperados e listados.

### testDeleteEventByUser
Verifica se a exceção correta é lançada quando um usuário comum tenta excluir um evento. Assim como no teste de criação, este teste assegura que apenas administradores têm permissão para excluir eventos.

