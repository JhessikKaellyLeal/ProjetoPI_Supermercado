/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author SamaraTavares
 */
// dentro do java Class não se uma "main"
public class Usuario {
   /*Primeira regra de Segurança:
   - todos os atributos são declarados como privados
   - private informa que somente a classe(Usuario) pode acessar 
   */
   private String nome,dataNascimento,cpf,email; 
   private String senha,telefone,endereco,sexo;       
   
   // construtor - metodo que inicializa o objeto criado
   // construtor - precisa ter o mesmo nom da classe
   public void Usuario(){
       
   }        
           
   // SETTER - é utilizado para cadastrar e atualizar dados
   // metodo com parametro - quem chamar tem que passar texto
    public void setNome(String valor){// metodo
       this.nome = valor;   
    }// fim do setNome
   
    public void setCpf(String valor){
       this.cpf = valor;
    }// fim do setCpf
   
    public void setDataNascimento(String valor){
       this.dataNascimento = valor;
    }// fim do setDataNascimento

    public void setEmail(String email) {
        this.email = email;
    }// fim do setEmail

    public void setSenha(String senha) {
        this.senha = senha;
    }// fim do setSenha

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }// fim do setTelefone

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }// fim do setEndereco

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }// fim do setSexo
    
    //GETTER - possibilita ler o dado que tem na variavel
    // metodo com retorno do tipo String
    // this - serve para indicar que a váriavel está dentro da classe
    public String getNome(){
        return this.nome;
    }// fim so getNome

    public String getDataNascimento() {
        return dataNascimento;
    }// fim do getDataNascimento

    public String getCpf() {
        return cpf;
    }// fim do getCpf

    public String getEmail() {
        return email;
    }// fim do getEmail

    public String getSenha() {
        return senha;
    }// fim do getSenha

    public String getTelefone() {
        return telefone;
    }// fim do getTelefone

    public String getEndereco() {
        return endereco;
    }// fim do getEndereço

    public String getSexo() {
        return sexo;
    }// fim do getSexo 
   
    
   
   
   
   
   
   
}  
   