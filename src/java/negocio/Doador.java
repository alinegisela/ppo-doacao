/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Aline
 */
public class Doador {

 final String tipo = "doador";
 private String nome;
 private String cpf;
 private String endereco;
 private String telefone;
 private String email;
 private String senha;
 private String senhaConfirmacao;
 private String novaSenha;
 private String nomePrimeiro="";

    public Doador(String nome, String cpf, String endereco, String telefone, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public Doador(){
    }

    public String getNomePrimeiro() {
        nomePrimeiro="";
        for(int i=0;i<getNome().length();i++){
            if(getNome().charAt(i) != ' '){
                nomePrimeiro+=getNome().charAt(i);
            }else{
                break;
            }
        }
        return nomePrimeiro ;
    }

    public void setNomePrimeiro(String nomePrimeiro) {
        this.nomePrimeiro = nomePrimeiro;
    }

    
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaConfirmacao() {
        return senhaConfirmacao;
    }

    public void setSenhaConfirmacao(String senhaConfirmacao) {
        this.senhaConfirmacao = senhaConfirmacao;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Doador{" + "nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco + ", telefone=" + telefone + ", email=" + email + '}';
    }
 
 
    
}
