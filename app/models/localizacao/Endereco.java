package models.localizacao;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Endereco extends Model{
    
    public String uf;

}
