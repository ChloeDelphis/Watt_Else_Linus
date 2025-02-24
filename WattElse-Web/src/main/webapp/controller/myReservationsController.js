'use strict';

import { getBackUrl } from "../commands/backUrl.js";
import { fetchSockets } from "../commands/selectionsInForms.js";
import { getFullTimeReservation } from "../commands/commandsView.js";
import { addressDisplay } from "../commands/commandsView.js";
import { formatDateTime } from "../commands/commandsView.js";
import { fetchReservationHours } from "../commands/selectionsInForms.js";
import { fetchReservationMinutes } from "../commands/selectionsInForms.js";
import { fetchUsersCreditCards } from "../commands/selectionsInForms.js";
import { fetchUsersVehicles } from "../commands/selectionsInForms.js";
import { fetchRatingType } from "../commands/selectionsInForms.js";
import { ifNotNull } from "../commands/commands.js";


const backUrl = `${getBackUrl()}/space`;

let user = JSON.parse(sessionStorage.getItem("user"));

const requestOptions = {headers: { "Authorization": "Bearer " + user.token },
cache: "no-store"}

const dateOptions = {day: 'numeric', month: 'long', year: 'numeric'};

/*function addActionToAddVehicleButton() {
    const addVehicleButton = document.getElementById("addVehiclesButton");
    addVehicleButton.addEventListener("click", displayAddVehicle);
}

addActionToAddVehicleButton() 

function displayAddVehicle() {
    const addVehicleForm = document.getElementById("addVehicleFormRow");
    addVehicleForm.setAttribute("class", "row");
}

fetchSockets(".socketTypeClassAdd");

function addActionToAjouterButton() {
    const addVehicleButton = document.getElementById("addVehicleButton");
    addVehicleButton.addEventListener("click", addModVehicle);
}

addActionToAjouterButton()*/ 


function cancelModResa(reservation) {
    console.log(JSON.stringify(reservation));
   
    
    const getFormValues = (formElement) => {
        return {
            idVehicule: JSON.parse(formElement.querySelector("#reservationVehicle").value).vehicleId,
            dateReservation: formElement.querySelector("#reservationDate").value,
            startReservationHours: JSON.parse(formElement.querySelector("#startReservationHours").value),
            startReservationMinutes: JSON.parse(formElement.querySelector("#startReservationMinutes").value),
            endReservationHours: JSON.parse(formElement.querySelector("#endReservationHours").value),
            endReservationMinutes: JSON.parse(formElement.querySelector("#endReservationMinutes").value),
            idCreditCard: JSON.parse(formElement.querySelector("#reservationCard").value).idCreditCard
        };
    };

    let formElement = null;

    if (reservation.dateAnormalEnding == null) {
        formElement = document.getElementById(`result${reservation.idReservation}`).querySelector("form");
    }

    const {
        idVehicule,
        dateReservation,
        startReservationHours,
        startReservationMinutes,
        endReservationHours,
        endReservationMinutes,
        idCreditCard
    } = formElement ? getFormValues(formElement) : {
        idVehicule: reservation.vehicleDto.vehicleId,
        dateReservation: reservation.dateReservation,
        startReservationHours: reservation.startReservationHours,
        startReservationMinutes: reservation.startReservationMinutes,
        endReservationHours: reservation.endReservationHours,
        endReservationMinutes: reservation.endReservationMinutes,
        idCreditCard: reservation.creditCardDto.idCreditCard
    };

    const ratingId = (reservation.rating !== null && reservation.rating.ratingId) 
    ? reservation.rating.ratingId 
    : null;

    const requestOptions = {
        method: "post",
        headers: {"Content-Type" : "application/json", "Authorization": "Bearer " + user.token},
        body: JSON.stringify({
            idReservation: reservation.idReservation,
            idVehicule: idVehicule,
            idStation: reservation.station.stationId,
            dateReservation: dateReservation,
            startReservationHours: startReservationHours,
            startReservationMinutes: startReservationMinutes,
            endReservationHours: endReservationHours,
            endReservationMinutes: endReservationMinutes,
            idFacturation: reservation.facturation.idMonthlyFacturation,
            reservationRegisterDate: reservation.reservationRegisterDate,
            idCreditcard: idCreditCard,
            energyConsumed: ifNotNull(reservation.energyConsumed),
            cost: ifNotNull(reservation.cost),
            idRating: ratingId,
            dateDebutRecharge: ifNotNull(reservation.dateDebutRecharge),
            dateFinRecharge: ifNotNull(reservation.dateFinRecharge),
            dateDriverFacturation: ifNotNull(reservation.dateDriverFacturation),
            dateAnormalEnding: reservation.dateAnormalEnding,
            reservationAnormalEnding: ifNotNull(reservation.reservationAnormalEnding)
        })
    };



    console.log(requestOptions);

    fetch(`${backUrl}/user/updatereservations`, requestOptions)
        .then(response => response.ok ? response.text() : Promise.reject(response))
        .then(text => {
            console.log(text);
            fetchMyReservations();
            window.location.reload();
        }
    )
    .catch(response => {
        console.error(
        "une erreur s'est produite lors de la modification de la reservation avec l'ID " + reservation.idReservation,
        `${response.status} - ${response.statusText}`)
        }
    );
}

