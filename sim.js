document.addEventListener("DOMContentLoaded", () => {
  const totalActionValue = 150;
  const barWidth = 800;

  const characters = [
    { name: "Char 1", speed: 134, av: 10000, color: "teal" },
    { name: "Char 2", speed: 132, av: 10000, color: "purple" },
    { name: "Char 3", speed: 108, av: 10000, color: "crimson" },
    { name: "Char 4", speed: 120, av: 10000, color: "orange" }
  ];

  const bar = document.getElementById("bar");
  const label = document.getElementById("label");

  characters.forEach((char, index) => {
    const { speed, av, color, name } = char;
    const avPerAction = av / speed;
    const actionsInCycle = Math.floor(totalActionValue / avPerAction);

    for (let i = 0; i < actionsInCycle; i++) {
      const avUsed = (i + 1) * avPerAction;
      const leftPercent = avUsed / totalActionValue;
      const leftPx = leftPercent * barWidth;

      const marker = document.createElement("div");
      marker.className = "action-marker";
      marker.style.left = `${leftPx}px`;
      marker.style.backgroundColor = color;
      marker.title = `${name} - Turn ${i + 1}`;
      bar.appendChild(marker);
    }
  });

  label.innerHTML = characters.map(c => 
    `<span style="color:${c.color}">${c.name} (${c.speed} SPD)</span>`
  ).join(" • ");
});
