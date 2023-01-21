package models.usuario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import models.localizacao.Endereco;
import models.musica.Musica;
import play.data.validation.Unique;
import play.db.jpa.Blob;
import play.db.jpa.GenericModel;
import play.libs.Crypto;

@Entity
public class Usuario extends GenericModel{
    @Id
    @GeneratedValue
    public Long id;
    
    public String firstName;
    public String lastName;

    @Unique(message="Já existe um usuário com este email")
    public String email;

    public String password;

    @ManyToOne
    public Endereco endereco;

    @ManyToMany
    public List<Musica> musicasFavoritas;
    
    @Enumerated(EnumType.STRING)
    public Genero genero;
    
    public Blob photo;

    @Enumerated(EnumType.STRING)
	public Papel papel;


    public void criptografarSenha(){
        String passwordEnconded = Crypto.passwordHash(password);
        this.password = passwordEnconded;
    }

    public String toString() {
        return this.firstName + " " + this.lastName;
    }
    
}