function fetchMyReservations(){
    fetch(`${backUrl}/user/${user.id}/reservations?t=${new Date().getTime()}`, requestOptions)
    .then(response => response.ok ? response.json() : Promise.reject(response))
    .then(textJson => {
        const results = document.getElementById("results"); 
        for (let reservation of textJson) {

            const result = document.createElement("div");
            result.setAttribute("class","result-resa");
            result.setAttribute("id",`result${reservation.idReservation}`);
            results.appendChild(result);

            const divInfo = document.createElement("div");
            divInfo.setAttribute("class","info");
            result.appendChild(divInfo);

            const divIdentifier = document.createElement("div");
            divIdentifier.setAttribute("class","date");
            divInfo.appendChild(divIdentifier);
            const spanReservationDate = document.createElement("span");
            spanReservationDate.setAttribute("class","reservation-date");
            spanReservationDate.textContent =
             `Réservation du ${new Date(reservation.dateReservation)
                .toLocaleDateString("fr-FR", dateOptions)}`;
            divIdentifier.appendChild(spanReservationDate);

            const divTime = document.createElement("div");
            divTime.setAttribute("class","time");
            divInfo.appendChild(divTime);
            const spanReservationTime = document.createElement("span");
            spanReservationTime.setAttribute("id","reservation-time");
            spanReservationTime.setAttribute("class","reservation-time reservation-date");
            spanReservationTime.textContent = 
            `de ${getFullTimeReservation(reservation.startReservationHours.hour,
                 reservation.startReservationMinutes.minutes)}
                 à ${getFullTimeReservation(reservation.endReservationHours.hour,
                    reservation.endReservationMinutes.minutes)}`;
            divTime.appendChild(spanReservationTime);

            const divLocation = document.createElement("div");
            divLocation.setAttribute("class","location");
            divInfo.appendChild(divLocation);
            const spanLocation = document.createElement("span");
            spanLocation.setAttribute("class","address");
            spanLocation.textContent = `${addressDisplay(reservation.station.addressDisplay)}`;
            divLocation.appendChild(spanLocation);

            const divVehicleResa = document.createElement("div");
            divVehicleResa.setAttribute("class","vehicle-resa mb-2");
            divInfo.appendChild(divVehicleResa);
            const spanVehicleResaLabel = document.createElement("span");
            spanVehicleResaLabel.setAttribute("class","reservation-stats-label");
            spanVehicleResaLabel.textContent = `Véhicule : `;
            divVehicleResa.appendChild(spanVehicleResaLabel);
            const spanVehicleResa = document.createElement("span");
            spanVehicleResa.setAttribute("class","reservation-stats-data");
            spanVehicleResa.textContent = `${reservation.vehicleDto.vehicleName}`;
            divVehicleResa.appendChild(spanVehicleResa);

            const divCardResa = document.createElement("div");
            divCardResa.setAttribute("class","vehicle-resa mb-2");
            divInfo.appendChild(divCardResa);
            const spanCardResaLabel = document.createElement("span");
            spanCardResaLabel.setAttribute("class","reservation-stats-label");
            spanCardResaLabel.textContent = `Carte de crédit : `;
            divCardResa.appendChild(spanCardResaLabel);
            const spanCardResaDots = document.createElement("span");
            spanCardResaDots.setAttribute("class","reservation-stats-label text-dark");
            spanCardResaDots.textContent = `****`;
            divCardResa.appendChild(spanCardResaDots);
            const spanCardResa = document.createElement("span");
            spanCardResa.setAttribute("class","reservation-stats-data");
            spanCardResa.textContent = ` ${reservation.creditCardDto.cardNumber}`;
            divCardResa.appendChild(spanCardResa);

            if (reservation.dateFinRecharge != null) {
                const divStartDateTimeLabel = document.createElement("div");
                divStartDateTimeLabel.setAttribute("class","reservation-stats");
                divInfo.appendChild(divStartDateTimeLabel);               
                const spanStartDateTimeLabel = document.createElement("span");
                spanStartDateTimeLabel.setAttribute("class","reservation-stats-label");
                spanStartDateTimeLabel.textContent = `Début de charge :`;
                divStartDateTimeLabel.appendChild(spanStartDateTimeLabel);

                const divChargeStart = document.createElement("div");
                divChargeStart.setAttribute("class","reservation-stats");
                divInfo.appendChild(divChargeStart);                
                const spanStartDateTime = document.createElement("span");
                spanStartDateTime.setAttribute("class","reservation-stats-data");
                spanStartDateTime.textContent = `${formatDateTime(reservation.dateDebutRecharge)}`;
                divChargeStart.appendChild(spanStartDateTime);

                const divEndDateTimeLabel = document.createElement("div");
                divEndDateTimeLabel.setAttribute("class","reservation-stats");
                divInfo.appendChild(divEndDateTimeLabel);               
                const spanEndDateTimeLabel = document.createElement("span");
                spanEndDateTimeLabel.setAttribute("class","reservation-stats-label");
                spanEndDateTimeLabel.textContent = `Fin de charge :`;
                divEndDateTimeLabel.appendChild(spanEndDateTimeLabel);

                const divChargeEnd = document.createElement("div");
                divChargeEnd.setAttribute("class","reservation-stats");
                divInfo.appendChild(divChargeEnd);                
                const spanEndtDateTime = document.createElement("span");
                spanEndtDateTime.setAttribute("class","reservation-stats-data");
                spanEndtDateTime.textContent = `${formatDateTime(reservation.dateFinRecharge)}`;
                divChargeEnd.appendChild(spanEndtDateTime);

                const divChargeEnergy = document.createElement("div");
                divChargeEnergy.setAttribute("class","reservation-stats");
                divInfo.appendChild(divChargeEnergy);                
                const spanChargeEnergyLabel = document.createElement("span");
                spanChargeEnergyLabel.setAttribute("class","reservation-stats-label");
                spanChargeEnergyLabel.textContent = `Energie consommée : `;
                divChargeEnergy.appendChild(spanChargeEnergyLabel);
                const spanChargeEnergy = document.createElement("span");
                spanChargeEnergy.setAttribute("class","reservation-stats-data");
                spanChargeEnergy.textContent = `${reservation.energyConsumed} kwh`;
                divChargeEnergy.appendChild(spanChargeEnergy);

                const divChargeCost = document.createElement("div");
                divChargeCost.setAttribute("class","reservation-stats mb-2");
                divInfo.appendChild(divChargeCost);                
                const spanChargeCostLabel = document.createElement("span");
                spanChargeCostLabel.setAttribute("class","reservation-stats-label");
                spanChargeCostLabel.textContent = `Cout : `;
                divChargeCost.appendChild(spanChargeCostLabel);
                const spanChargeCost = document.createElement("span");
                spanChargeCost.setAttribute("class","reservation-stats-data");
                spanChargeCost.textContent = `${reservation.facturation.amount} €`;
                divChargeCost.appendChild(spanChargeCost);

                const divRateResa = document.createElement("div");
                divRateResa.setAttribute("class", "vehicle-resa d-flex flex-column justify-content-center mb-2");
                divInfo.appendChild(divRateResa);

                const inputRatingTypeResa = document.createElement("select");
                inputRatingTypeResa.setAttribute("id", `inputRatingTypeResa${reservation.idReservation}`);
                inputRatingTypeResa.setAttribute("class", `form-select rounded-pill inputRatingTypeResa${reservation.idReservation} d-none`);
                fetchRatingType(`.inputRatingTypeResa${reservation.idReservation}`);
                divRateResa.appendChild(inputRatingTypeResa);

                const inputCommentResa = document.createElement("input");
                inputCommentResa.setAttribute("id", `inputCommentResa${reservation.idReservation}`);
                inputCommentResa.setAttribute("type", "text");
                inputCommentResa.setAttribute("class", "form-label rounded-pill inputCommentResa d-none");
                inputCommentResa.setAttribute("placeholder", "Commentaire (facultatif)");
                divRateResa.appendChild(inputCommentResa);

                const inputRateResa = document.createElement("select");
                inputRateResa.setAttribute("id", `inputRateResa${reservation.idReservation}`);
                inputRateResa.setAttribute("class", "form-control rounded-pill inputRateResa d-none");

                let i = 5; 
                while (i >= 1) {
                    const optionRateResa = document.createElement("option");
                    optionRateResa.innerHTML = i;
                    optionRateResa.value = i;
                    inputRateResa.appendChild(optionRateResa);
                    i--;
                }
                divRateResa.appendChild(inputRateResa);

                const divButtons = document.createElement("div");
                divButtons.setAttribute("class", "d-flex flex-column justify-content-center");
                divButtons.setAttribute("id", `divButtons${reservation.idReservation}`);
                divInfo.appendChild(divButtons);

                const inputButtonRate = document.createElement("input");
                inputButtonRate.setAttribute("type", "button");
                inputButtonRate.setAttribute("class", `btn btn-primary yellowButton buttonRateResa${reservation.idReservation}`);
                inputButtonRate.setAttribute("value", "Noter la station");
                inputButtonRate.setAttribute("id", `rateButton${reservation.idReservation}`);
                inputButtonRate.addEventListener("click", () => rateResa(reservation));
                divButtons.appendChild(inputButtonRate);

            } else {

                document.getElementById("reservation-time").setAttribute("class", "reservation-time");

                const divButtons = document.createElement("div");
                divButtons.setAttribute("class","d-flex flex-column justify-content-center");
                divButtons.setAttribute("id",`divButtonsActionsResa${reservation.idReservation}`);
                divInfo.appendChild(divButtons);
                const inputButtonMod = document.createElement("input");
                inputButtonMod.setAttribute("id","modResa");
                inputButtonMod.setAttribute("type","button");
                inputButtonMod.setAttribute("class","btn btn-primary greenButton");
                inputButtonMod.setAttribute("value","Modifier");
                inputButtonMod.addEventListener("click", () => updateResaButton(reservation));
                divButtons.appendChild(inputButtonMod);
                const inputButtonCancel = document.createElement("input");
                inputButtonCancel.setAttribute("id","cancelResa");
                inputButtonCancel.setAttribute("type","button");
                inputButtonCancel.setAttribute("class","btn btn-primary redButton");
                inputButtonCancel.setAttribute("value","Annuler");
                inputButtonCancel.addEventListener("click", () => {
                    reservation.dateAnormalEnding = new Date().toISOString().split('T')[0];
                    cancelModResa(reservation);
                });
                divButtons.appendChild(inputButtonCancel);
                const inputButtonStart = document.createElement("input");
                inputButtonStart.setAttribute("id", `startResa${reservation.idReservation}`);
                inputButtonStart.setAttribute("type","button");
                inputButtonStart.setAttribute("class","btn btn-primary yellowButton");
                inputButtonStart.setAttribute("value","Démarrer charge");
                inputButtonStart.addEventListener("click", () => startResa(reservation.idReservation));
                divButtons.appendChild(inputButtonStart);
            }
        }
    })
    .catch(response => {            
            console.log(`Erreur ${response.status}`);
    });
}

