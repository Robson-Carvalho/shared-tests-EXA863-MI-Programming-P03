# Testes da Classe TicketServiceTest
Este README descreve os testes da classe TicketServiceTest, que verifica o funcionamento do serviço de gerenciamento de ingressos em um sistema de gerenciamento de eventos. Os testes são realizados na classe TicketService, que gerencia operações como criação, remoção e listagem de ingressos.

## Estrutura dos Testes
Os testes são organizados da seguinte forma:

- setUp(): Método executado antes de cada teste, responsável por inicializar as dependências necessárias, como o TicketStore e o EventService, além de instanciar o TicketService. Um evento de teste e um ingresso de teste também são criados para garantir a consistência durante os testes.

- tearDown(): Método executado após cada teste, responsável por limpar os dados criados durante os testes, garantindo que o estado do sistema permaneça consistente.

## Cenários de Teste
### testCreateTicket
Verifica se um novo ingresso pode ser criado com sucesso. O ingresso é criado com todos os atributos necessários (evento, usuário e valor) e verifica-se se o ingresso foi corretamente armazenado no TicketStore.

### testCreateTicketForInvalidEvent
Verifica se a exceção correta é lançada quando um ingresso é criado para um evento inválido. O teste assegura que apenas eventos válidos podem ter ingressos associados.

### testDeleteTicket
Verifica se um ingresso criado pode ser removido corretamente. Após a remoção, é garantido que o ingresso não está mais presente no TicketStore.

### testListTickets
Verifica se o método para listar ingressos funciona corretamente. O teste assegura que ingressos criados podem ser recuperados e listados com sucesso.

### testDeleteTicketByUser
Verifica se a exceção correta é lançada quando um usuário comum tenta excluir um ingresso. Assim como no teste de criação, este teste assegura que apenas administradores têm permissão para excluir ingressos.