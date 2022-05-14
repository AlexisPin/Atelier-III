const userId = window.localStorage.getItem("id");
const userInfos = [];
let room = {
  name: "",
  bet: "",
  user: "",
};
let stompClient = null;

const onConnected = () => {
  room = {
    ...room,
    user: userInfos[0].surName,
  };
  stompClient.subscribe("/room/public", onRoomReceived);
  stompClient.subscribe(`/room/${room.name}/private`, onPlayerReceived);
  roomCreate();
  const formContainer = document.querySelector("#form-container");
  formContainer.style.display = "none";
  const waitingContainer = document.querySelector("#waiting-container");
  waitingContainer.style.display = "block";
  const pageTitle = document.querySelector("#title");
  pageTitle.innerHTML = `Play Room ${room.name}`;
};

const onRoomReceived = (payload) => {
  const roomInfo = JSON.parse(payload.body);
  //
};

const onPlayerReceived = (payload) => {
  console.log(payload);
  const data = JSON.parse(payload.body);
  const player = data;
  window.location.href = "/pages/game.html";
};

const createButton = document.getElementById("room-container");

function onError(error) {
  console.log(error);
}

const handleRoomData = () => {
  room = {
    ...room,
    name: roomName.value,
    bet: bet.value,
  };
};
const registerRoom = (e) => {
  e.preventDefault();
  var socket = new SockJS("http://localhost:8081/ws");
  stompClient = Stomp.over(socket);
  stompClient.connect({}, onConnected, onError);
};

const roomCreate = () => {
  stompClient.send(`/room/public`, {}, JSON.stringify(room));
};

const roomForm = document.getElementById("room-form");
const roomName = document.getElementById("name");
const bet = document.getElementById("bet");

const createRoomButton = document.getElementById("create-room");

roomForm.addEventListener("change", handleRoomData);
createRoomButton.addEventListener("click", registerRoom);

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
