export function createSearchResult(resultJson) {

    const optionList = document.getElementById("address-list");

    const resultOption = document.createElement("button");
    resultOption.setAttribute("type","button");
    resultOption.setAttribute("class","btn-secondary");
    resultOption.setAttribute("value",resultJson.place_id);
    resultOption.innerText = resultJson.display_name;
    optionList.appendChild(resultOption);

    return resultOption;
}

export function showMap(map) {
    document.getElementById("map-confirmation-button").setAttribute("class","btn-dark");
    const mapImage = document.getElementById("map");
    
    mapImage.setAttribute("src",`https://www.openstreetmap.org/export/embed.html?bbox=${map.boundingbox[2]}%2C${map.boundingbox[0]}%2C${map.boundingbox[3]}%2C${map.boundingbox[1]}&amp;layer=mapnik`)
    mapImage.setAttribute("class","w-100");
    //src="https://www.openstreetmap.org/export/embed.html?bbox=2.530208230018616%2C48.95467427998106%2C2.5327187776565556%2C48.95581558172538&amp;layer=mapnik
}

export function hideMap() {
    document.getElementById("map").setAttribute("class","d-none");
    document.getElementById("map-confirmation-button").setAttribute("class","d-none");
}