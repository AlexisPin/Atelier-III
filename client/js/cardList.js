let userCards = [];
const userId = window.localStorage.getItem("id");

const fetchUserInfos = () => {
  const context = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch(`http://127.0.0.1:8081/user/${userId}`, context)
    .then((response) => response.json())
    .then((data) => {
      userCards.push(...data.cardList);
    })
    .then(() => displayCards(userCards))
    .then(() => addEvent())
    .catch((error) => console.log(error));
};

fetchUserInfos();

const displayCards = (userCardsData) => {
  let template = document.querySelector("#row");

  userCardsData.forEach((card) => {
    let clone = document.importNode(template.content, true);

    newContent = clone.firstElementChild.innerHTML
      .replace(/{{family_name}}/g, card.family)
      .replace(/{{affinity}}/g, card.affinity)
      .replace(/{{img_src}}/g, card.imgUrl)
      .replace(/{{name}}/g, card.name)
      .replace(/{{description}}/g, card.description)
      .replace(/{{hp}}/g, card.hp)
      .replace(/{{energy}}/g, card.energy)
      .replace(/{{id}}/g, card.id);
    clone.firstElementChild.innerHTML = newContent;

    let cardContainer = document.querySelector("#tableContent");
    cardContainer.appendChild(clone);
  });
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
};
