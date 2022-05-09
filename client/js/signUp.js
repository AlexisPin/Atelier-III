const surname = document.getElementById("surname");
const password = document.getElementById("password");
const lastname = document.getElementById("lastname");
const confirmPassword = document.getElementById("confirmPassword");

const form = document.getElementById("form");

form.addEventListener("submit", function (event) {
  event.preventDefault();
  const user = {
    surName: surname.value,
    lastName: lastname.value,
    pwd: password.value,
  };

  const context = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  };
  if (password.value !== confirmPassword.value) {
    alert("Passwords do not match!");
  } else {
    fetch("https://asi2-backend-market.herokuapp.com/user", context)
      .then(() => goToLogin())
      .catch((error) => console.log(error));
  }
});

const goToLogin = () => {
  window.location.replace("http://127.0.0.1:5500/pages/login.html");
};
