// ************** Import des fonctions de la vue **************

import {
  displaySocket,
  displayDepartements,
  displayCities,
  displayRange,
  displayVehicles,
  enableRadiusRange,
  enableOrderByDistance,
  disableRadiusRange,
  disableOrderByDistance,
  displayResultsWithoutDistance,
  displayResultsWithDistance
}
  from "../view/csSearchView.js";

import { getUser } from "../commands/commands.js";

import { getBackUrl } from "./backUrl.js";

// ************** Adresse **************

const backUrl = `${getBackUrl()}/search`;

// ************** Variables globales **************

// Résultats
let vehicleArray = [];
let resultsArray = [];

// Départements et communes en variables globales
// pour éviter des fetchs multiples
let departmentsData = null;
let communesData = null;

// Critères de recherche
let socketId = null;
let dptId = null;
let cityId = null;

let range = null;

let searchAroundMe = false;
let currentLat = null;
let currentLong = null;

let onlyAvailableNow = false;

let orderByCriterium = "orderByPowerDesc"; // orderByDistanceAsc


// ************** Initialisation des affichages et des événements **************

export async function initInitialDisplayAndEventListeners() {
  // Véhicule
  // Véhicule - Set up initial
  vehicleArray = await fetchUserCars();
  displayVehicles(vehicleArray);

  // Véhicule - Evt
  const vehicleSelect = document.getElementById("vehicleSelect");
  vehicleSelect.addEventListener("change", (event) => {
    handleVehicleChange(event);
  });

  // Socket
  // Socket - setp-up initial
  console.log("vehicleSelect.value = " + vehicleSelect.value);
  const socketToDisplayObj = await fetchSocketToDisplay(vehicleSelect.value);
  displaySocket(socketToDisplayObj);

  console.table(socketToDisplayObj);
  socketId = socketToDisplayObj.socketId;
  console.log(" socketId = " + socketId);

  // Dpt
  // Dpt - Set up initial
  dptId = document.getElementById("dptSelect").value;
  displayDepartements(departmentsData);

  // Dpt - Evt
  const dptSelect = document.getElementById("dptSelect");
  dptSelect.addEventListener("change", (event) => {
    handleDptChange(event);
  });

  // City
  // City - Set up initial
  cityId = document.getElementById("citySelect").value;

  // City -  Evt
  const citySelect = document.getElementById("citySelect");
  citySelect.addEventListener("change", (event) => {
    handleCitySelect(event);
  });

  // Range de recherche
  // Range - Set up initial
  const radiusRangeChoice = document.getElementById("radiusRange");
  displayRange(radiusRangeChoice.value);
  range = radiusRangeChoice.value;

  // Range - Evt
  radiusRangeChoice.addEventListener("change", (event) => {
    handleRangeChange(event);
  });

  // ^ actuellement en d-none et non fonctionnel
  // Evt pour chercher autour de soi
  // const searchAroundMeElmt = document.getElementById("searchAroundMe");
  // searchAroundMeElmt.addEventListener("change", (event) => {
  //   handleSearchAroundMe(event);
  // });


  // Evt modification de l'ouverture de la station
  const currentlyAvailableStationsElmt = document.getElementById(
    "currentlyAvailableStations"
  );
  currentlyAvailableStationsElmt.addEventListener("change", (event) => {
    handleCurrentlyAvailable(event);
  });


  // Tri - Evt
  document.querySelectorAll('input[name="optionsOrder"]').forEach((radio) => {
    radio.addEventListener('change', (event) => {
      handleOrderChange(event)
    });
  });
}


// ************** Traitement des évènements **************

async function handleVehicleChange(event) {
  // 1. On affiche la prise correspondante
  const chosenVehicle = event.target.value;
  console.log("chosenVehicle = " + chosenVehicle);

  const socketToDisplayObj = await fetchSocketToDisplay(chosenVehicle);
  socketId = socketToDisplayObj.sockedId;

  console.table(socketToDisplayObj);
  socketId = socketToDisplayObj.socketId;
  console.log("socketId = " + socketId);

  displaySocket(socketToDisplayObj);

  research();


}

