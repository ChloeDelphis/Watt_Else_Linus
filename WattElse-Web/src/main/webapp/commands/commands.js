import {
  incorrectEmailDisplay,
  incompleteFormDisplay,
} from "./commandsView.js";

export function authenticate(email, password) {
  if (email === "" || password === "") {
    incompleteFormDisplay();
  } else {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email: email, password: password }),
    };

    console.log(requestOptions);

    fetch(`http://127.0.0.1:8080/api/rest/test/authenticate`, requestOptions)
      .then((response) =>
        response.ok ? response.text() : Promise.reject(response)
      )
      .then((text) => {
        sessionStorage.setItem("user", text);
        window.location.href = "home.html";
      })
      .catch((response) => {
        incorrectEmailDisplay();
        console.error(
          "Une erreur s'est produite lors de l'authentification",
          `${response.status} ${response.statusText}`
        );
      });
  }
}

export function getUser() {
  //   const user = JSON.parse(sessionStorage.getItem("user"));
  // Récupération correcte du user
  const user = JSON.parse(sessionStorage.getItem("user"));
  if (!user) {
    window.location.href = "connection.html";
    return null;
  }
  return user;
}

export function getUserId() {
  const user = getUser();
  const userID = user.id;
  return userID;
}

export function getUserToken() {
  const user = getUser();
  const userToken = user.token;
  return userToken;
}

export function ifNotNull(item){
  if (item != null) {
    return item;
}else {
    return null;
}
}