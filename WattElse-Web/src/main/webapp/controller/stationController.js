'use strict';
import { updateCalendar } from "../calendar/calendarController.js";
import { getUser } from "../commands/commands.js";
import { fetchUsersCreditCards } from "../commands/selectionsInForms.js";
import { updateAllDisplays,displayVehicles, updateEstimation } from "../view/stationView.js";
import { getBackUrl } from "./backUrl.js";

let station = undefined;
let stationId = undefined;
const user = JSON.parse(sessionStorage.getItem("user"));

fetchStation();
const reservationDisplayObj = createReservationDisplayObj();
addActionToButton();
displayVehicles(await fetchUserCars());
updateCalendar(stationId);
fetchUsersCreditCards("#reservation-form-card", 0, user.id);

function fetchStation() {
    const urlParams = new URLSearchParams(window.location.search);
    stationId = urlParams.get('id');

    const requestOptions = {
        headers: {"Authorization": "Bearer " + user.token}
    };

    fetch(`${getBackUrl()}/search/station/${stationId}`, requestOptions)
        .then(response => response.ok ? response.json() : Promise.reject(response))
        .then(json => {
            console.log(json);
            station = json;
            updateAllDisplays(station);
        })
        .catch(response => {
            console.info(
                "Une erreur s'est produite lors de la consultation des stations :  "
                , `${response.status} (${response.statusText})`);
        }
    );
}

function validateReservation() {
    const requestOptions = {
        method : "POST",
        headers : {
            "Content-Type" : "application/json",
            "Authorization": "Bearer " + user.token
        },
        body: JSON.stringify(getReservationInfos())
    };

    console.log(requestOptions)
    fetch(`${getBackUrl()}/search/station/reservate`, requestOptions)
        .then(response => response.ok ? response.text() : Promise.reject(response))
        .then(text => {
            window.location.href= "myReservations.html";
        })
        .catch(response => {
            console.log(response);
            console.info(
                "Une erreur s'est produite lors de la création d'une réservation :  "
                , `${response.status} (${response.statusText})`);
        }
    );
}

function addActionToButton() {
    document.getElementById("reservation-form-possible-duration").addEventListener("change", () => {
        moveReservation(getReservationInfos());
        updateEstimation(station);
    });
    document.getElementById("reservation-form-day").addEventListener("change", () => moveReservation(getReservationInfos()));
    document.getElementById("reservation-form-possible-hours").addEventListener("change", () => moveReservation(getReservationInfos()));
    document.getElementById("reservation-form-possible-minutes").addEventListener("change", () => moveReservation(getReservationInfos()));
    document.getElementById("reservation-form-button-validate").addEventListener("click", validateReservation);
}

function createReservationDisplayObj() {
    const a = document.createElement("div");
    a.setAttribute("class","w-100 bg-primary");
    a.setAttribute("title","Ma réservation");
    return a;
}

function moveReservation(reservationInfos) {
    const dayNumber = new Date(reservationInfos.dateReservation).getDate() - new Date().getDate();
    const obj = document.getElementById(`j${dayNumber}`);
    if (!obj) return;

    const offset = reservationInfos.idHeureDebutReservation + reservationInfos.idMinuteDebutReservation * 0.25;
    const width = (reservationInfos.idHeureFinReservation + reservationInfos.idMinuteFinReservation * 0.25) - (reservationInfos.idHeureDebutReservation + reservationInfos.idMinuteDebutReservation * 0.25);

    reservationDisplayObj.setAttribute("style",`position: absolute; height: ${width}rem; top: ${offset}rem`)

    obj.appendChild(reservationDisplayObj)
}

function getReservationInfos() {

    const heureDebutReservation = parseFloat(document.getElementById("reservation-form-possible-hours").value, 10);
    const minuteDebutReservation = parseFloat(document.getElementById("reservation-form-possible-minutes").value, 10);
    const duree = parseInt(document.getElementById("reservation-form-possible-duration").value,10);
    const dureeCalc = (heureDebutReservation + minuteDebutReservation * 0.25) + duree * 0.25;
    const heureFinReservation = parseInt(dureeCalc,10);
    const minuteFinReservation = (dureeCalc%1) * 4;

    return {
        token: user.token,
        idVehicule: document.getElementById("reservation-form-vehicle").value,
        idStation: stationId,
        dateReservation: document.getElementById("reservation-form-day").value,
        idHeureDebutReservation: heureDebutReservation,
        idMinuteDebutReservation: minuteDebutReservation,
        idHeureFinReservation: heureFinReservation,
        idMinuteFinReservation: minuteFinReservation
    }
}

function createOption(value, text) {
    var option = document.createElement('option');
    option.text = text;
    option.value = value;
    return option;
}

var hourSelect = document.getElementById('hours');
for(var i = 8; i <= 18; i++){
    //hourSelect.add(createOption(i, i));
}

var minutesSelect = document.getElementById('minutes');
for(var i = 0; i < 60; i += 15) {
    //minutesSelect.add(createOption(i, i));
}

async function fetchUserCars() {
  const user = getUser();
  const userId = user.id;
  const userToken = user.token;

  const requestOptions = { headers: { Authorization: "Bearer " + userToken } };

  try {
    const response = await fetch(
      `${getBackUrl()}/search/vehicles/${userId}`,
      requestOptions
    );

    if (!response.ok) {
      throw new Error(`Erreur ${response.status}: ${response.statusText}`);
    }

    return response.json();

  } catch (error) {
    console.error(
      "Une erreur s'est produite lors de la récupération des véhicules :",
      error.message
    );
  }
}