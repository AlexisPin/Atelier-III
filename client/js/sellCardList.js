let userCardsData = [];
let userCardsId = [];
let userAccount = 0;
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
      userCardsId.push(...data.cardList), (userAccount = data.account);
    })
    .then(() => fetchCardList(userCardsId))
    .catch((error) => console.log(error));
};

fetchUserInfos();

const fetchCardList = (userCardsId) => {
  const context = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  };
  userCardsId.forEach((id) => {
    fetch(`https://asi2-backend-market.herokuapp.com/card/${id}`, context)
      .then((response) => response.json())
      .then((data) => userCardsData.push(data))
      .then(() => isLoaded())
      .catch((error) => console.log(error));
  });
};

const isLoaded = () => {
  if (userCardsData.length == userCardsId.length) {
    displayCards(userCardsData);
    addEvent();
  }
};

const displayCards = (userCardsData) => {
  let template = document.querySelector("#row");

  userCardsData.forEach((card) => {
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

const sellCard = (id) => {
  userCardsId = userCardsId.filter((card) => card != id);
  userAccount = userAccount + userCardsData.find((card) => card.id == id).price;
  const context = {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      cardList: userCardsId,
      account: userAccount,
    }),
  };

  fetch(`https://asi2-backend-market.herokuapp.com/user/${userId}`, context)
    .then(() => updateMoney(userAccount))
    .catch((error) => console.log(error));

  let cardContainer = document.querySelector("#tableContent");
  var children = Array.from(cardContainer.children).splice(1);
  children.forEach((child) => {
    if (child.lastElementChild.firstElementChild.firstElementChild.id == id) {
      cardContainer.removeChild(child);
    }
  });
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
          (cardInfo["name"] = td.lastElementChild.innerHTML));

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
      sellCard(parseInt(e.target.id, 10));
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
  buySellButton?.addEventListener("click", (e) => {
    sellCard(parseInt(e.target.id, 10));
  });
};

const round = (x) => {
  return Number.parseFloat(x).toFixed();
};

const updateMoney = (newAccount) => {
  let account = document.querySelector("#account");
  account.innerHTML = newAccount.toString();
};
