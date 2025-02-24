'use strict';

import{
    authenticate
} from "../commands/commands.js"

import{
    resetForm,
} from "../commands/commandsView.js";

addActionToInscriptionButton();

function addActionToInscriptionButton() {
    const connectionButton = document.getElementById("connectionButton");
    connectionButton.addEventListener("click", gettingReadyToAuthenticate);
    
    const inscriptionButton = document.getElementById("inscriptionButton");
    inscriptionButton.addEventListener("click",redirect)
}

function gettingReadyToAuthenticate(){
    let email = this.form.emailInput.value;
    let password = this.form.passwordInput.value;
    authenticate(email,password);
    
   resetForm();
}

function redirect(){
    window.location.href= "inscription.html";
}
