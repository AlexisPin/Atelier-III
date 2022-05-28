let userInfos = [];
const userId = window.localStorage.getItem("id");
let selectedCard = null;

const fetchUserInfos = () => {
  const context = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch(`http://127.0.0.1:8080/userId/${userId}`, context)
    .then((response) => response.json())
    .then((data) => userInfos.push(data))
    .then(() => {
      displayCards(userInfos[0].cardList), addEvent();
    })
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
      .replace(/{{defence}}/g, card.defence)
      .replace(/{{attack}}/g, card.attack);

    clone.firstElementChild.innerHTML = newContent;
    let cardContainer = document.querySelector("#tableContent");
    cardContainer.appendChild(clone);
    cardContainer.lastElementChild.id = card.id;
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

        td.className === "id" && (cardInfo["id"] = parseInt(row.id, 10));
        td.className === "price" &&
          (cardInfo["price"] = parseInt(td.innerHTML.split(-1), 10));
      });
      e.target.id == "" && displayCard(cardInfo);
      selectedCard = parseInt(row.id, 10);
    });
  });

  const playButton = document.querySelector("#playButton");
  playButton.addEventListener("click", (e) => {
    if (selectedCard) {
      fetchCard(selectedCard);
    } else {
      alert("Please select a card");
    }
  });
};

const displayCard = (card) => {
  let template = document.querySelector("#right-card");
  let clone = document.importNode(template.content, true);

  newContent = clone.firstElementChild.innerHTML
    .replace(/{{family_name}}/g, card.family)
    .replace(/{{img_src}}/g, card.imgUrl)
    .replace(/{{name}}/g, card.name)
    .replace(/{{description}}/g, card.description)
    .replace(/{{hp}}/g, card.hp)
    .replace(/{{energy}}/g, card.energy)
    .replace(/{{attack}}/g, card.attack)
    .replace(/{{defence}}/g, card.defence)
    .replace(/{{price}}/g, card.price);

  clone.firstElementChild.innerHTML = newContent;

  let cardContainer = document.querySelector("#card");
  cardContainer.childElementCount > 1 &&
    cardContainer.removeChild(cardContainer.lastElementChild);
  cardContainer.appendChild(clone);
};

const displayRooms = async (rooms) => {
  let template = document.querySelector("#row");
  let roomElement = [];
  await rooms?.forEach((room) => {
    let clone = document.importNode(template.content, true);

    newContent = clone.firstElementChild.innerHTML
      .replace(/{{room}}/g, room.name)
      .replace(/{{user}}/g, room.user)
      .replace(/{{bet}}/g, room.bet);

    clone.firstElementChild.innerHTML = newContent;
    roomElement.push(clone);
  });
  let roomContainer = document.querySelector("#tableContent");
  const roomTemplate = roomContainer.lastElementChild;
  roomContainer.replaceChildren(...roomElement, roomTemplate);
};

const rooms = [];
let stompClient = null;

const onConnected = () => {
  stompClient.subscribe("/room/public", onRoomReceived);
};

const onRoomReceived = (payload) => {
  const roomInfo = JSON.parse(payload.body);
  rooms.push(roomInfo);
  displayRooms(rooms);
};

const joinRoom = (card) => {
  if (stompClient) {
    stompClient.send("/app/start", {}, JSON.stringify(card));
  }
};

function onError(error) {
  console.log(error);
}

const connect = () => {
  var socket = new SockJS("http://localhost:8081/ws");
  stompClient = Stomp.over(socket);
  stompClient.connect({}, onConnected, onError);
};

connect();

const fetchCard = async (id) => {
  const context = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch(`http://127.0.0.1:8080/card/${id}`, context)
    .then((response) => response.json())
    .then((data) => joinRoom(data))
    .catch((error) => console.log(error));
};
