const artistsNodeList = document.querySelectorAll(".podio a");
const artistsList = Array.from(artistsNodeList);
for(let i = 0; i < artistsList.length ; i++){
    addTitle(artistsList[i]);
}

function addTitle(element){
    let title = element.querySelector("b").textContent + "\n" + element.querySelector("span").textContent;
    element.setAttribute('title', title);
}