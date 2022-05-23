const userId = window.localStorage.getItem("id");

const fetchCardsList = () => {
  const context = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch(`http://localhost:8081/cards`, context)
    .then((response) => response.json())
    .then((data) => {
      displayCards(data), addEvent();
    })
    .catch((error) => console.log(error));
};
fetchCardsList();
const displayCards = (cardsList) => {
  let template = document.querySelector("#row");
  cardsList?.forEach((card) => {
    let clone = document.importNode(template.content, true);

    newContent = clone.firstElementChild.innerHTML
      //.replace(/{{family_src}}/g, card.imgUrl)
      .replace(/{{family_name}}/g, card.family)
      .replace(/{{affinity}}/g, card.affinity)
      .replace(/{{img_src}}/g, card.imgUrl)
      .replace(/{{name}}/g, card.name)
      .replace(/{{description}}/g, card.description)
      .replace(/{{hp}}/g, card.hp)
      .replace(/{{energy}}/g, card.energy)
      .replace(/{{attack}}/g, card.attack)
      .replace(/{{defence}}/g, card.defence)
      .replace(/{{price}}/g, card.price)
      .replace(/{{id}}/g, card.id)
      .replace(/{{id_card}}/g, card.id);
    clone.firstElementChild.innerHTML = newContent;

    let cardContainer = document.querySelector("#tableContent");
    cardContainer.appendChild(clone);
  });
};

const buyCard = (id) => {
  const context = {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      card: id,
    }),
  };

  fetch(`http://localhost:8080/user/${userId}/buy`, context)
    .then((response) => response.json())
    .then((data) => updateMoney(data, id))
    .catch((error) => console.log(error));

  let rightCardContainer = document.querySelector("#card");
  rightCardContainer.childElementCount > 1 &&
    rightCardContainer.lastElementChild?.remove();
};

const addEvent = () => {
  const rows = document.querySelectorAll(".row-card");
  rows.forEach((row) => {
    row.addEventListener("click", (e) => {
      let cardInfo = {
        imgUrl: null,
        name: null,
        description: null,
        family: null,
        hp: null,
        energy: null,
        defence: null,
        attack: null,
        price: null,
        id: null,
      };
      row.querySelectorAll("td").forEach((td) => {
        cardInfo[td.className] = td.innerHTML;

        td.className === "imgUrl" &&
          ((cardInfo["imgUrl"] = td.firstElementChild.src),
          (cardInfo["name"] = td.appendChild(td.lastElementChild).innerHTML));

        td.className === "id" &&
          (cardInfo["id"] = parseInt(
            td.firstElementChild.firstElementChild.id,
            10
          ));
        td.className === "price" &&
          (cardInfo["price"] = parseInt(td.innerHTML.split(-1), 10));
      });
      e.target.id == "" && displayCard(cardInfo);
    });
  });
  const sellBtn = document.querySelectorAll(".sellButton");
  sellBtn.forEach((btn) => {
    btn.addEventListener("click", (e) => {
      const id = parseInt(e.target.id, 10);
      buyCard(id);
    });
  });
};

const displayCard = (card) => {
  let template = document.querySelector("#right-card");
  let clone = document.importNode(template.content, true);
  newContent = clone.firstElementChild.innerHTML
    //.replace(/{{family_src}}/g, card.imgUrl)
    .replace(/{{family_name}}/g, card.family)
    .replace(/{{img_src}}/g, card.imgUrl)
    .replace(/{{name}}/g, card.name)
    .replace(/{{description}}/g, card.description)
    .replace(/{{hp}}/g, card.hp)
    .replace(/{{energy}}/g, card.energy)
    .replace(/{{attack}}/g, card.attack)
    .replace(/{{defence}}/g, card.defence)
    .replace(/{{price}}/g, card.price)
    .replace(/{{id}}/g, card.id);
  clone.firstElementChild.innerHTML = newContent;

  let cardContainer = document.querySelector("#card");
  cardContainer.childElementCount > 1 &&
    cardContainer.removeChild(cardContainer.lastElementChild);
  cardContainer.appendChild(clone);

  const buySellButton = document.querySelector(".buySellButton");
  buySellButton.addEventListener("click", (e) => {
    const id = parseInt(e.target.id, 10);
    buyCard(id);
  });
};

const updateMoney = (data, id) => {
  data.errorMessage
    ? alert(data.errorMessage)
    : ((account = document.querySelector("#account")),
      (account.innerHTML = data.toString()),
      alert("Vous venez d'acheter une carte !"));

  if (!data.errorMessage) {
    let cardContainer = document.querySelector("#tableContent");
    var children = Array.from(cardContainer.children).splice(1);
    children.forEach((child) => {
      if (child.lastElementChild.firstElementChild.firstElementChild.id == id) {
        cardContainer.removeChild(child);
      }
    });
  }
};
