package models.musica;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.dom4j.rule.Mode;

import models.artista.Artista;
import models.usuario.Usuario;
import play.db.jpa.Model;

@Entity
public class Musica extends Model{
    @Id
    @GeneratedValue
    public Long id;
    
    public String name;

    @Column(columnDefinition = "TEXT")
    public String lyric;
    public int likes;
    public boolean isDeleted;
    public long views;

    @ManyToOne
    public Artista artist;

    @ManyToOne
    public Usuario sendedBy;

    @Temporal(TemporalType.DATE)
    public Date additionDate;

    @ManyToOne
    public GeneroMusical gender;

}