fetchMyReservations()


function updateResaButton(reservation){
         
    const result = document.getElementById(`result${reservation.idReservation}`);
    while (result.firstChild) {
        result.removeChild(result.lastChild);
    }
    const divInfo = document.createElement("form");
    divInfo.setAttribute("class","info");
    result.appendChild(divInfo);

    const divIdentifier = document.createElement("div");
    divIdentifier.setAttribute("class","date");
    divInfo.appendChild(divIdentifier);
    const inputReservationDate = document.createElement("input");
    inputReservationDate.setAttribute("id","reservationDate");
    inputReservationDate.setAttribute("class","form-select rounded-pill reservation-date");
    inputReservationDate.setAttribute("type","date");
    inputReservationDate.valueAsDate = new Date(reservation.dateReservation);
    divIdentifier.appendChild(inputReservationDate);

    const divTime = document.createElement("div");
    divTime.setAttribute("class","d-flex flex-row justify-content-center align-items-center time");
    divInfo.appendChild(divTime);
    const inputReservationHourStart = document.createElement("select");
    inputReservationHourStart.setAttribute("id","startReservationHours");
    inputReservationHourStart.setAttribute("class","form-select rounded-pill reservationTimeHourStart");
    fetchReservationHours(".reservationTimeHourStart", reservation.startReservationHours);
    divTime.appendChild(inputReservationHourStart);

    const inputReservationTextStart = document.createElement("span");
    inputReservationTextStart.setAttribute("class","reservation-stats-label text-center");
    inputReservationTextStart.textContent = `h`;           
    divTime.appendChild(inputReservationTextStart);

    const inputReservationMinStart = document.createElement("select");
    inputReservationMinStart.setAttribute("id","startReservationMinutes");
    inputReservationMinStart.setAttribute("class","form-select rounded-pill reservationTimeMinStart");
    fetchReservationMinutes(".reservationTimeMinStart", reservation.startReservationMinutes);           
    divTime.appendChild(inputReservationMinStart);

    const inputReservationTextMid = document.createElement("span");
    inputReservationTextMid.setAttribute("class","reservation-stats-label");
    inputReservationTextMid.textContent = `-`;           
    divTime.appendChild(inputReservationTextMid);

    const inputReservationHourEnd = document.createElement("select");
    inputReservationHourEnd.setAttribute("id","endReservationHours");
    inputReservationHourEnd.setAttribute("class","form-select rounded-pill reservationTimeHourEnd");           
    fetchReservationHours(".reservationTimeHourEnd", reservation.endReservationHours);
    divTime.appendChild(inputReservationHourEnd);

    const inputReservationTextEnd = document.createElement("span");
    inputReservationTextEnd.setAttribute("class","reservation-stats-label");
    inputReservationTextEnd.textContent = `h`;           
    divTime.appendChild(inputReservationTextEnd);

    const inputReservationMinEnd = document.createElement("select");
    inputReservationMinEnd.setAttribute("id","endReservationMinutes");
    inputReservationMinEnd.setAttribute("class","form-select rounded-pill reservationTimeMinEnd");       
    fetchReservationMinutes(".reservationTimeMinEnd", reservation.endReservationMinutes);
    divTime.appendChild(inputReservationMinEnd);

    const divLocation = document.createElement("div");
    divLocation.setAttribute("class","location");
    divInfo.appendChild(divLocation);
    const spanLocation = document.createElement("span");
    spanLocation.setAttribute("class","address");
    spanLocation.textContent = `${addressDisplay(reservation.station.addressDisplay)}`;
    divLocation.appendChild(spanLocation);

    const divVehicleResa = document.createElement("div");
    divVehicleResa.setAttribute("class","vehicle-resa mb-2");
    divInfo.appendChild(divVehicleResa);
    const spanVehicleResaLabel = document.createElement("span");
    spanVehicleResaLabel.setAttribute("class","reservation-stats-label");
    spanVehicleResaLabel.textContent = `Véhicule : `;
    divVehicleResa.appendChild(spanVehicleResaLabel);
    const inputVehicleResa = document.createElement("select");
    inputVehicleResa.setAttribute("id","reservationVehicle");
    inputVehicleResa.setAttribute("class","form-select rounded-pill reservation-Vehicle");
    fetchUsersVehicles(".reservation-Vehicle", reservation.vehicleDto, user.id);
    divVehicleResa.appendChild(inputVehicleResa);

    const divCardResa = document.createElement("div");
    divCardResa.setAttribute("class","vehicle-resa mb-2");
    divInfo.appendChild(divCardResa);
    const spanCardResaLabel = document.createElement("span");
    spanCardResaLabel.setAttribute("class","reservation-stats-label");
    spanCardResaLabel.textContent = `Carte de crédit : `;
    divCardResa.appendChild(spanCardResaLabel);
    const inputCardResa = document.createElement("select");
    inputCardResa.setAttribute("id","reservationCard");
    inputCardResa.setAttribute("class","form-select rounded-pill reservationCard");
    fetchUsersCreditCards(".reservationCard", reservation.creditCardDto, user.id);
    divCardResa.appendChild(inputCardResa);

    const divButtons = document.createElement("div");
    divButtons.setAttribute("class","d-flex flex-column justify-content-center");
    divInfo.appendChild(divButtons);
    const inputButtonMod = document.createElement("input");
    inputButtonMod.setAttribute("id","modResa");
    inputButtonMod.setAttribute("type","button");
    inputButtonMod.setAttribute("class","btn btn-primary greenButton");
    inputButtonMod.setAttribute("value","Modifier");
    inputButtonMod.addEventListener("click", () => {
        reservation.dateAnormalEnding = null;
        cancelModResa(reservation);
    });
    divButtons.appendChild(inputButtonMod);
    const inputButtonCancel = document.createElement("input");
    inputButtonCancel.setAttribute("id","cancelResa");
    inputButtonCancel.setAttribute("type","button");
    inputButtonCancel.setAttribute("class","btn btn-primary redButton");
    inputButtonCancel.setAttribute("value","Annuler");
    inputButtonCancel.addEventListener("click", () => window.location.reload());
    divButtons.appendChild(inputButtonCancel);

}