function handleRangeChange(event) {
  // 1. On affiche la range choisie
  displayRange(event.target.value);
  range = event.target.value;

  // 2. On lance la recherche
  research();
}

async function handleDptChange(event) {
  dptId = event.target.value;
  let citiesOfDptSelected = await fetchCitiesAccordingToDpt(dptId);

  // On déselectionne les villes
  cityId = null;

  // 1. On affiche les villes correspondantes
  displayCities(citiesOfDptSelected);

  // 2. On lance la recherche et on affiche les résultats
  research();


}

function handleCitySelect(event) {

  cityId = event.target.value;

  // 1. On permet / empêche la sélection d'un rayon de recherche et d'un tri par distance
  // Ce n'est permis que si une ville spécifique est sélectionnée
  if (cityId != "allCities") {
    enableRadiusRange();
    enableOrderByDistance();
  } else {
    disableRadiusRange();
    disableOrderByDistance();
  }

  // 2. On lance la recherche
  research();


}

function handleCurrentlyAvailable(event) {
  onlyAvailableNow = event.target.checked;
  console.log("onlyAvailableNow = " + onlyAvailableNow);
  research();
}

function handleOrderChange(event) {
  orderByCriterium = event.target.value;
  research();
}

// ************** Research hub **************

async function research() {

  console.log("cityId = " + cityId);

  if (cityId != "allCities" && cityId != "" && cityId != null) {

    console.log("Je cherche en fonction de la ville")

    console.log("socketId = " + socketId +
      "\ncityId = " + cityId +
      "\nrange = " + range +
      "\norderByCriterium = " + orderByCriterium
    )

    resultsArray = await fetchCSBySocketAndCityAndRangeAndOrder(
      socketId, cityId, range, orderByCriterium, onlyAvailableNow);

    // Display Results
    displayResultsWithDistance(resultsArray);

  } else if (dptId != "default") {
    console.log("Je cherche en fonction du département")
    resultsArray = await fetchCSBySocketAndDpt(socketId, dptId, onlyAvailableNow);

    // Display Results
    displayResultsWithoutDistance(resultsArray);

  }


}



// ************** Fetch **************

// Fonction pour charger les fichiers JSON une seule fois
async function loadData() {
  if (!departmentsData) {
    const response = await fetch("./entity/departements-france.json");
    if (!response.ok) throw new Error("Erreur lors de la récupération des départements.");
    departmentsData = await response.json();
  }

  if (!communesData) {
    const response = await fetch("./entity/communes-france.json");
    if (!response.ok) throw new Error("Erreur lors de la récupération des communes.");
    communesData = await response.json();
  }
}

