const btnuser = document.querySelector(".btnuser");

btnuser.addEventListener("mousedown", function () {  
    btnuser.classList.add("pressed");
});

btnuser.addEventListener("mouseup", function () {
    btnuser.classList.remove("pressed");
});
