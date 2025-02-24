export function createStationButton(station) {
    
    const root = document.createElement("div");
    root.setAttribute("class","result row mt-2 w-100 mx-5");
    const splitted = station.display_address.split(", ");
    root.innerHTML = `<div class="info col-12">
        <div class="identifier">
            <span class="nameCS">${station.name}</span>
        </div>
        <div class="location">
            <span class="location-city">${splitted.at(-6)},</span>
            <span class="location-dpt">${splitted[4]}</span>
        </div>
        <div class="power">
            Puissance : <span class="power-number">${station.power.value}</span> kW
        </div>
        <div class="price">
            <span class="price-number">${`${station.tarification.cost}€${station.tarification.typeTarification.abbreviationTypeTarification}`}</span>
        </div>
    </div>`;
    document.getElementById("results").appendChild(root);
    return root;
}
/*
<div class="result row mt-2 w-100 mx-5">
    <div class="info col-12">
        <div class="identifier">
            <span class="nameCS">Ma jolie station</span>
        </div>
        <div class="location">
            <span class="location-city">Ville,</span>
            <span class="location-dpt">Département</span>
        </div>
        <div class="power">
            Puissance : <span class="power-number">11</span> kW
        </div>
        <div class="price">
            <span class="price-number">11€</span>
            <span class="price-tarification">/heure</span>
        </div>
    </div>
</div>
*/