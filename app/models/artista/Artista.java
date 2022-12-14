package models.artista;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import models.musica.Musica;
import play.db.jpa.Model;

@Entity
public class Artista extends Model{
    
    public String name;
    public String biography;

    @Embedded
    public RedeSocial socialNetwork;

    @OneToMany
    public List<Musica> musics;

}
