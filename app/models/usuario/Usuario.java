package models.usuario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import org.apache.commons.codec.digest.Crypt;

import models.localizacao.Endereco;
import models.musica.Musica;
import play.db.jpa.Model;

@Entity
public class Usuario extends Model{
    
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public Endereco endereco;

    @ManyToMany
    public List<Musica> musicasFavoritas;
    
    @Enumerated(EnumType.STRING)
    public Genero genero;

    public void criptografarSenha(){
        Crypt.crypt(password);
    }
    
}
