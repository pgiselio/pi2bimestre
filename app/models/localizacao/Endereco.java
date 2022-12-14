package models.localizacao;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Endereco extends Model{
    
    public String uf;
    public String ufPorExtenso;

    public Endereco (String uf, String ufPorExtenso){
        this.uf = uf;
        this.ufPorExtenso = ufPorExtenso;
    }
    
    public Endereco() {
        super();
    }

    @Override
    public String toString() {
        return "Endereco [uf=" + uf + ", ufPorExtenso=" + ufPorExtenso + "]";
    }    

}
