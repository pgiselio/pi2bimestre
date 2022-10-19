package models.musica;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.dom4j.rule.Mode;

import play.db.jpa.Model;

@Entity
public class Musica extends Model{
    
    public String name;

    @Column(columnDefinition = "TEXT")
    public String lyric;
    public int likes;
    public boolean isDeleted;

    @ManyToOne
    public GeneroMusical gender;

}
