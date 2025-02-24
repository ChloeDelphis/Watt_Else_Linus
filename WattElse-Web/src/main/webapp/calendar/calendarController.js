
'use strict';

import { getBackUrl } from "../commands/backUrl.js";
import { updateCalendarDisplay } from "./calendarView.js";


export function updateCalendar(stationId) {
    fetch("./calendar/calendar.html")
    .then(response => response.text())
    .then(html => {
        document.getElementById("calendar-display").innerHTML = html;
        fetchCalendar(stationId);
    });
    
}

function fetchCalendar(stationId) {

    const requestOptions = {
        headers: {"Authorization": "Bearer " + JSON.parse(sessionStorage.getItem("user")).token}
    };
    fetch(`${getBackUrl()}/search/station/${stationId}/calendar`,requestOptions)
    .then(response => response.json())
    .then(json => {
        updateCalendarDisplay(json);
    });
}