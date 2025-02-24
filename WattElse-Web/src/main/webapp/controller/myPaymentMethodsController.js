'use strict';

import {
    incompleteFormDisplay,
    resetForm
} from "../commands/commandsView.js";

import {
    getUserId,
    getUserToken
} from "../commands/commands.js";

import {
    getBackUrl
} from "../commands/backUrl.js"

const backUrl = `${getBackUrl()}/paymentMethods`;


addActionToPayementsButtons();
fetchCreditCardsByUserId();
fetchBankAccountsByUserId();

/// Actions des boutons
function addActionToPayementsButtons() {
    const addingCCButton = document.getElementById("addingCCButton");
    addingCCButton.addEventListener("click", displayFormCC);

    const validateAddingCCButton = document.getElementById("validateAddingCCButton");
    validateAddingCCButton.addEventListener("click", geCCInformations);

    const abbortAddingCCButton = document.getElementById("abbortAddingCCButton");
    abbortAddingCCButton.addEventListener("click",displayFormCC);

    const addingBAButton = document.getElementById("addingBAButton");
    addingBAButton.addEventListener("click", displayFormBA);

    const validateAddingBAButton = document.getElementById("validateAddingBAButton");
    validateAddingBAButton.addEventListener("click", geBAInformations);

    const abbortAddingBAButton = document.getElementById("abbortAddingBAButton");
    abbortAddingBAButton.addEventListener("click",displayFormBA);
}

/// Gestion de la visibilité des deux formulaires, Crédit card et Bank account
function displayFormCC() {
    const addingCCForm = document.getElementById("addingCCForm");
    const addingBAForm = document.getElementById("addingBAForm");


    if (addingCCForm.classList.contains('d-none')) {
        addingCCForm.classList.remove('d-none');
    } else {
        addingCCForm.classList.add('d-none');
        addingCCForm.reset();
    }

    if (!addingBAForm.classList.contains('d-none')) {
        addingBAForm.classList.add('d-none');
    }

}
function displayFormBA(){
    const addingBAForm = document.getElementById("addingBAForm");
    const addingCCForm = document.getElementById("addingCCForm");
    
    if (addingBAForm.classList.contains('d-none')) {
        addingBAForm.classList.remove('d-none');
    } else {
        addingBAForm.classList.add('d-none');
        addingBAForm.reset();
    }

    if (!addingCCForm.classList.contains('d-none')) {
        addingCCForm.classList.add('d-none');
    }
}

/// Récupérer les informations du formulaire d'ajout de CC
function geCCInformations() {

    const numberCard = this.form.addNumberCardInput.value;
    const expirationDate = this.form.addExpirationDateInput.value;
    const ccs = this.form.addccsInput.value;

    if (numberCard === "" || expirationDate === "" ||ccs === "") {

        incompleteFormDisplay();

    } else {
        resetForm();

        const userID = getUserId();
        console.log(userID);

        const newCC = {
            idUser : userID,
            registerDate : null,

            cardNumber: numberCard,
            expiryDate: expirationDate,
            ccs: ccs,
        };
        console.log(newCC);
        fetchNewCC(newCC);
    }
}

/// Récupérer les informations du formulaire d'ajout de BA
function geBAInformations() {

    const iban = this.form.addIBANInput.value;
    const bic = this.form.addBICInput.value;

    if (iban === "" || bic === "") {

        incompleteFormDisplay();

    } else {
        resetForm();

        const userID = getUserId();
        console.log(userID);

        const newBA = {
            userId : userID,
            registerDate : null,

            iban: iban,
            bic: bic,
        };
        console.log(newBA);
        fetchNewBA(newBA);
    }
}

/// Ajout d'une CC au back
function fetchNewCC(newCC) {

    const token = getUserToken();

    const requestOptions = {
        method: "POST",
        headers: { "Authorization": "Bearer " + token, 
            "Content-Type": "application/json",
         },
        body: JSON.stringify(newCC)
    };

    console.log(requestOptions);

    fetch(`${backUrl}/addCreditCard`, requestOptions)

        .then(response => response.ok ? null : Promise.reject(response))
        .then(window.location.href= "myPaymentMethods.html")

        .catch(response => {
            console.info(
                "Une erreur s'est produite lors de l'ajout d'une nouvelle carte de credit : "
                , `${response.status} (${response.statusText})`);
        }
    );
}

/// Ajout d'un BA au back
function fetchNewBA(newBA) {

    const token = getUserToken();

    const requestOptions = {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + token, 
            "Content-Type": "application/json"},
        body: JSON.stringify(newBA)
    };

    console.log(requestOptions);

    fetch(`${backUrl}/addBankAccount`, requestOptions)

        .then(response => response.ok ? null : Promise.reject(response))
        .then(window.location.href= "myPaymentMethods.html")

        .catch(response => {
            console.info(
                "Une erreur s'est produite lors de l'ajout d'un nouveau compte :  "
                , `${response.status} (${response.statusText})`);
        }
        
    );
}

