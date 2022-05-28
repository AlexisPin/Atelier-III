const form = document.querySelector("form");
const succes_message = document.querySelector(".success-message");

const handleSubmit = (e) => {
  e.preventDefault();
  const data = $("form").serializeArray();
  let card = {};
  for (i in data) {
    card[data[i].name] = parseInt(data[i].value, 10)
      ? parseInt(data[i].value, 10)
      : data[i].value;
  }
  fetchData(card);
  succes_message.style.display = "block";
};

form.addEventListener("submit", handleSubmit);

function fetchData(card) {
  let context = {
    method: "POST",
    body: JSON.stringify(card),
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch("http://127.0.0.1:8080/newCard", context).then(() => clearForm());
}

const clearForm = () => {
  form.reset();
};