function startResa(idReservation){
    const requestOptions = {
        method: "post",
        headers: {"Authorization": "Bearer " + user.token}
    };
    fetch(`${backUrl}/reservation/${idReservation}/startcharge`, requestOptions)
    .then(response => response.ok ? response.text() : Promise.reject(response))
    .then(text => {
        console.log(text);
        const divButtonsActionsResa = document.getElementById(`divButtonsActionsResa${idReservation}`);
        while (divButtonsActionsResa.firstChild) {
            divButtonsActionsResa.removeChild(divButtonsActionsResa.lastChild);
        }
        const inputButtonEnd = document.createElement("input");
        inputButtonEnd.setAttribute("id", `endResa${idReservation}`);
        inputButtonEnd.setAttribute("type","button");
        inputButtonEnd.setAttribute("class","btn btn-primary redButton");
        inputButtonEnd.setAttribute("value","Arrêter charge");
        inputButtonEnd.addEventListener("click", () => endResa(idReservation));
        divButtonsActionsResa.appendChild(inputButtonEnd);
    })
    .catch(response => {
        console.error(
        "une erreur s'est produite lors du démrage de la charge de la résertion id : " + idReservation,
        `${response.status} - ${response.statusText}`)
        }
    );

}

function endResa(idReservation){
    const requestOptions = {
        method: "post",
        headers: {"Authorization": "Bearer " + user.token}
    };
    fetch(`${backUrl}/reservation/${idReservation}/endcharge`, requestOptions)
    .then(response => response.ok ? response.text() : Promise.reject(response))
    .then(text => {
        console.log(text);
        fetchMyReservations();
        window.location.reload();
    })
    .catch(response => {
        console.error(
        "une erreur s'est produite lors de l'arrêt de la charge de la résertion id : " + idReservation,
        `${response.status} - ${response.statusText}`)
        }
    );
}

