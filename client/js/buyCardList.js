let cardsList = [];
let userAccount = 0;
//let userCardsId = [];
const userId = window.localStorage.getItem("id");

const fetchUserInfos = () => {
  const context = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch(`https://asi2-backend-market.herokuapp.com/user/${userId}`, context)
    .then((response) => response.json())
    .then((data) => {
      //userCardsId.push(...data.cardList),
      userAccount = data.account;
    })
    .then(() => fetchCardsList())
    .catch((error) => console.log(error));
};
fetchUserInfos();

const fetchCardsList = () => {
  const context = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch(`https://asi2-backend-market.herokuapp.com/cards`, context)
    .then((response) => response.json())
    .then((data) => cardsList.push(...data))
    .then(() => {
      displayCards(cardsList), addEvent();
    })
    .catch((error) => console.log(error));
};

const displayCards = (cardsList) => {
  let template = document.querySelector("#row");

  cardsList.forEach((card) => {
    let clone = document.importNode(template.content, true);

    newContent = clone.firstElementChild.innerHTML
      //.replace(/{{family_src}}/g, card.imgUrl)
      .replace(/{{family_name}}/g, card.family)
      .replace(/{{img_src}}/g, card.smallImgUrl)
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

const buyCard = (id, cardPrice) => {
  //userAccount -= cardPrice;
  //userCardsId = [...userCardsId, id];
  const context = {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      card: id,
    }),
  };

  fetch(`https://asi2-backend-market.herokuapp.com/user/${userId}`, context)
    .then((response) => response.json())
    .then((data) => updateMoney(data))
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
      cardsList.forEach((card) => {
        if (card.id === id) {
          const cardPrice = card.price;
          userAccount >= cardPrice
            ? buyCard(id, cardPrice)
            : alert("Not enough money");
        }
      });
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
    .replace(/{{hp}}/g, round(card.hp))
    .replace(/{{energy}}/g, round(card.energy))
    .replace(/{{attack}}/g, round(card.attack))
    .replace(/{{defence}}/g, round(card.defence))
    .replace(/{{price}}/g, round(card.price))
    .replace(/{{id}}/g, card.id);
  clone.firstElementChild.innerHTML = newContent;

  let cardContainer = document.querySelector("#card");
  cardContainer.childElementCount > 1 &&
    cardContainer.removeChild(cardContainer.lastElementChild);
  cardContainer.appendChild(clone);

  const buySellButton = document.querySelector(".buySellButton");
  buySellButton.addEventListener("click", (e) => {
    const id = parseInt(e.target.id, 10);
    cardsList.forEach((card) => {
      if (card.id === id) {
        const cardPrice = card.price;
        userAccount >= cardPrice
          ? buyCard(id, cardPrice)
          : alert("Not enough money");
      }
    });
  });
};

const round = (x) => {
  return Number.parseFloat(x).toFixed();
};

const updateMoney = (newAccount) => {
  userAccount = newAccount;
  let account = document.querySelector("#account");
  account.innerHTML = newAccount.toString();
  alert("Card bought remains " + userAccount + " $");
};
