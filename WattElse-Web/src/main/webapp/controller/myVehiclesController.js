'use strict';

import { getBackUrl } from "../commands/backUrl.js";
import { fetchSockets } from "../commands/selectionsInForms.js";

const backUrl = `${getBackUrl()}/space`;

let user = JSON.parse(sessionStorage.getItem("user"));

const requestOptions = {headers: { "Authorization": "Bearer " + user.token }}

function addActionToAddVehicleButton() {
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

addActionToAjouterButton() 

function addModVehicle() {
    const vehicleId = this.form.vehicleIdInput.value=="null"?null:this.form.vehicleIdInput.value;
    const vehicleName = this.form.vehicleNameInput.value;
    const vehicleLicensePlate = this.form.vehicleLicensePlateInput.value;
    const socket = JSON.parse(this.form.socketTypes.value);
    const requestOptions = {
        method: "post",
        headers: {"Content-Type" : "application/json", "Authorization": "Bearer " + user.token},
        body: JSON.stringify({
            vehicleId: vehicleId,                     
            vehicleName: vehicleName,                     
            licensePlate: vehicleLicensePlate,
            socket: socket,
            userId: user.id
        })
    };
    fetch(`${backUrl}/addmodvehicle`, requestOptions)
        .then(response => response.ok ? response.text() : Promise.reject(response))
        .then(text => {
            console.log(text);
            const addVehicleForm = document.getElementById("addVehicleFormRow");
            addVehicleForm.setAttribute("class", "row d-none");
            window.location.reload();
        }
    )
        .catch(response => {
            console.error(
            "une erreur s'est produite lors de l'enregistrement du véhicule " + vehicleName,
            `${response.status} - ${response.statusText}`)
            }
        );
}

function fetchMyVehicles(){
    fetch(`${backUrl}/user/${user.id}/vehicles`, requestOptions)
    .then(response => response.ok ? response.json() : Promise.reject(response))
    .then(textJson => {
        const results = document.getElementById("results");   
        for (let vehicle of textJson) {
            const result = document.createElement("div");
            result.setAttribute("class","result");
            result.setAttribute("id",`result${vehicle.vehicleId}`);
            results.appendChild(result);

            const divInfo = document.createElement("div");
            divInfo.setAttribute("class","info");
            result.appendChild(divInfo);

            const divIdentifier = document.createElement("div");
            divIdentifier.setAttribute("class","vehicleName");
            divInfo.appendChild(divIdentifier);
            const spanNameV = document.createElement("span");
            spanNameV.setAttribute("class","nameV");
            spanNameV.textContent = vehicle.vehicleName;
            divIdentifier.appendChild(spanNameV);

            const divImmat = document.createElement("div");
            divImmat.setAttribute("class","immat");
            divInfo.appendChild(divImmat);
            const spanImmat = document.createElement("span");
            spanImmat.setAttribute("class","immat-vehicle");
            spanImmat.textContent = vehicle.licensePlate;
            divImmat.appendChild(spanImmat);

            const divSocket = document.createElement("div");
            divSocket.setAttribute("class","socket");
            divInfo.appendChild(divSocket);
            const spanSocket = document.createElement("span");
            spanSocket.setAttribute("class","socket-label");
            spanSocket.textContent = vehicle.socket.socketLabel;
            divSocket.appendChild(spanSocket);

            const divButtons = document.createElement("div");
            divButtons.setAttribute("class","buttonsCS align-items-center");
            divInfo.appendChild(divButtons);
            const inputButtonMod = document.createElement("input");
            inputButtonMod.setAttribute("id","modVehicle");
            inputButtonMod.setAttribute("class","btn btn-primary");
            inputButtonMod.setAttribute("value","Modifier");
            inputButtonMod.addEventListener("click", () => modVehcile(vehicle));
            divButtons.appendChild(inputButtonMod);
            const inputButtonSupp = document.createElement("input");
            inputButtonSupp.setAttribute("id","suppVehicle");
            inputButtonSupp.setAttribute("class","btn btn-primary");
            inputButtonSupp.setAttribute("value","Supprimer");
            inputButtonSupp.addEventListener("click", () => suppVehcile(vehicle.vehicleId));
            divButtons.appendChild(inputButtonSupp);
        }
    })
    .catch(response => {            
            console.log(`Erreur ${response.status}`);
    });
}

fetchMyVehicles()

function suppVehcile(vehicleId){
    const requestOptions = {
        method: "post",
        headers: {"Authorization": "Bearer " + user.token}
    };
    fetch(`${backUrl}/suppvehicle/${vehicleId}`, requestOptions)
    .then(response => response.ok ? response.text() : Promise.reject(response))
    .then(text => {
        console.log(text);
        window.location.reload();
    })
    .catch(response => {
        console.error(
        "une erreur s'est produite lors de la suprresion du vehicule id : " + vehicleId,
        `${response.status} - ${response.statusText}`)
        }
    );
}

function modVehcile(vehicle){
    const result = document.getElementById(`result${vehicle.vehicleId}`);
    while (result.firstChild) {
        result.removeChild(result.lastChild);
    }
    const divInfo = document.createElement("form");
    divInfo.setAttribute("class","info");
    result.appendChild(divInfo);

    const divIdVehicle = document.createElement("div");
    divIdVehicle.setAttribute("class","d-none");
    divInfo.appendChild(divIdVehicle);
    const inputIdVehicle = document.createElement("input");
    inputIdVehicle.setAttribute("class","form-control");
    inputIdVehicle.setAttribute("id","vehicleIdInput");
    inputIdVehicle.setAttribute("type","text");
    inputIdVehicle.setAttribute("value",`${vehicle.vehicleId}`);
    divIdVehicle.appendChild(inputIdVehicle);

    const divIdentifier = document.createElement("div");
    divIdentifier.setAttribute("class","vehicleName");
    divInfo.appendChild(divIdentifier);
    const inputNameV = document.createElement("input");
    inputNameV.setAttribute("class","form-control rounded-pill");
    inputNameV.setAttribute("id","vehicleNameInput");
    inputNameV.setAttribute("type","text");
    inputNameV.value = `${vehicle.vehicleName}`;
    divIdentifier.appendChild(inputNameV);

    const divImmat = document.createElement("div");
    divImmat.setAttribute("class","immat");
    divInfo.appendChild(divImmat);
    const spanImmat = document.createElement("span");
    spanImmat.setAttribute("class","immat-vehicle");
    spanImmat.textContent = vehicle.licensePlate;
    divImmat.appendChild(spanImmat);
    const inputImmat = document.createElement("input");
    inputImmat.setAttribute("class","form-control d-none");
    inputImmat.setAttribute("id","vehicleLicensePlateInput");
    inputImmat.setAttribute("type","text");
    inputImmat.setAttribute("value", `${vehicle.licensePlate}`);
    console.log(`${vehicle.vehicleLicensePlate}`)
    divImmat.appendChild(inputImmat);

    const divSocket = document.createElement("div");
    divSocket.setAttribute("class","socket");
    divInfo.appendChild(divSocket);
    const selectSocket = document.createElement("select");
    selectSocket.setAttribute("class","form-select rounded-pill socketTypeClassMod");
    selectSocket.setAttribute("id","socketTypes");
    selectSocket.value = `${vehicle.socket.socketLabel}`;
    fetchSockets(".socketTypeClassMod");
    divSocket.appendChild(selectSocket);

    const divButtons = document.createElement("div");
    divButtons.setAttribute("class","buttonsCS align-items-center");
    divInfo.appendChild(divButtons);
    const inputButtonMod = document.createElement("input");
    inputButtonMod.setAttribute("id","modVehicle");
    inputButtonMod.setAttribute("class","btn btn-primary");
    inputButtonMod.setAttribute("value","Modifier");
    inputButtonMod.addEventListener("click", addModVehicle);
    divButtons.appendChild(inputButtonMod);

    const inputButtonSupp = document.createElement("input");
    inputButtonSupp.setAttribute("id","suppVehicle");
    inputButtonSupp.setAttribute("class","btn btn-primary");
    inputButtonSupp.setAttribute("value","annuler");
    inputButtonSupp.addEventListener("click", () => window.location.reload());
    divButtons.appendChild(inputButtonSupp);
}