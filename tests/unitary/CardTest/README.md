Testes da Classe ClientControllerCardTest
Este README descreve os testes da classe ClientControllerCardTest, que verifica o funcionamento do controlador de clientes no gerenciamento de cartões de crédito em um sistema de gerenciamento de usuários. Os testes são realizados na classe ClientController, que gerencia operações como adição, remoção e desativação de cartões de crédito de usuários.

Estrutura dos Testes
Os testes são organizados da seguinte forma:

setUp(): Método executado antes de cada teste, responsável por inicializar as dependências necessárias, como o CardService e o CardStore, além de instanciar o ClientController.

tearDown(): (não implementado, mas pode ser adicionado caso seja necessário para limpeza de dados ou reset entre os testes).

Cenários de Teste
testAddCreditCard
Verifica se um novo cartão de crédito pode ser adicionado com sucesso ao sistema. O cartão é criado com todos os atributos necessários (nome do titular, número, validade, etc.) e verifica-se se o cartão foi corretamente armazenado na CardStore.

testRemoveCreditCard
Verifica se um cartão de crédito pode ser removido corretamente do sistema. Após a remoção, é garantido que o cartão não está mais presente na CardStore.

testDisableCreditCard
Verifica se um cartão de crédito pode ser desativado corretamente. Após a desativação, verifica-se que o cartão está marcado como inativo na CardStore.

