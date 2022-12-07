function validarCadastro(form) {
    var firstname = cadastroForm.firstname;
    var lastname = cadastroForm.lastname;
    var email = cadastroForm.email;
    var password = cadastroForm.password;
    var confirmPassword = cadastroForm.confirmPassword;
    var genero = cadastroForm.genero;
    var endereco = cadastroForm.endereco;
    var aux = 0;
    var erros = "";
    if (form != undefined) {
        if(form.value == "")
        form.classList.add("errorField");
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

        } else if (password.value.length < 8 || password.value.length > 20) {
            erros += "A senha deve ter entre 8 e 20 caracteres!<br/>";
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

        } else{
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

    if (aux == 0)
        return true;
    else {
        document.getElementById("errorMessage").innerHTML = erros;
        document.getElementById("errorMessage").style.display = "block";
        return false;
    }

}

function removeErrorClass(form) {
    form.classList.remove("errorField");
}