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
  addEvent();
};

const userId = window.localStorage.getItem("id");
const userInfos = [];
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

const joinRoom = (roomName) => {
  if (stompClient) {
    stompClient.send(
      `/room/${roomName}/private`,
      {},
      JSON.stringify(userInfos)
    );
  }
};

function onError(error) {
  console.log(error);
}

const fetchUserInfos = () => {
  const context = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch(`http://127.0.0.1:8081/user/${userId}`, context)
    .then((response) => response.json())
    .then((data) => userInfos.push(data))
    .catch((error) => console.log(error));
};

fetchUserInfos();

const connect = () => {
  var socket = new SockJS("http://localhost:8081/ws");
  stompClient = Stomp.over(socket);
  stompClient.connect({}, onConnected, onError);
};

connect();

const addEvent = () => {
  const playButtons = document.querySelectorAll("#play-button");
  playButtons.forEach((button) => {
    button.addEventListener("click", () => {
      joinRoom(button.firstElementChild.id);
    });
  });
};
