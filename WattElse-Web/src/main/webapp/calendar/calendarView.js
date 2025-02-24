export function updateCalendarDisplay(calendar) {
    for (let i = 0; i < 7; i++) {
        updateDay(i,calendar);
    }
}

function updateDay(dayNumber, calendar) {
    const obj = document.getElementById(`j${dayNumber}`);
    const objLabel = document.getElementById(`j${dayNumber}-label`);

    var date = new Date();
    date.setDate(date.getDate() + dayNumber);

    let day = undefined;
    switch (date.getDay()) {
        case 0:
            day = "Dimanche";
            break;
        case 1:
            day = "Lundi";
            break;
        case 2:
            day = "Mardi";
            break;
        case 3:
            day = "Mercredi";
            break;
        case 4:
            day = "Jeudi";
            break;
        case 5:
            day = "Vendredi";
            break;
        case 6:
            day = "Samedi";
            break;
        default:
            break;
    }

    objLabel.innerText = `${day} ${date.getDate()}`

    for (let i = 0; i < calendar.j[dayNumber].openingHours.length; i++) {
        const disp = calendar.j[dayNumber].openingHours[i];
        const offset = disp.startHour + (disp.startMinute/15)*0.25;
        const width = (disp.endHour + (disp.endMinute/15)*0.25) - (disp.startHour + (disp.startMinute/15)*0.25);

        const a = document.createElement("div");
        a.setAttribute("class","w-100 bg-success-subtle");
        a.setAttribute("title",`Ouvert de: ${disp.startHour}:${disp.startMinute} à ${disp.endHour}:${disp.endMinute}`);
        a.setAttribute("style",`position: absolute; height: ${width}rem; top: ${offset}rem`)

        obj.appendChild(a);
    }

    for (let i = 0; i < calendar.j[dayNumber].reservations.length; i++) {
        const disp = calendar.j[dayNumber].reservations[i];
        const offset = disp.startHour + (disp.startMinute/15)*0.25;
        const width = (disp.endHour + (disp.endMinute/15)*0.25) - (disp.startHour + (disp.startMinute/15)*0.25);

        const a = document.createElement("div");
        a.setAttribute("class","w-100 bg-warning-subtle");
        a.setAttribute("title",`Occupé de: ${disp.startHour}:${disp.startMinute} à ${disp.endHour}:${disp.endMinute}`);
        a.setAttribute("style",`position: absolute; height: ${width}rem; top: ${offset}rem`)

        obj.appendChild(a);
    }
}