'use strict';

/* Displays the header */
fetch("./arch/header.html")
    .then(response => response.text())
    .then(html => document.getElementById("header").innerHTML = html);

/*
If a user is connected, displays
the navigation bar including their first and last name 
*/
let user = sessionStorage.getItem("user");

const navigationHtml = user 
    ? "./arch/navigation.html"
    : "./arch/navigationUnconnected.html"

fetch(navigationHtml)
    .then(response => response.text())
    .then(html => document.getElementById("navigation").innerHTML = html)
    // Coloring the active link
    .then(() => {
        Array
            .from(document.getElementsByClassName("nav-link"))
            .forEach(element => {
                const currentHtml = window.location.pathname.substring(
                    window.location.pathname.lastIndexOf('/') + 1
                );
                const elementHtml = element.href.substring(
                    element.href.lastIndexOf('/') + 1
                );
                element.setAttribute("class", 
                    (currentHtml === elementHtml) ? "nav-link active" : "nav-link"
                );
            }
        )
        if (user) {
            user = JSON.parse(user);
            document.getElementById("connected-user").innerHTML = `${user.lastName} ${user.firstName}`;        
        }
    }
);


/* Displays the footer */
fetch("./arch/footer.html")
    .then(response => response.text())
    .then(html => document.getElementById("footer").innerHTML = html);


/* Disconnect */
function disconnect() {
    sessionStorage.setItem("user", "");
    window.location.href = "connection.html";
}
