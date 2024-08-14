#language: pt

Funcionalidade: Criação de Tarefas

  Cenario: Criar tarefa valida
    Dado que estou logado
    E estou na pagina de criacao de tarefas
    Quando preencho o formulario corretamente
    E clico em 'Criar Nova Tarefa'
    Entao devo ser redirecionado para a pagina de visualizacao da tarefa

  Cenario: Tentar criar tarefa sem preencher 'categoria'
    Dado que estou logado
    E estou na pagina de criacao de tarefas
    Quando preencho o formulario mas deixo de preencher 'categoria'
    E clico em 'Criar Nova Tarefa'
    Então devo visualizar uma mensagem de erro informando a obrigatoriedade do campo 'categoria'
