package models.musica;

import java.util.List;

import javax.persistence.Entity;

import org.dom4j.rule.Mode;

import play.db.jpa.Model;

@Entity
public class Musica extends Model{
    
    public String name;
    public int likes;
    public GeneroMusical gender;

}
