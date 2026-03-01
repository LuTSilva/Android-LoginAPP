# Android-AuthApp
Repositório contendo minha primeira aplicação Android, desenvolvida como requisito para estágio seguindo os seguintes requisitos:

1.Splash Screen
Exibir um logotipo qualquer ou o nome do aplicativo por alguns segundos
antes de abrir a tela de login.

2.Tela de Login
Campos para nome de usuário ou e-mail e senha.
Botão para entrar.
Botão para acessar a tela de cadastro.
Validação básica dos campos (não permitir login com campos vazios e
nem permitir cadastro de usuário já existente).

3.Tela de Cadastro
Campos para nome de usuário, e-mail e senha.
Botão para salvar cadastro.
Validação básica dos campos (ex.: e-mail deve ser válido, senha com no
mínimo 6 caracteres).
Após cadastro, retornar para a tela de login.

4.Tela Principal
Exibir as informações do usuário logado (nome de usuário e e-mail).
Botão para logout, que retorna para a tela de login.

Requisitos Técnicos
O usuário cadastrado deve ser salvo localmente (pode ser com 
SharedPreferences ou outra forma simples de persistência).
O estado de login deve ser mantido até o usuário fazer logout (ou seja, se o
app for fechado e aberto novamente, o usuário já deve ir para a tela
principal se ainda estiver logado).
