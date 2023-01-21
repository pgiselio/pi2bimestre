package jobs;

import java.util.Collections;

import org.apache.commons.codec.digest.Crypt;

import models.artista.Artista;
import models.localizacao.Endereco;
import models.musica.Musica;
import models.usuario.Genero;
import models.usuario.Papel;
import models.usuario.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

	@Override
	public void doJob() throws Exception {
		if (Endereco.count() == 0) {
			Endereco ac = new Endereco("AC", "Acre");
			ac.save();
			Endereco al = new Endereco("AL", "Alagoas");
			al.save();
			Endereco ap = new Endereco("AP", "Amapá");
			ap.save();
			Endereco am = new Endereco("AM", "Amazonas");
			am.save();
			Endereco ba = new Endereco("BA", "Bahia");
			ba.save();
			Endereco ce = new Endereco("CE", "Ceará");
			ce.save();
			Endereco df = new Endereco("DF", "Distrito Federal");
			df.save();
			Endereco es = new Endereco("ES", "Espirito Santo");
			es.save();
			Endereco go = new Endereco("GO", "Goiás");
			go.save();
			Endereco ma = new Endereco("MA", "Maranhão");
			ma.save();
			Endereco mt = new Endereco("MT", "Mato Grosso");
			mt.save();
			Endereco ms = new Endereco("MS", "Mato Grosso do Sul");
			ms.save();
			Endereco mg = new Endereco("MG", "Minas Gerais");
			mg.save();
			Endereco pa = new Endereco("PA", "Pará");
			pa.save();
			Endereco pb = new Endereco("PB", "Paraíba");
			pb.save();
			Endereco pr = new Endereco("PR", "Paraná");
			pr.save();
			Endereco pe = new Endereco("PE", "Pernambuco");
			pe.save();
			Endereco pi = new Endereco("PI", "Piauí");
			pi.save();
			Endereco rj = new Endereco("RJ", "Rio de Janeiro");
			rj.save();
			Endereco rn = new Endereco("RN", "Rio Grande do Norte");
			rn.save();
			Endereco rs = new Endereco("RS", "Rio Grande do Sul");
			rs.save();
			Endereco ro = new Endereco("RO", "Rondônia");
			ro.save();
			Endereco rr = new Endereco("RR", "Roraima");
			rr.save();
			Endereco sc = new Endereco("SC", "Santa Catarina");
			sc.save();
			Endereco sp = new Endereco("SP", "São Paulo");
			sp.save();
			Endereco se = new Endereco("SE", "Sergipe");
			se.save();
			Endereco to = new Endereco("TO", "Tocantins");
			to.save();
		}
		if (Usuario.count() == 0) {
			Usuario admin = new Usuario();
			Endereco endereco = Endereco.find("uf = ?1", "RN").first();
			admin.email = "admin";
			admin.password = "admin";
			admin.firstName = "Admin";
			admin.lastName = "da Silva";
			admin.endereco = endereco;
			admin.genero = Genero.MASCULINO;
			admin.papel = Papel.ADMINISTRADOR;
			admin.criptografarSenha();
			admin.save();
		}
		if (Artista.count() == 0) {
			Artista artista = new Artista();
			artista.name = "Artista Teste";
			artista.biography = "Biografia do artista";
			artista.musics = Collections.<Musica>emptyList();
			artista.save();

			Artista artista2 = new Artista();
			artista2.name = "Artista Teste 2";
			artista2.biography = "Biografia do artista";
			artista2.musics = Collections.<Musica>emptyList();
			artista2.save();
		}
		System.out.println("O banco foi populado!");
	}
}