function rateResa(reservation) {
    const inputRatingTypeResa = document.getElementById(`inputRatingTypeResa${reservation.idReservation}`)
    inputRatingTypeResa.setAttribute("class","form-select rounded-pill inputRatingTypeResa mb-2");
    const inputCommentResa = document.getElementById(`inputCommentResa${reservation.idReservation}`)
    inputCommentResa.setAttribute("class","form-label rounded-pill inputCommentResa mb-2");
    const inputRateResa = document.getElementById(`inputRateResa${reservation.idReservation}`)
    inputRateResa.setAttribute("class","form-select rounded-pill inputRateResa");
    const rateButton = document.getElementById(`rateButton${reservation.idReservation}`);
    rateButton.removeEventListener('click', () => rateResa(reservation));

    // Suppression de l'ancien écouteur d'événements pour éviter les doublons
    rateButton.replaceWith(rateButton.cloneNode(true));
    const newRateButton = document.getElementById(`rateButton${reservation.idReservation}`);

    const ratingType = JSON.parse(inputRatingTypeResa.value);

    newRateButton.addEventListener("click", () => {
        const rating = {
            ratingId: null,
            ratingType: ratingType,
            comment: inputCommentResa.value,
            rate: inputRateResa.value
        };

        const requestOptions = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${user.token}`
            },
            body: JSON.stringify(rating)
        };

        fetch(`${backUrl}/reservation/rate`, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erreur ${response.status}: ${response.statusText}`);
                }
                return response.json();
            })
            .then(data => {
                
                rating.ratingId = data;

               
                reservation.rating = rating;
                
                console.log("Nouvelle valeur de reservation :", JSON.stringify(reservation));

                
                if (!newRateButton.classList.contains('d-none')) {
                    newRateButton.classList.add('d-none');
                }

                cancelModResa(reservation);               
        

            })
            .catch(error => {
                console.error(`Erreur lors de la modification de la réservation ${reservation.idReservation}:`, error.message);
            });
    });

    // Ajout du bouton d'annulation
    const divButtons = document.getElementById(`divButtons${reservation.idReservation}`);
    if (!document.getElementById("cancelResa")) {
        const inputButtonCancel = document.createElement("input");
        inputButtonCancel.setAttribute("id", "cancelResa");
        inputButtonCancel.setAttribute("type", "button");
        inputButtonCancel.setAttribute("class", "btn btn-primary redButton");
        inputButtonCancel.setAttribute("value", "Annuler");
        inputButtonCancel.addEventListener("click", () => window.location.reload());

        divButtons.appendChild(inputButtonCancel);
    }
}
