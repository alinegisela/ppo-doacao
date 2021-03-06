package negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

public class Insumo {

    private int codigo;
    private String nome;
    private String tipo;
    private String descricao;
    private boolean prioridade;
    private String prioridadeString;

    public String getPrioridadeString() {
        return prioridadeString;
    }

    public void setPrioridadeString(String prioridadeString) {
        this.prioridadeString = prioridadeString;
    }
    
    
    
    @Override
    public String toString() {
        return "Insumo - \nCódigo: " + codigo + "\nNome: " + nome + "\nTipo: " + tipo + "\nDescrição: " + descricao + "\n";
    } 

    public Insumo(int codigo, String nome, String tipo, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Insumo() {

    }
    
    public String getPrioridadeString(boolean prioridade){
        if(prioridade){
            return "Sim";
        }else{
            return "Não";
        }
    }

    public boolean isPrioridade() {
        return prioridade;
    }

    public void setPrioridade(boolean prioridade) {
        this.prioridade = prioridade;
    }
    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
