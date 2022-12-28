package models.usuario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import models.localizacao.Endereco;
import models.musica.Musica;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model{
    
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    @ManyToOne
    public Endereco endereco;

    @ManyToMany
    public List<Musica> musicasFavoritas;

    @OneToMany
    public List<Musica> musicasEnviadas;
    
    @Enumerated(EnumType.STRING)
    public Genero genero;

    public void criptografarSenha(){
        String passwordEnconded = Crypto.passwordHash(password);
        this.password = passwordEnconded;
    }

    public String toString() {
        return this.firstName + " " + this.lastName;
    }
    
}
