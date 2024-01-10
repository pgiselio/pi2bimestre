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
import play.data.validation.Required;
import play.db.jpa.GenericModel;

@Entity
public class Musica extends GenericModel{
    @Id
    @GeneratedValue
    public Long id;
    
    @Required(message="O título da música é obrigatório")
    public String name;

    @Column(columnDefinition = "TEXT")
    public String lyric;
    public boolean isDeleted;
    public long views;

    public boolean approved;
    public boolean approvedByMod;

    @Required(message="É necessário informar um artista")
    @ManyToOne
    public Artista artist;

    @ManyToOne
    public Usuario sendedBy;

    @Temporal(TemporalType.DATE)
    public Date additionDate;

    @ManyToOne
    public GeneroMusical gender;

    public long getFavoriteNumber() {
        return Usuario.count("musicasFavoritas = ?1", this);
    }

    public void addView() {
        views++;
        save();
    }
}
