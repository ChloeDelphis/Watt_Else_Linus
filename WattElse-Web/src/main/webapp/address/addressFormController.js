'use strict';

import {
    createSearchResult,
    showMap,
    hideMap
} from "./addressFormView.js"

let displayedmap = null;
let searchField = null;
let btn = null;
let lastRequestTime = new Date();

const requestCooldown = 1000;

fetch("./address/addressForm.html")
    .then(response => response.text())
    .then(html => {
        document.getElementById("address-form").innerHTML = html

        searchField = document.getElementById("address-search-field");
        searchField.addEventListener("change", fetchAddressSearchResult);

        btn = document.getElementById("map-confirmation-button");
        btn.addEventListener("click", confirmAddress);
    });

function fetchAddressSearchResult() {

    unconfirmAddress();

    const timer = (new Date().getTime()) - (lastRequestTime.getTime());
    if (timer < requestCooldown) return;
    lastRequestTime = new Date();

    let searchField = document.getElementById("address-search-field").value;
    searchField = searchField.replaceAll(" ","+");

    const openStreetMapRequest = `https://nominatim.openstreetmap.org/search?q=${searchField}&format=json&polygon=1&addressdetails=1`

    console.log(openStreetMapRequest);

    fetch(openStreetMapRequest)
        .then(response => response.ok ? response.json() : Promise.reject(response))
        .then(json => {
            const optionList = document.getElementById("address-list");
            optionList.innerHTML = "";

            if (json.length > 1) {
                for (const result of json) {
                    const button = createSearchResult(result);
                    button.addEventListener("click",() => validateAddress(result));
                }
            } else if (json.length == 1) {
                validateAddress(json[0])
            } else {
                document.getElementById("address-search-field").setAttribute("class","form-control btn-danger");
            }
        })
        .catch(response => {
            console.info("Une erreur s'est produite lors de la recherche d'addresse : ",`${response.status} (${response.statusText} ${response})`);
        }
    );
}

function validateAddress(mapResult) {
    displayedmap = mapResult;
    document.getElementById("address-search-field").value = displayedmap.display_name.replaceAll(",","");
    showMap(displayedmap);
    document.getElementById("map-confirmation-button").setAttribute("class","btn-dark")
    document.getElementById("address-list").innerHTML = "";
}

function unconfirmAddress() {
    document.getElementById("address-search-field").setAttribute("class","form-control");
}

function confirmAddress() {
    document.getElementById("address-search-field").setAttribute("class","form-control btn-success");
    hideMap();
}

export function getAddress() {
    return displayedmap;
}