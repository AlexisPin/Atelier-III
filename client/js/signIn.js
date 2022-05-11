const surname = document.getElementById("surname");
const password = document.getElementById("password");

const form = document.getElementById("form");

form.addEventListener("submit", function (event) {
  event.preventDefault();
  const users = [];
  const context = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  };

  fetch("http://localhost:8081/users", context)
    .then((response) => response.json())
    .then((data) => getData(data, users))
    .then(() => login(users, surname, password))
    .catch((error) => console.log(error));
});

const checkCredentials = (users, surname, password) => {
  for (const user in users) {
    if (users[user].login === surname && users[user].pwd === password) {
      return true;
    }
  }
  return false;
};

const getData = (data, users) => {
  for (const user in data) {
    users.push(data[user]);
  }
};

const login = (users, surname, password) => {
  for (const user in users) {
    if (
      users[user].surName === surname.value &&
      users[user].pwd === password.value
    ) {
      window.location.href = "../index.html";
      window.localStorage.setItem("id", users[user].id);
      return;
    }
  }
  alert("Wrong credentials!");
};
