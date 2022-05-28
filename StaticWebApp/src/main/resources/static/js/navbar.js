const user = {};
const id = window.localStorage.getItem("id");
window.addEventListener("load", function () {
  fetch(`http://127.0.0.1:8080/userId/${id}`)
    .then((response) => response.json())
    .then((data) => getData(data));
});

const getData = (data) => {
  const user = {
    login: data.login,
    account: data.account,
  };

  let currentPage = "";
  if (window.location.href.includes("buy")) {
    currentPage = "BUY";
  } else if (window.location.href.includes("sell")) {
    currentPage = "SELL";
  } else if (window.location.href.includes("play")) {
    currentPage = "PLAY";
  } else if (window.location.href.includes("index")) {
    currentPage = "HOME";
  } else if (window.location.href.includes("room")) {
  currentPage = "ROOM CREATION";
}
  let template = document.querySelector("#navbar-content");
  let clone = document.importNode(template.content, true);

  newContent = clone.firstElementChild.innerHTML
    .replace(/{{money}}/g, user.account)
    .replace(/{{currentPage}}/g, currentPage)
    .replace(/{{login}}/g, user.login);
  clone.firstElementChild.innerHTML = newContent;

  let cardContainer = document.querySelector("#gridContainer");
  cardContainer.appendChild(clone);

  const homeButton = document.getElementById("homeButton");
  homeButton?.addEventListener("click", () => {
    window.location.href = "../index.html";
  });
};

const cards = document.querySelectorAll(".card");

cards.forEach((card) => {
  card.addEventListener("click", function () {
    window.location.replace(`/pages/${card.id}.html`);
  });
});
