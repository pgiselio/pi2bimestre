const searchField = document.querySelector("#txtBusca");
window.addEventListener('load', () => {
  if (searchField.value){
    expandirSearch();
  }
});

function expandirEsconderMenu() {
  document.body.classList.toggle("show-menu");
}
function getWidth() {
  return document.body.clientWidth;
}

function expandirSearch() {
  if (getWidth() < 767) {
    document.body.classList.add("seachOnFocus");
  }
}
function ocultarSearch() {
  if(!searchField.value) document.body.classList.remove("seachOnFocus");
}
