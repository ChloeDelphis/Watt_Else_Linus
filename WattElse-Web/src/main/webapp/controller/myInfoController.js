// ************** Import des fonctions de la vue **************

import {
  displayUserInfo
}
  from "../view/myInfoView.js";

import { getUser } from "../commands/commands.js";

import { getBackUrl } from "./backUrl.js";

// ************** Adresse **************

const backUrl = `${getBackUrl()}/user/info`;

// ************** Affichage initial **************

async function initialDisplay () {
  let userInfo = await fetchUserInfo();
  displayUserInfo(userInfo);
}

// ************** Fetch **************

async function fetchUserInfo() {

  const user = getUser();
  const userId = user.id;
  const userToken = user.token;

  const requestOptions = { headers: { Authorization: "Bearer " + userToken } };

  try {
    const response = await fetch(
      `${backUrl}/${userId}`,
      requestOptions
    );

    if (!response.ok) {
      throw new Error(`Erreur ${response.status}: ${response.statusText}`);
    }

    return response.json();

  } catch (error) {
    console.error(
      "Une erreur s'est produite lors de la récupération des informations de l'utilisateur à l'id :"+userId,
      error.message
    );
  }



}






// ************** Mise en place des listeners (quand le DOM est chargé) **************
document.addEventListener("DOMContentLoaded", async () => {
  console.log("Page chargée - Initialisation des événements");

  initialDisplay();

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