// Renvoie les véhicules de l'utilisateur connecté
async function fetchUserCars() {
  const user = getUser();
  const userId = user.id;
  const userToken = user.token;

  console.log("fetchUserCars");
  console.log("user = " + user);
  console.log("userId = " + userId);
  console.log("userToken = " + userToken);

  const requestOptions = { headers: { Authorization: "Bearer " + userToken } };

  try {
    const response = await fetch(
      `${backUrl}/vehicles/${userId}`,
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

// Renvoie un tableau de communes qui appartiennent au département choisi
function fetchCitiesAccordingToDpt(chosenDpt) {
  const departmentCode = chosenDpt.toString().padStart(2, "0");

  return communesData.filter(city => {
    // On convertit en string et on force 5 chiffres
    const postalCode = String(city.Code_postal).padStart(5, "0");
    return postalCode.startsWith(departmentCode);
  });
}

// Renvoie la prise associée au véhicule choisi
function fetchSocketToDisplay(vehicleId) {

  if (vehicleId === "noVehicle") {
    return "";
  }

  const vehicle = vehicleArray.filter(vehicle => vehicle.vehicleId == vehicleId);
  // console.table(vehicle);
  // console.log(vehicle[0].socket);
  return vehicle[0].socket;
}

// Renvoie le résultat de la recherche = prise + dpt
async function fetchCSBySocketAndDpt(socketId, dptSelected, onlyAvailableNow) {

  const user = getUser();
  const userToken = user.token;

  const requestOptions = { headers: { Authorization: "Bearer " + userToken } };

  try {
    const response = await fetch(
      `${backUrl}/cs/${socketId}/${dptSelected}/onlyAvailableNow=${onlyAvailableNow}`,
      requestOptions
    );

    if (response.status === 404) {
      console.warn(`Aucune station trouvée pour une prise : ${socketId} et le département : ${dptSelected}`);
      return [];
    }

    if (!response.ok) {
      throw new Error(`Erreur ${response.status}: ${response.statusText}`);
    }

    let stations = await response.json();

    stations = stations.map(station => {
      const departement = findDptFromZipCode(station.postalCode);

      return {
        ...station,
        departement,

      };
    });

    console.table(stations);

    return stations;

  } catch (error) {
    console.error(
      "Une erreur s'est produite lors de la recherche des stations de recherge : ",
      error.message
    );
  }

}

async function fetchCSBySocketAndCityAndRangeAndOrder(
  socketId, cityId, range, orderByCriterium, onlyAvailableNow) {

  // TODO USER à sortir en variable globale pour être utilisé par:
  // fetchCSBySocketAndDpt et fetchCSBySocketAndCityAndRangeAndOrder
  const user = getUser();
  const userToken = user.token;

  const requestOptions = { headers: { Authorization: "Bearer " + userToken } };

  try {
    const response =
      await fetch(
  
        `${backUrl}/cs/${socketId}/${cityId}/${range}/${orderByCriterium}/onlyAvailableNow=${onlyAvailableNow}`,
        requestOptions
      );

    if (response.status === 404) {
      console.warn(`Aucune station trouvée pour une prise : ${socketId} et la ville : ${cityId}`);
      return [];
    }

    if (!response.ok) {
      throw new Error(`Erreur ${response.status}: ${response.statusText}`);
    }

    let stations = await response.json();

    stations = stations.map(station => {
      const departement = findDptFromZipCode(station.postalCode);

      return {
        ...station,
        departement,

      };
    });

    console.table(stations);

    return stations;

  } catch (error) {
    console.error(
      "Une erreur s'est produite lors de la recherche des stations de recherge : ",
      error.message
    );
  }

}


// Renvoie le département d'un code postal
function findDptFromZipCode(zipCode) {
  if (!departmentsData) return null;

  // Conversion en string + forçage à 2 caractères
  const dptCode = String(zipCode).padStart(5, "0").substring(0, 2);

  const dpt = departmentsData.find(d => d.code === dptCode);
  return dpt ? dpt.dep_name : null; // Utiliser d.dep_name au lieu de d.name
}


// ! A supp - Renvoie la commune d'un code postal
function findCityFromZipCode(zipCode) {
  if (!communesData) return null;
  const city = communesData.find(c => String(c.Code_postal) === String(zipCode));
  return city ? city.Nom_commune : null;
}



// ************** Mise en place des listeners (quand le DOM est chargé) **************
document.addEventListener("DOMContentLoaded", async () => {
  console.log("Page chargée - Initialisation des événements");

  await loadData();

  console.log("departmentsData", departmentsData);
  console.log("communesData", communesData);

  initInitialDisplayAndEventListeners();
});


// ************** Redirection si l'utilisateur n'est pas connecté **************
redirect();

// Fonction de redirection vers la page de connexion si l'utilisateur n'est pas connecté
function redirect() {
  const user = getUser();
  if (!user) {
    window.location.href = "connection.html";
  }
}
