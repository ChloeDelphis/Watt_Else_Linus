// Manips DOM

export function displayDepartements(dpts) {
  console.table(dpts);

  const dptSelect = document.getElementById("dptSelect");

  // Transformation du tableau en Map
  const dptMap = new Map(
    dpts
      // Exclusion des DOM-TOM
      .filter(dpt => !["971", "972", "973", "974", "976"].includes(dpt.code))
      .map(dpt => [dpt.code, dpt.dep_name])
  );

  // Ajout des options à partir de la Map
  dptMap.forEach((dep_name, code) => {
    dptSelect.appendChild(new Option(dep_name, code));
  });

}

// Retire l'attribut disabled du select des villes
// Affiche les villes du département délectionné
export function displayCities(cities) {

  console.table(cities);

  const citySelect = document.getElementById("citySelect");
  citySelect.removeAttribute("disabled");

  citySelect.innerHTML = "";

  let allDptsOption = document.createElement("option");
  allDptsOption.innerHTML = "Toutes les villes du département";
  allDptsOption.value = "allCities";
  allDptsOption.setAttribute('selected', true);
  citySelect.appendChild(allDptsOption);


  // Ajout des options
  cities.forEach((city) => {
    const option = document.createElement("option");
    option.innerHTML = city.Nom_commune;

    option.value = city.Code_postal + "-"
      + city.Nom_commune + "-"
      + makeCoordonneesGood(city.coordonnees_gps);

    citySelect.appendChild(option);
  });

}

function makeCoordonneesGood(coordonnees) {
  const splitArray = coordonnees.split(",");
  const result = splitArray[0] + "-" + splitArray[1].trim();
  return result;
}

// Affiche la valeur de la range choisise
export function displayRange(value) {
  const radiusRangeElmt = document.getElementById("number-of-km");
  radiusRangeElmt.innerHTML = value;
}

// Affiche les véhicules
export function displayVehicles(vehicles) {

  console.table(vehicles);

  const vehicleSelect = document.getElementById("vehicleSelect");

  vehicleSelect.innerHTML = "";

  if (vehicles.length === 0) {
    const option = document.createElement("option");
    option.setAttribute("disabled", true);
    option.setAttribute("selected", true);
    option.innerHTML = "Pas encore de véhicule enregistré";
    option.value = "noVehicle";
    vehicleSelect.appendChild(option);
  } else {
    vehicles.forEach((vehicle, index) => {
      const option = document.createElement("option");
      option.innerHTML = vehicle.vehicleName;
      option.value = vehicle.vehicleId;

      if (index === 0) {
        option.setAttribute("selected", true); // Sélectionne le premier véhicule
      }

      vehicleSelect.appendChild(option);
    });
  }
}


// Affiche la prise associée au véhicule choisi
export function displaySocket(socketObj) {
  const socketElmt = document.getElementById("socket");
  socketElmt.value = socketObj.socketLabel;
  socketElmt.dataset.socketId = socketObj.socketId;
}

// Permet la sélection d'un rayon de recherche
export function enableRadiusRange() {
  const radiusRange = document.getElementById("radiusRange");
  radiusRange.removeAttribute("disabled");

  const radiusRangeElmts = document.getElementById("range");
  radiusRangeElmts.classList.remove("d-none")

}

// Permet la sélection d'un tri par distance
export function enableOrderByDistance() {
  const orderByDistance = document.getElementById("orderByDistanceAsc");
  orderByDistance.removeAttribute("disabled");

  const orderByDistanceElmts = document.getElementById("OrderByDistance");
  orderByDistanceElmts.classList.remove("d-none")

}

// Empêche la sélection d'un rayon de recherche
export function disableRadiusRange() {
  const radiusRange = document.getElementById("radiusRange");
  radiusRange.setAttribute("disabled", true);

  const radiusRangeElmts = document.getElementById("range");
  radiusRangeElmts.classList.add("d-none")

}

// Empêche la sélection d'un tri par distance
export function disableOrderByDistance() {
  const orderByDistance = document.getElementById("orderByDistanceAsc");
  orderByDistance.setAttribute("disabled", true);

  const orderByDistanceElmts = document.getElementById("OrderByDistance");
  orderByDistanceElmts.classList.add("d-none")

}

