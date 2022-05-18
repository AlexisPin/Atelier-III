const surname = document.getElementById("surname");
const password = document.getElementById("password");

const form = document.getElementById("form");

form.addEventListener("submit", function (event) {
  event.preventDefault();
  const user = {
    login: surname.value,
    pwd: password.value,
  };
  const context = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  };

  fetch("http://localhost:8081/login", context)
    .then((response) => response.json())
    .then((data) => login(data))
    .catch((error) => console.log(error));
});

const login = (data) => {
  data.errorMessage
    ? alert(data.errorMessage)
    : (window.localStorage.setItem("id", data.id),
      (window.location.href = "../index.html"));
};
