package controllers;

import play.*;
import play.data.validation.Valid;
import play.mvc.*;
import play.mvc.results.RenderJson;
import security.Seguranca;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import models.*;
import models.artista.Artista;
import models.musica.Musica;
import models.usuario.Papel;
import models.usuario.Usuario;

@With(Seguranca.class)
public class Musicas extends Controller {

    public static void index() {
        render();
    }

    public static void form(Musica music) {
        render(music);
    }

    public static void detalhar(Long id) {
        Boolean isFavorite = null;
        if (session.get("userSession") != null) {
            Usuario user = Usuario.find("email = ?1", session.get("userSession")).first();
            isFavorite = user.musicasFavoritas.contains(Musica.findById(id));
        }
        Musica musica = Musica.findById(id);
        if (musica != null) {
            musica.views++;
            musica.save();
        }
        render(musica, isFavorite);
    }

    public static void findByName(String name) {
        final String nameUpperCase = name.toUpperCase();

        List<Musica> musics = Musica.find("upper(name) like ?1 AND isDeleted = ?2",
                "%" + nameUpperCase + "%", false)
                .fetch();
        renderTemplate("Musicas/listar.html", name, musics);
    }

    public static void listar() {
        List<Musica> musics = Musica.find("isDeleted = ?1", false).fetch();
        render(musics);
    }

    public static List<Musica> listarTop10() {
        List<Musica> musics = Musica.find("SELECT m FROM Musica m WHERE isDeleted = ?1"
                + " ORDER BY m.views DESC", false)
                .fetch(10);
        return musics;
    }

    public static void save(@Valid Musica music) {
        if (params.get("music.artist.id").equals("-1") && params.get("newartist.name") != null) {
            Artista artist = new Artista();
            artist.name = params.get("newartist.name");
            artist.save();
            music.artist = artist;
            save(music);
        }
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            form(music);
        }
        Artista artist = Artista.findById(music.artist.id);
        Musica musica = Musica.find("name = ?1 AND artist = ?2", music.name, artist).first();
        if (musica != null) {
            validation.addError("music.name", "Música já cadastrada!");
            validation.keep();
            params.flash();
            form(music);
        }
        Usuario usuario = Usuario.find("email = ?1", session.get("userSession")).first();
        music.additionDate = new Date();
        music.sendedBy = usuario;
        music.save();
        listar();
    }

    public static void delete(Long id) {
        final Musica music = Musica.findById(id);
        music.isDeleted = true;
        music.save();
        listar();
    }
    public static void alternarFavorito(Long id) {
        Usuario usuario = Usuario.find("email = ?1", session.get("userSession")).first();
        Musica musica = Musica.findById(id);
        if(musica == null) {
            notFound("Música não encontrada!");
        }
        Boolean isFavorite = usuario.musicasFavoritas.contains(musica);
        if (isFavorite) {
            usuario.musicasFavoritas.remove(musica);
        } else {
            usuario.musicasFavoritas.add(musica);
        }
        usuario.save();
        JsonObject json = new JsonObject();
        json.addProperty("isFavorite", !isFavorite);
        renderJSON(json);
    }

    public static void saveEditar(@Valid Musica music) {
        if (params.get("music.artist.id").equals("-1") && params.get("newartist.name") != null) {
            Artista artist = new Artista();
            artist.name = params.get("newartist.name");
            artist.save();
            music.artist = artist;
            saveEditar(music);
        }
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            form(music);
        }
        String papel = session.get("papel");
        if (!Papel.ADMINISTRADOR.name().equals(papel) && !Papel.MODERADOR.name().equals(papel)) {
            Musica musica = Musica.find("id = ?1 AND artist = ?2", music.id, music.artist).first();
            if (musica == null) {
                validation.addError("music.name", "Você não pode mudar o artista!");
                validation.keep();
                params.flash();
                editar(music.id);
            }
        }
        Usuario usuario = Usuario.find("email = ?1", session.get("userSession")).first();
        music.additionDate = new Date();
        music.sendedBy = usuario;
        music.save();
        listar();
    }
    public static void editar(Long id) {
        Musica music = Musica.findById(id);
        renderTemplate("Musicas/form.html", music);
    }


}