// Affiche les résultats de la recherche sans la distance
export function displayResultsWithoutDistance(resultsArray) {

  let numberOfResults = resultsArray.length;
  console.log("numberOfResults = " + numberOfResults);

  // Affiche le nombre de résultats
  displayNumberOfResults(numberOfResults);

  const resultsElmt = document.getElementById("results-stations");
  resultsElmt.innerHTML = "";

  if (numberOfResults > 0) {

    resultsArray.forEach((result) => {
      // Création de l'élément principal
      const resultDiv = document.createElement("div");
      resultDiv.classList.add("result", "mb-3");

      // Création de la div "info"
      const infoDiv = document.createElement("div");
      infoDiv.classList.add("info");

      // Localisation
      const locationDiv = document.createElement("div");
      locationDiv.classList.add("location");
      locationDiv.innerHTML = `
          <span class="location-city">${result.city},</span>
          <span class="location-dpt">${result.departement}</span>
      `;

      // Puissance
      const powerDiv = document.createElement("div");
      powerDiv.classList.add("power");
      powerDiv.innerHTML = `Puissance : <span class="power-number">${result.powerValue}</span> kW`;

      // Prix
      const priceDiv = document.createElement("div");
      priceDiv.classList.add("price");
      priceDiv.innerHTML = `
          <span class="price-number">${result.tarificationCost}€</span>
          <span class="price-tarification">${result.typeTarification.abbreviationTypeTarification}</span>
      `;


      // Disponibilité
      const availabilityDiv = document.createElement("div");
      availabilityDiv.classList.add("availability");
      availabilityDiv.innerHTML = `
          <i class="fa-solid fa-circle ${result.availableNow ? 'available' : 'unavailable'} me-1"></i>
          <span class="availability-info">${result.availableNow ? "Disponible maintenant !" : "Indisponible"}</span>
      `;

      // // Distance
      // const distanceDiv = document.createElement("div");
      // distanceDiv.classList.add("distance");
      // distanceDiv.innerHTML = `
      //     <i class="fa-solid fa-map-marker-alt me-1"></i>À
      //     <span class="distance-info">${result.distance}</span>km
      // `;

      // Ajout des éléments à la div "info"
      infoDiv.appendChild(locationDiv);
      infoDiv.appendChild(powerDiv);
      infoDiv.appendChild(priceDiv);
      infoDiv.appendChild(availabilityDiv);
      // infoDiv.appendChild(distanceDiv);

      // Ajout des sections à l'élément principal
      resultDiv.appendChild(infoDiv);

      // Note
      if (result.averageGradeForStation != 0) {
        const gradeDiv = document.createElement("div");
        gradeDiv.classList.add("grade");
        gradeDiv.innerHTML = `
            <i class="fa-solid fa-star"></i>
            <span class="grade-number">${result.averageGradeForStation}</span>`;
        // Ajout à l'élément principal
        resultDiv.appendChild(gradeDiv);
      }


      // Ajout du résultat au conteneur principal
      resultsElmt.appendChild(resultDiv);


      // Lien vers le détail
      resultDiv.addEventListener("click", () => {
        window.location.href = `station.html?id=${result.stationId}`;
      });



    });

  }


}

// Affiche les résultats de la recherche AVEC la distance
export function displayResultsWithDistance(resultsArray) {

  let numberOfResults = resultsArray.length;
  console.log("numberOfResults = " + numberOfResults);

  // Affiche le nombre de résultats
  displayNumberOfResults(numberOfResults);

  const resultsElmt = document.getElementById("results-stations");
  resultsElmt.innerHTML = "";

  if (numberOfResults > 0) {

    resultsArray.forEach((result) => {
      // Création de l'élément principal
      const resultDiv = document.createElement("div");
      resultDiv.classList.add("result", "mb-3");

      // Création de la div "info"
      const infoDiv = document.createElement("div");
      infoDiv.classList.add("info");

      // Localisation
      const locationDiv = document.createElement("div");
      locationDiv.classList.add("location");
      locationDiv.innerHTML = `
          <span class="location-city">${result.city},</span>
          <span class="location-dpt">${result.departement}</span>
      `;

      // Puissance
      const powerDiv = document.createElement("div");
      powerDiv.classList.add("power");
      powerDiv.innerHTML = `Puissance : <span class="power-number">${result.powerValue}</span> kW`;

      // Prix
      const priceDiv = document.createElement("div");
      priceDiv.classList.add("price");
      priceDiv.innerHTML = `
          <span class="price-number">${result.tarificationCost}€</span>
          <span class="price-tarification">${result.typeTarification.abbreviationTypeTarification}</span>
      `;


      // Disponibilité
      const availabilityDiv = document.createElement("div");
      availabilityDiv.classList.add("availability");
      availabilityDiv.innerHTML = `
          <i class="fa-solid fa-circle ${result.availableNow ? 'available' : 'unavailable'} me-1"></i>
          <span class="availability-info">${result.availableNow ? "Disponible maintenant !" : "Indisponible"}</span>
      `;

      // Distance
      const distanceDiv = document.createElement("div");
      distanceDiv.classList.add("distance");
      distanceDiv.innerHTML = `
          <i class="fa-solid fa-map-marker-alt me-1"></i>À
          <span class="distance-info">${result.distanceFromSearchingPoint}</span>km
      `;

      // Ajout des éléments à la div "info"
      infoDiv.appendChild(locationDiv);
      infoDiv.appendChild(powerDiv);
      infoDiv.appendChild(priceDiv);
      infoDiv.appendChild(availabilityDiv);
      infoDiv.appendChild(distanceDiv);

      // Ajout des sections à l'élément principal
      resultDiv.appendChild(infoDiv);

      // Note
      if (result.averageGradeForStation != 0) {
        const gradeDiv = document.createElement("div");
        gradeDiv.classList.add("grade");
        gradeDiv.innerHTML = `
            <i class="fa-solid fa-star"></i>
            <span class="grade-number">${result.averageGradeForStation}</span>`;
        // Ajout à l'élément principal
        resultDiv.appendChild(gradeDiv);
      }


      // Ajout du résultat au conteneur principal
      resultsElmt.appendChild(resultDiv);


      // Lien vers le détail
      resultDiv.addEventListener("click", () => {
        window.location.href = `station.html?id=${result.stationId}`;
      });



    });

  }


}


// Afficher le nombre de résultats
function displayNumberOfResults(numberOfResults) {
  const numberOfResultsElmt = document.getElementById("results-number");

  numberOfResultsElmt.innerHTML = `${numberOfResults} ${numberOfResults > 1 ? "résultats" : "résultat"}`;
}













