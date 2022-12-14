function validarCadastro(form) {
  const firstname = cadastroForm["newUser.firstName"];
  const lastname = cadastroForm["newUser.lastName"];
  const email = cadastroForm["newUser.email"];
  const password = cadastroForm["newUser.password"];
  const confirmPassword = cadastroForm["newUser.confirmPassword"];
  const genero = cadastroForm["newUser.genero"];
  const endereco = cadastroForm["newUser.endereco.id"];

  let aux = 0;
  let erros = "";

  if (form != undefined) {
    if (form.value == "") form.classList.add("errorField");
  } else {
    if (firstname.value == "") {
      erros += "Nome não informado!<br/>";
      firstname.classList.add("errorField");
      aux++;
    }
    if (lastname.value == "") {
      erros += "Sobrenome não informado!<br/>";
      lastname.classList.add("errorField");
      aux++;
    }
    if (email.value == "") {
      erros += "E-mail não informado!<br/>";
      email.classList.add("errorField");
      aux++;
    }
    if (password.value == "") {
      erros += "Senha não informada!<br/>";
      password.classList.add("errorField");
      aux++;
    } else if (password.value.length < 8) {
      erros += "A senha deve ter no mínimo 8 caracteres!<br/>";
      password.classList.add("errorField");
      aux++;
    }
    if (confirmPassword.value == "") {
      confirmPassword.classList.add("errorField");
      aux++;
    }
    if (password.value != confirmPassword.value) {
      erros += "As senhas não conferem! <br/>";
      password.classList.add("errorField");
      confirmPassword.classList.add("errorField");
      aux++;
    }
    if (genero.value == "") {
      erros += "Sexo não informado!<br/>";
      genero.classList.add("errorField");
      aux++;
    }
    if (endereco.value == "") {
      erros += "Estado não informado!";
      endereco.classList.add("errorField");
      aux++;
    }

    if (aux == 0) {
      document.getElementById("errorMessage").innerHTML = " ";
      document.getElementById("errorMessage").style.display = "none";
      return true;
    } else {
      document.getElementById("errorMessage").innerHTML = erros;
      document.getElementById("errorMessage").style.display = "block";
      return false;
    }
  }
}

function validarLogin() {
  var email = loginForm.email;
  var senha = loginForm.senha;
  var aux = 0;
  var erros = "";

  if (senha.value == "") {
    erros += "Senha não informada!<br/>";
    senha.classList.add("errorField");
    aux++;
  }
  if (email.value == "") {
    erros += "E-mail não informado!<br/>";
    email.classList.add("errorField");
    aux++;
  }

  if (aux == 0) return true;
  else {
    document.getElementById("errorMessage").innerHTML = erros;
    document.getElementById("errorMessage").style.display = "block";
    return false;
  }
}

function removeErrorClass(form) {
  form.classList.remove("errorField");
}