// Récupérer et afficher les Cartes de crédit
function fetchCreditCardsByUserId() {
    const userID = getUserId();
    const token = getUserToken();

    const requestOptions = { headers: { "Authorization": "Bearer " + token } };
    fetch(`${backUrl}/fetch/creditCard/${userID}`, requestOptions)
    .then(response => response.ok ? response.json() : Promise.reject(response))
    .then(textJson => {
        const results = document.getElementById("results");

        const cardTitle = document.createElement("h4");
        cardTitle.setAttribute("class", "text-center text-primary mb-4" );
        cardTitle.textContent = "Mes cartes bancaires";
        results.appendChild(cardTitle);

        for (let card of textJson) {
            const creditCardDiv = document.createElement("div");
            creditCardDiv.setAttribute("class", "CreditCards");

            const divInfo = document.createElement("div");
            divInfo.setAttribute("class", "info");
            creditCardDiv.appendChild(divInfo);

            const divNumberCard = document.createElement("div");
            divNumberCard.setAttribute("class", "numberCard");
            const spanNumberCard = document.createElement("span");
            spanNumberCard.setAttribute("class", "numberCard");
            spanNumberCard.textContent = `Numéro carte : ${card.cardNumber}`;
            divNumberCard.appendChild(spanNumberCard);
            divInfo.appendChild(divNumberCard);

            const divExpirationDate = document.createElement("div");
            divExpirationDate.setAttribute("class", "expirationDate");
            const spanExpirationDate = document.createElement("span");
            spanExpirationDate.setAttribute("class", "expirationDate");
            spanExpirationDate.textContent = `Date d'expiration : ${card.expiryDate}`;
            divExpirationDate.appendChild(spanExpirationDate);
            divInfo.appendChild(divExpirationDate);

            const divCCS = document.createElement("div");
            divCCS.setAttribute("class", "CCS");
            const spanCCS = document.createElement("span");
            spanCCS.setAttribute("class", "CCS");
            spanCCS.textContent = `Code de sécurité : ${card.ccs}`;
            divCCS.appendChild(spanCCS);
            divInfo.appendChild(divCCS);

            const divButtons = document.createElement("div");
            divButtons.setAttribute("class", "buttonsCS align-items-center");
            const modifyButton = document.createElement("input");
            modifyButton.setAttribute("id", "modifyCC");
            modifyButton.setAttribute("class", "btn btn-primary smallGreenButton");
            modifyButton.setAttribute("value", "Modifier");
            modifyButton.addEventListener("click", () => modCard(card));
            divButtons.appendChild(modifyButton);

            const deleteButton = document.createElement("input");
            deleteButton.setAttribute("id", "deleteCC");
            deleteButton.setAttribute("class", "btn btn-primary redbutton");
            deleteButton.setAttribute("value", "Supprimer");
            deleteButton.addEventListener("click", () => suppCard(card.idCreditCard));
            divButtons.appendChild(deleteButton);

            divInfo.appendChild(divButtons);

            results.appendChild(creditCardDiv);
        }
    })
    .catch(response => {
        console.log(`Erreur ${response.status}`);
    });
}

// Récupérer et afficher le compte bancaire : 
function fetchBankAccountsByUserId() {
    const userID = getUserId();
    const token = getUserToken();

    const requestOptions = { headers: { "Authorization": "Bearer " + token } };
    fetch(`${backUrl}/fetch/bankaccount/${userID}`, requestOptions)
    .then(response => response.ok ? response.json() : Promise.reject(response))
    .then(textJson => {
        const results = document.getElementById("resultsBank");

        const bankAccountTitle = document.createElement("h4");
        bankAccountTitle.setAttribute("class", "text-center text-primary mb-4");
        bankAccountTitle.textContent = "Mon compte bancaire";
        results.appendChild(bankAccountTitle);

        for (let account of textJson) {
            const bankAccountDiv = document.createElement("div");
            bankAccountDiv.setAttribute("class", "BankAccount");

            const divInfo = document.createElement("div");
            divInfo.setAttribute("class", "info");
            bankAccountDiv.appendChild(divInfo);

            // Affichage de l'IBAN (partiellement masqué)
            const divIban = document.createElement("div");
            divIban.setAttribute("class", "iban");
            const spanIban = document.createElement("span");
            spanIban.setAttribute("class", "iban");
            spanIban.textContent = `IBAN : ${maskIban(account.iban)}`;
            divIban.appendChild(spanIban);
            divInfo.appendChild(divIban);

            // Affichage du BIC (partiellement masqué)
            const divBic = document.createElement("div");
            divBic.setAttribute("class", "bic");
            const spanBic = document.createElement("span");
            spanBic.setAttribute("class", "bic");
            spanBic.textContent = `BIC : ${maskBic(account.bic)}`;
            divBic.appendChild(spanBic);
            divInfo.appendChild(divBic);

            const divButtons = document.createElement("div");
            divButtons.setAttribute("class", "buttonsBankAccount align-items-center");

            // Ajouter un bouton de modification
            const modifyButton = document.createElement("input");
            modifyButton.setAttribute("id", "modifyBankAccount");
            modifyButton.setAttribute("class", "btn btn-primary smallGreenButton");
            modifyButton.setAttribute("value", "Modifier");
            modifyButton.addEventListener("click", () => modBankAccount(account));
            divButtons.appendChild(modifyButton);

            // Ajouter un bouton de suppression
            const deleteButton = document.createElement("input");
            deleteButton.setAttribute("id", "deleteBankAccount");
            deleteButton.setAttribute("class", "btn btn-primary redbutton");
            deleteButton.setAttribute("value", "Supprimer");
            deleteButton.addEventListener("click", () => suppBankAccount(account.idBankAccount));
            divButtons.appendChild(deleteButton);

            divInfo.appendChild(divButtons);

            results.appendChild(bankAccountDiv);
        }
    })
    .catch(response => {
        console.log(`Erreur ${response.status}`);
    });
}

// Fonction pour masquer l'IBAN (par exemple, garder les 4 premiers et 4 derniers caractères visibles)
function maskIban(iban) {
    if (iban && iban.length > 8) {
        return `${iban.slice(0, 4)}****${iban.slice(-4)}`;
    }
    return iban; // Si l'IBAN est trop court, on ne le masque pas
}

// Fonction pour masquer le BIC (par exemple, garder les 4 premiers et 4 derniers caractères visibles)
function maskBic(bic) {
    if (bic && bic.length > 8) {
        return `${bic.slice(0, 4)}****${bic.slice(-4)}`;
    }
    return bic; 
}
