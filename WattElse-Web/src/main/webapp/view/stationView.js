export function updateAllDisplays(station) {
    updateMap(station);
    updateInfos(station);
	updateEstimation(station);
}

export function updateMap(station) {
    document.getElementById("map-address").innerText = station.addressDisplay;
    const mapImage = document.getElementById("map");

    const zoomLevel = 0.001;
    const boundingbox = [
        station.latitude + zoomLevel,
        station.latitude - zoomLevel,
        station.longitude + zoomLevel,
        station.longitude - zoomLevel
    ]
    mapImage.setAttribute("src",`https://www.openstreetmap.org/export/embed.html?bbox=${boundingbox[2]}%2C${boundingbox[0]}%2C${boundingbox[3]}%2C${boundingbox[1]}&amp;layer=mapnik`);
}

export function updateInfos(station) {
    //document.getElementById("station-infos-power").innerText = `${}`;
    document.getElementById("station-infos-power").innerHTML = `<b>Prise:</b> ${station.socket.socketLabel} - ${station.power.value}kW/h`;
    document.getElementById("station-infos-owner").innerHTML = `<b>Propriétaire:</b> ${station.owner.fullName} ( <i class="fa-solid fa-phone"></i> ${station.owner.phone}) - <i>Inscrit depuis le ${station.owner.inscriptionDate}</i>`;
    document.getElementById("station-infos-tarification").innerHTML = `<b>Tarification:</b> ${station.tarification.cost}€${station.tarification.typeTarification.abbreviationTypeTarification}`;
}

export function displayVehicles(vehicles) {
  const vehicleSelect = document.getElementById("reservation-form-vehicle");

  vehicleSelect.innerHTML = "";

  if (vehicles == null || vehicles.length === 0) {
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

export function updateEstimation(station) {

    const duree = parseFloat(document.getElementById("reservation-form-possible-duration").value,10)/4;
	let estimation = undefined;
	switch (station.tarification.typeTarification.typeTarificationId) {
		case 0: //par heure
			estimation = station.tarification.cost * duree
			break;
		case 1: //par kW
			estimation = station.tarification.cost * station.power.value * duree;
			break;
	
		default:
			break;
	}
    document.getElementById("reservation-estimation").innerText = `${estimation}€`
}