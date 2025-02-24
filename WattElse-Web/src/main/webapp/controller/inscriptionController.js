'use strict';
import { getBackUrl } from "./backUrl.js";

import{
    mailAlreadyExist,
} from "../view/inscriptionView.js";

import {
    authenticate
} from "../commands/commands.js";

import {
    incompleteFormDisplay,
    resetForm,
    incorrectEmailDisplay
} from "../commands/commandsView.js";

import {
    getAddress
} from "../address/addressFormController.js";

addActionToButtons();

function addActionToButtons() {
    const inscriptionButton = document.getElementById("inscriptionButton");
    inscriptionButton.addEventListener("click", getUserInformations);

    const navigateToConnectionButton = document.getElementById("navigateToConnectionButton");
    navigateToConnectionButton.addEventListener("click", navigateToConnection);
}

function getUserInformations() {

    const email = this.form.addMailInput.value;
    const password = this.form.addPasswordInput.value;

    const lastName = this.form.addLastNameInput.value;
    const firstName = this.form.addFirstNameInput.value;
    const phone = this.form.addPhoneInput.value;
    const birthdate = this.form.addBirthdateInput.value;

    const validatedAddress = getAddress();

    const latitude = validatedAddress.lat;
    const longitude = validatedAddress.lon;
    const display_name = validatedAddress.display_name;

    if (email === "" || password === "" || lastName === "" ||
        firstName === "" || phone === "" || birthdate === "" ||
        latitude === "" || longitude === "" || display_name === "") {
        incompleteFormDisplay();

    } else {
        resetForm();

        const newUser = {
            email: email,
            password: password,

            lastName: lastName,
            firstName: firstName,
            phone: phone,
            birthDate: birthdate,

            latitude: latitude,
            longitude : longitude,
            addressDisplay: display_name,
        };
        console.log(newUser);
        fetchNewUser(newUser);
    }
}

function fetchNewUser(newUser) {

    const requestOptions = {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body: JSON.stringify(newUser)
    };

    console.log(requestOptions);

    fetch(`${getBackUrl()}/test/register/user`, requestOptions)
        .then(response => response.ok ? getToAuthenticate(newUser.email,newUser.password) : Promise.reject(response))
        .catch(response => {
            console.info("Une erreur s'est produite lors de l'inscription : ",`${response.status} (${response.statusText})`);
            mailAlreadyExist();
        }
     );
    
}

function getToAuthenticate(email,password){
    authenticate(email,password);
    resetForm();
}

function navigateToConnection(){
    window.location.href= "connection.html";
}