export function displayUserInfo (userInfo) {

    const myInfo__name = document.querySelector(".myInfo__name");
    const myInfo__email = document.querySelector(".myInfo__email")
    const myInfo__bithdate = document.querySelector(".myInfo__bithdate")
    const myInfo__tel = document.querySelector(".myInfo__tel")
    const myInfo__address = document.querySelector(".myInfo__address")

    myInfo__name.innerHTML = userInfo.firstName+ " "+userInfo.lastName;
    myInfo__email.innerHTML = userInfo.email;
    myInfo__bithdate.innerHTML = userInfo.birthDate;
    myInfo__tel.innerHTML = userInfo.phone;
    myInfo__address.innerHTML = userInfo.addressDisplay;


}