package controllers;

import play.*;
import play.mvc.*;
import security.GetUserLoggedIn;

import java.util.*;

import models.*;
import models.artista.Artista;
import models.musica.Musica;
import models.usuario.Usuario;

@With(GetUserLoggedIn.class)
public class Artistas extends Controller {
    public static void index() {
        List<Artista> artists = Artista.findAll();
        artists.sort(Comparator.comparing(Artista::getViews).reversed());
        if (artists.size() > 100) {
            artists = artists.subList(0, 100);
        }
        render(artists);
    }

    public static void salvar(Artista artista) {
        artista.save();
        index();
    }

    public static void detalhar(Long id) {
        Artista artist = Artista.findById(id);
        if (artist == null)
            forbidden("Artista não encontrado!");
        List<Musica> musics = Musica.find("artist = ?1 ORDER BY name", artist).fetch();
        detalharPeloNome(artist.urlString);
        render(artist, musics);
    }

    public static void detalharPeloNome(String artista) {
        final String artistaLowerCase = artista.toLowerCase().replace(" ", "-");
        Artista artist = Artista.find("replace(lower(urlString), ' ', '-') like ?1", "%" + artistaLowerCase + "%")
                .first();
        if (artist == null)
            notFound("Artista não encontrado!");
        if (!artist.urlString.equals(artista) || !artist.urlString.equals(artistaLowerCase))
            detalharPeloNome(artist.urlString);
        List<Musica> musics = Musica.find("artist = ?1 ORDER BY name", artist).fetch();
        renderTemplate("Artistas/detalhar.html", artist, musics);
    }

    public static void encontrarMusicaPorNomeArtista(String a, String m) {
        String artistaLowerCase = a.toLowerCase().replace(" ", "-");

        Artista artist = Artista.find("replace(lower(urlString), ' ', '-') like ?1", artistaLowerCase + "%").first();
        if (artist == null)
            notFound("Artista não encontrado!");

        String nameLowerCase = m.toLowerCase().replace(" ", "-");
        if (!a.equals(artist.urlString) || !m.equals(nameLowerCase))
            encontrarMusicaPorNomeArtista(artist.urlString, nameLowerCase);

        Musica musica = Musica
                .find("isDeleted = ?1 AND replace(lower(name), ' ', '-') like ?2 AND artist = ?3",
                        false, "%" + nameLowerCase + "%", artist)
                .first();
                
        if (musica != null) {
            musica.addView();
        }

        Boolean isFavorite = null;
        if (session.get("userSession") != null) {
            Usuario user = Usuario.find("email = ?1", session.get("userSession")).first();
            isFavorite = user.musicasFavoritas.contains(musica);
        }

        renderTemplate("Musicas/detalhar.html", musica, isFavorite);
    }

    public static void criarNovoArtista() {
        render();
    }

    public static void getArtistas(String term, Long id) {
        List<Artista> artists;
        if (term == null || term.isEmpty()) {
            artists = Artista.find("order by name").fetch();

        } else {
            String termLowerCase = term.toLowerCase();
            artists = Artista.find("lower(name) like ?1", "%" + termLowerCase + "%").fetch();
        }
        artists.forEach(artista -> {
            artista.biography = null;
            artista.musics = null;
            artista.photo = null;

        });
        renderJSON(artists);
    }

    public static void enviarFotoForm() {
        render();
    }

    public static void enviarFoto(Artista a) {
        Artista artista = Artista.findById(a.id);
        a.photo = a.photo;
        artista.save();
        enviarFotoForm();
    }

    public static void downloadFoto(Long id) {
        Artista artista = Artista.findById(id);
        renderBinary(artista.photo.getFile());
    }

}