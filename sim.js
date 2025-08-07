document.addEventListener("DOMContentLoaded", () => {
    // Constants
    const totalActionValue = 150;
    const barWidth = 800; // match CSS max-width

    // Character info
    const speed = 150;
    const actionCost = 10000;

    const bar = document.getElementById("bar");
    const label = document.getElementById("label");

    // Calculate how many actions fit in 150 AV
    const avPerAction = actionCost / speed;
    const actionsInCycle = Math.floor(totalActionValue / avPerAction);

    for (let i = 0; i < actionsInCycle; i++) {
        const avUsed = (i + 1) * avPerAction;
        const leftPercent = avUsed / totalActionValue;
        const leftPx = leftPercent * barWidth;

        const marker = document.createElement("div");
        marker.className = "action-marker";
        marker.style.left = `${leftPx}px`;
        bar.appendChild(marker);
    }

    label.textContent = `Speed: ${speed} → Action every ${avPerAction.toFixed(2)} AV. Actions in first cycle: ${actionsInCycle}`;
})
