import { getBackUrl } from "./backUrl.js";
import { getUserToken } from "./commands.js";

const backUrl = `${getBackUrl()}`;
const token = getUserToken();
const requestOptions = { headers: { "Authorization": "Bearer " + token } };


/// Fonctions à appeler dans les form-select
export function fetchSockets(classSelect, value) {
    fetchData('range/fetch/sockets', classSelect, value, processSocketData);
}
export function fetchPowerSockets(classSelect, value) {
    fetchData('range/fetch/powers', classSelect, value, processPowerData);
}
export function fetchPriceSockets(classSelect, value) {
    fetchData('range/fetch/tarifications', classSelect, value, processPriceData);
}

export function fetchReservationHours(classSelect, value) {
    fetchData('range/fetch/reservationHour', classSelect, value, processReservationHourData);
}
export function fetchReservationMinutes(classSelect, value) {
    fetchData('range/fetch/reservationMinutes', classSelect, value, processReservationMinutesData);
}
export function fetchWeekDay(classSelect, value) {
    fetchData('range/fetch/weekday', classSelect, value, processWeekDays);
}

export function fetchBanMotives(classSelect, value) {
    fetchData('range/fetch/banmotives', classSelect, value, processBanmotive);
}
export function fetchStationClosingMotive(classSelect, value) {
    fetchData('range/fetch/stationclosingmotive', classSelect, value, processStationClosingMotive );
}
export function fetchAccountClosingMotive(classSelect) {
    fetchData('range/fetch/accountclosingmotive', classSelect, value,processAccountClosingMotive);
}

export function fetchReservationAnormalEnding(classSelect, value) {
    fetchData('range/fetch/reservation/anormal/Ending', classSelect, value, processReservationAnormalEnding);
}
export function fetchRatingType(classSelect, value) {
    fetchData('range/fetch/ratingtype', classSelect, value, processRatingType );
}
export function fetchStationService(classSelect, value) {
    fetchData('range/fetch/stationservice', classSelect, value,processStationService);
}

export function fetchUsersCreditCards(classSelect, value, userId) {
    fetchData(`paymentMethods/fetch/creditCard/${userId}`, classSelect, value, processCreditCards);
}

export function fetchUsersVehicles(classSelect, value, userId) {
    fetchData(`space/user/${userId}/vehicles`, classSelect, value, processVehicles);
}

// Fonction commune à tous les fetchs
function fetchData(endpoint, classSelect, defaultValue, processData) {
    fetch(`${backUrl}/${endpoint}`, requestOptions)
        .then(response => response.ok ? response.json() : Promise.reject(response))
        .then(data => {
            const container = document.querySelector(classSelect);
            data.forEach(item => {
                const option = document.createElement("option");
                processData(option, item);
                container.appendChild(option);
            });
        })
        .then(() => {
            if (defaultValue) {
                const selectElement = document.querySelector(classSelect);
                if (defaultValue.vehicleId != null){
                    const options = selectElement.options;
                    
                    for (let option of options) {
                        const optionData = JSON.parse(option.value);
                        
                        if (optionData.vehicleId === defaultValue.vehicleId) {
                            selectElement.value = option.value;
                            console.log(option.value);
                            break;
                        }
                    }
                }else{
                    selectElement.value = JSON.stringify(defaultValue)
                }
            }
        })
        .catch(response => {
            console.log(`Erreur ${response.status}`);
        });
}


function processSocketData(option, socket) {
    option.setAttribute("value", JSON.stringify(socket));
    option.textContent = socket.socketLabel;
}

function processPowerData(option, power) {
    option.setAttribute("value", JSON.stringify(power));
    option.textContent = JSON.stringify(power.value);
}

function processPriceData(option, tarification) {
    option.setAttribute("value", JSON.stringify(tarification));
    option.textContent = tarification.labelTypeTarification;
}

function processReservationHourData(option, hours) {
    option.setAttribute("value", JSON.stringify(hours));
    option.textContent = hours.hour;
}

function processReservationMinutesData(option, minutes) {
    option.setAttribute("value", JSON.stringify(minutes));
    option.textContent = minutes.minutes;
}

function processWeekDays(option, day) {
    option.setAttribute("value", JSON.stringify(day));
    option.textContent = day.labelWeekDay;
}

function processBanmotive(option, ban) {
    option.setAttribute("value", JSON.stringify(ban));
    option.textContent = ban.motive;
}

function processStationClosingMotive(option, scm) {
    option.setAttribute("value", JSON.stringify(scm));
    option.textContent = scm.label;
}

function processAccountClosingMotive(option, acm) {
    option.setAttribute("value", JSON.stringify(acm));
    option.textContent = acm.libelle;
}

function processReservationAnormalEnding(option, rae) {
    option.setAttribute("value", JSON.stringify(rae));
    option.textContent = rae.anormalEndingLabel;
}

function processRatingType(option, rating) {
    option.setAttribute("value", JSON.stringify(rating));
    option.textContent = rating.labelRatingType;
}

function processStationService(option, service) {
    option.setAttribute("value", JSON.stringify(service));
    option.textContent = service.labelStationService;
}

function processCreditCards(option, creditCard) {
    option.setAttribute("value", JSON.stringify(creditCard));
    option.textContent = `****${creditCard.cardNumber}`;
}

function processVehicles(option, vehicle) {
    option.setAttribute("value", JSON.stringify(vehicle));
    option.textContent = vehicle.vehicleName;
}