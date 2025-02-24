'use strict';
import { getBackUrl } from "./backUrl.js";

import { 
    createStationButton 
} from "../view/myChargingStationsView.js";

import {
    incompleteFormDisplay,
    resetForm
} from "../commands/commandsView.js";

import {
    fetchSockets,
    fetchPowerSockets,
    fetchPriceSockets
} from "../commands/selectionsInForms.js"


import {
    getAddress
} from "../address/addressFormController.js";

import {
    getUserId,
    getUserToken
} from "../commands/commands.js";

let myStations = undefined;
const backUrl = getBackUrl();
fetchStations();
const stationForm = createStationForm();


fetchSockets("#station-form-socketType");
fetchPowerSockets("#station-form-power");
fetchPriceSockets("#station-form-tarification-unit");
document.getElementById("station-form-button-new-station").addEventListener("click", displayNewStation);
hideStation();



function displayStation(station) {
    stationForm.root.setAttribute("class","sticky-top");
    stationForm.name.value = station.name;
    stationForm.socketType.value = JSON.stringify(station.socket);
    stationForm.power.value = JSON.stringify(station.power);
    stationForm.tarificationUnit.value = JSON.stringify(station.tarification.typeTarification);
    stationForm.tarification.value = station.tarification.cost;
    document.getElementById("address-search-field").value = station.display_address; // l'adresse est gérée en async, donc indispo au start. Mieux vaux la récup quand on en à besoin
    displayPlanning(station.planning);

    stationForm.deleteButton.addEventListener("click", () => deleteStation(station));
    stationForm.deleteButton.setAttribute("class","btn btn-primary redbutton");
    stationForm.modifyButton.addEventListener("click", () => modifyStation(station));
    stationForm.modifyButton.setAttribute("class","btn btn-primary smallGreenButton");
    stationForm.addButton.setAttribute("class","d-none");
}

function hideStation() {
    stationForm.root.setAttribute("class","d-none");
}

function createStationForm() {
    return {
        root: document.getElementById("station-form-root"),
        name: document.getElementById("station-form-name"),
        socketType: document.getElementById("station-form-socketType"),
        power: document.getElementById("station-form-power"),
        tarification: document.getElementById("station-form-tarification"),
        tarificationUnit: document.getElementById("station-form-tarification-unit"),
        deleteButton: document.getElementById("station-form-button-delete"),
        modifyButton: document.getElementById("station-form-button-modify"),
        addButton: document.getElementById("station-form-button-add")
    }
}

function createStationList() {
    document.getElementById("results").innerHTML = "";
    for (const station of myStations) {
        createStationButton(station).addEventListener("click",() => displayStation(station))
    }
}

function displayNewStation() {
    stationForm.root.setAttribute("class","sticky-top");
    stationForm.name.value = "";
    stationForm.socketType.value = 0;
    stationForm.power.powerId = 0;
    stationForm.tarification.value = 0;
    stationForm.tarification.unit = 0;
    document.getElementById("address-search-field").value = "";
    displayPlanning(standardPlanning());

    stationForm.deleteButton.setAttribute("class","d-none");
    stationForm.modifyButton.setAttribute("class","d-none");
    stationForm.addButton.addEventListener("click", () => addStation());
    stationForm.addButton.setAttribute("class","btn btn-primary largeGreenButton");
}

function displayPlanning(planning) {
    for (const day in planning) {
        if (Object.prototype.hasOwnProperty.call(planning, day)) {
            const element = planning[day];
            for (let i = 0; i < 2; i++) {
                document.getElementById(`${day}-${i}-start`).value = element[i].start;
                document.getElementById(`${day}-${i}-end`).value = element[i].end;
            }
        }
    }
}

function deleteStation(station) {
    // envoie d'un dto approprié dans le back
}

function modifyStation(station) {
    // envoie d'un dto approprié dans le back
}

function addStation(station) {

    console.log("OK")

    const name = document.getElementById("station-form-name").value;

    const power = JSON.parse(document.getElementById("station-form-power").value);
    const typeTarification = JSON.parse(document.getElementById("station-form-tarification-unit").value);
    const cost = document.getElementById("station-form-tarification").value;
    const socket = JSON.parse(document.getElementById("station-form-socketType").value);

    const validatedAddress = getAddress();

    const latitude = validatedAddress.lat;
    const longitude = validatedAddress.lon;
    const addressDisplay = validatedAddress.display_name;

    if(name === "" || typeTarification === "" ||socket === "" || power === "" 
        || latitude === "" ||longitude === "" || addressDisplay === "" ) {
            incompleteFormDisplay();
        } else {
            
            const userId = getUserId();
            const token = getUserToken();

            /*const station = {
                name: name,
                userId: userId,
                tarification: {
                    tarificationId: null,
                    typeTarification: typeTarification,
                    cost: cost,
                    dateTarificationStart: null,
                    dateTarificationEnd: null
                },                
                socket: socket,
                power: power,
                latitude: latitude,
                longitude: longitude,
                addressDisplay: addressDisplay
            }*/

            const requestOptions = {
                method: "POST",
                headers: {
                    "Authorization": "Bearer " + token, 
                    "Content-Type": "application/json"},
                body: JSON.stringify({
                    name: name,
                    userId: userId,
                    tarification: {
                        tarificationId: -1,
                        typeTarification: typeTarification,
                        cost: cost,
                        dateTarificationStart: null,
                        dateTarificationEnd: null
                    },                
                    socket: socket,
                    power: power,
                    latitude: latitude,
                    longitude: longitude,
                    addressDisplay: addressDisplay
                })
                
            };

fetch(`${backUrl}/myChargingStations/register/station`,requestOptions)
.then(response => response.ok ? fetchStations() : Promise.reject(response))
        .catch(response => {
            console.info(
                "Une erreur s'est produite lors de l'ajout d'une nouvelle station de recharge : "
                , `${response.status} (${response.statusText})`);
        }
    )
}
}

function fetchStations() { //corps de la method temporaire (il faudra aller chercher les vraies stations)
    
    let user = JSON.parse(sessionStorage.getItem("user"));
    const requestOptions = { headers: { "Authorization": "Bearer " + user.token } };

    fetch(`${backUrl}/myChargingStations/user/${user.id}/stations`,requestOptions)
    .then(response => response.ok ? response.json() : Promise.reject(response))
    .then(json => {
        for (const station of json) {
            station.planning = standardPlanning();
        }
        myStations = json;
        createStationList();
        return json;
    })
    .catch(response => {
        console.info(
            "Une erreur s'est produite lors de la mise à jour de la liste des stations de recharge : "
            , `${response.status} (${response.statusText})`);
    });
}
    

function standardPlanning() {
    return {
        monday: getStandartHoraire(),
        tuesday: getStandartHoraire(),
        wednesday: getStandartHoraire(),
        thursday: getStandartHoraire(),
        friday: getStandartHoraire(),
        saturday: getStandartHoraire(),
        sunday: getStandartHoraire()
    };
}

function getStandartHoraire() {
    return [{
        start: "08:00",
        end: "12:00"
    },{
        start: "14:00",
        end: "18:00"
    }];
}