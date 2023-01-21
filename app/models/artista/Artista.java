package models.artista;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import models.musica.Musica;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.GenericModel;
import play.db.jpa.JPABase;

@Entity
public class Artista extends GenericModel{
    @Id
    @GeneratedValue
    public Long id;

    @Required(message="O nome do artista é obrigatório")
    public String name;

    @Column(columnDefinition = "TEXT")
    public String biography;

    @Column(unique=true)
    public String urlString;

    public Blob photo;

    @Embedded
    public RedeSocial socialNetwork;

    @OneToMany
    public List<Musica> musics;

    public long getViews() {
        return Musica.count("artist = ?1", this);
    }

    @Override
    public <T extends JPABase> T save() {
        String artistURLStringGenerated = this.name.replaceAll(" ", "-").toLowerCase();
        if (this.urlString == null) {
            this.urlString = artistURLStringGenerated;
        }
        long contagemDeIgualdades = this.count("urlString = ?1", urlString);
        long indice = 1;
        while(contagemDeIgualdades > 0) {
            this.urlString = artistURLStringGenerated + indice;
            contagemDeIgualdades = this.count("urlString = ?1", urlString);
            indice++;
        }
        // TODO Auto-generated method stub
        return super.save();
    }

}
