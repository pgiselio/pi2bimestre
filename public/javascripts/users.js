function validarCadastro(form) {
    var nome = cadastroForm.nome;
    var sobrenome = cadastroForm.sobrenome;
    var email = cadastroForm.email;
    var senha = cadastroForm.senha;
    var confirmSenha = cadastroForm.confirmSenha;
    var sexo = cadastroForm.sexoOP;
    var estado = cadastroForm.cmbEstado;
    var aux = 0;
    var erros = "";
    if (form != undefined) {
        if(form.value == "")
        form.classList.add("errorField");
    } else {
        if (nome.value == "") {
            erros += "Nome não informado!<br/>";
            nome.classList.add("errorField");
            aux++;

        }
        if (sobrenome.value == "") {
            erros += "Sobrenome não informado!<br/>";
            sobrenome.classList.add("errorField");
            aux++;

        }
        if (email.value == "") {
            erros += "E-mail não informado!<br/>";
            email.classList.add("errorField");
            aux++;

        }
        if (senha.value == "") {
            erros += "Senha não informada!<br/>";
            senha.classList.add("errorField");
            aux++;

        } else if (senha.value.length < 8 || senha.value.length > 20) {
            erros += "A senha deve ter entre 8 e 20 caracteres!<br/>";
            senha.classList.add("errorField");
            aux++;

        } else if (confirmSenha.value == "") {
            confirmSenha.classList.add("errorField");
            aux++;
        }
        if (senha.value != confirmSenha.value) {
            erros += "As senhas não conferem! <br/>";
            senha.classList.add("errorField");
            confirmSenha.classList.add("errorField");
            aux++;
        }
        if (sexo.value == "") {
            erros += "Sexo não informado!<br/>";
            aux++;
        }
        if (estado.value == "") {
            erros += "Estado não informado!";
            estado.classList.add("errorField");
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