const connect = (e) => {
  e.preventDefault();
  var socket = new SockJS("http://localhost:8081/room");
  socket.onopen = function () {
    console.log("Connected to server");
  };
};

const onConnected = () => {
  stompClient.subscribe("/game/public", onUserReceived);

  stompClient.send(
    "/app/game.register",
    {},
    JSON.stringify({ sender: username, type: "JOIN" })
  );

  connectingElement.classList.add("hidden");
};

const onUserReceived = (payload) => {
  const data = JSON.parse(payload.body);
  console.log(data);
};

const createButton = document.getElementById("room-container");

createButton.addEventListener("click", connect);

function onError(error) {
  connectingElement.textContent =
    "Could not connect to WebSocket server. Please refresh this page to try again!";
  connectingElement.style.color = "red";
}

function send(event) {
  var messageContent = messageInput.value.trim();

  if (messageContent && stompClient) {
    var chatMessage = {
      sender: username,
      content: messageInput.value,
      type: "CHAT",
    };

    stompClient.send("/app/room.send", {}, JSON.stringify(chatMessage));
    messageInput.value = "";
  }
  event.preventDefault();
}

messageForm.addEventListener("submit", send, true);
