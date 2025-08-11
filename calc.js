document.addEventListener("DOMContentLoaded", () => {
    const actions = document.getElementById("actions");
    const cycleSelector = document.getElementById("cycleSelector")
    const reqSpeed = document.getElementById("reqSpeed");

    for (let i = 0; i <= 10; i++) { // init
        const option = document.createElement("option");
        option.value = 150 + 100*i;
        option.textContent = i;

        const option2 = document.createElement("option");
        option2.value = i + 1;
        option2.textContent = i + 1;

        cycleSelector.appendChild(option);
        actions.appendChild(option2);
        
        actions.value = 2;
        cycleSelector.value = 150;
        updateReqSpeed();
    }
    
    function updateReqSpeed() {
        let requiredSpeed = actions.value * 10000 / cycleSelector.value;
        reqSpeed.innerHTML = requiredSpeed.toFixed(1);
        // console.log(requiredSpeed);
    }

    actions.addEventListener("change", updateReqSpeed);
    cycleSelector.addEventListener("change", updateReqSpeed);
})