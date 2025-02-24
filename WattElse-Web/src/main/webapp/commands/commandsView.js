export function incorrectEmailDisplay() {
    document.getElementById("messageLabel").innerText = "Identifiant et/ou mot de passe incorrect(s)";
}

export function incompleteFormDisplay() {
    document.getElementById("messageLabel").innerText = "Veuillez remplir tous les champs";
}

export function resetForm() {
    document.getElementById("messageLabel").innerText = " ";

    const form = document.querySelector("form");
    form.reset();
}

export function getFullTimeReservation(hours, minutes) {
    if (minutes == 0) {
        return `${hours}h00`;
    }else {
        return `${hours}h${minutes}`;
    }
}

export function addressDisplay(address) {
    // Séparer les éléments de l'adresse
    let parts = address.split(", ");
    
    // Vérifier que l'adresse contient suffisamment d'éléments
    if (parts.length < 8) return "Adresse invalide";
    
    // Extraire les éléments nécessaires
    let street = parts[0] + ", " + parts[1]; // Numéro et nom de la rue
    let postalCity = parts[7] + " " + parts[3]; // Code postal + Ville
    
    // Retourner l'adresse formatée
    return `${street}, ${postalCity}`;
}

export function formatDateTime(dateTimeStr) {
    const moisComplets = ["janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"];
    
    const date = new Date(dateTimeStr);
    const jour = date.getDate();
    const mois = moisComplets[date.getMonth()];
    const annee = date.getFullYear().toString().slice(-2);
    const heures = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    
    return `${jour} ${mois} ${annee} à ${heures}h${minutes}`;
}
