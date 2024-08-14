#language: pt

Funcionalidade: Login

  Cenario: Login com credenciais validas
    Dado que estou na pagina de login
    Quando insiro um nome de usuario valido
    E clico em 'Entrar'
    E insiro uma senha valida
    E clico em 'Entrar'
    Entao devo ser redirecionado para a pagina principal

  Cenario: Tentar realizar login com credenciais invalidas
    Dado que estou na pagina de login
    Quando insiro um nome de usuario valido
    E clico em 'Entrar'
    E insiro uma senha nao correspondente ao nome de usuario
    E clico em 'Entrar'
    Entao devo visualizar um alerta informando que nao foi possivel realizar login
