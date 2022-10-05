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
  document.body.classList.remove("seachOnFocus");
}
