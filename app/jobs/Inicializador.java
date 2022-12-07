package jobs;


import org.apache.commons.codec.digest.Crypt;

import models.localizacao.Endereco;
import models.usuario.Genero;
import models.usuario.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

	@Override
	public void doJob() throws Exception {
		if (Usuario.count() == 0) {
			Usuario admin = new Usuario();
			Endereco endereco = new Endereco();
			String passwordEnconded = Crypt.crypt("admin");
			endereco.uf = "RN";
			admin.email = "admin";
			admin.password = passwordEnconded;
			admin.firstName ="admin";
			admin.lastName ="admin";
			admin.endereco = endereco;
			admin.genero = Genero.MASCULINO;
			admin.criptografarSenha();
			admin.save();
		}
		System.out.println("O banco foi populado!");
	}
}
