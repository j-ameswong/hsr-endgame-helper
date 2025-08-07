document.addEventListener("DOMContentLoaded", () => {
  const totalAV = 150;
  const barWidth = 800;
  const actionCost = 10000;
  const eagleBoost = 2500;
  const dddBoost = 2400;

  const form = document.getElementById("character-form");
  const bar = document.getElementById("bar");
  const legend = document.getElementById("legend");

  const characters = [];

  function generateColor(index) {
    const colors = ['teal', 'crimson', 'purple', 'orange', 'green', 'blue', 'magenta'];
    return colors[index % colors.length];
  }

  // TODO: DDD Superimposition Level Selector

  function render() {
    bar.innerHTML = "";
    legend.innerHTML = "";

    // Count total DDD ultimates (used for global bonus)
    const totalDddCasts = characters.reduce((sum, c) => sum + (c.ddd ? c.ultimates : 0), 0);

    characters.forEach((char, index) => {
      const { name, speed, eagle, ultimates, ddd, color } = char;

      // Base gain
      let gain = speed * totalAV;

      // Eagle applies only to self
      if (eagle) gain += ultimates * eagleBoost;

      // DDD applies globally
      gain += totalDddCasts * dddBoost;

      const avPerAction = actionCost / speed;
      const actions = Math.floor(gain / actionCost);

      for (let i = 0; i < actions; i++) {
        const avUsed = (i + 1) * avPerAction;
        const leftPercent = avUsed / totalAV;
        const leftPx = leftPercent * barWidth;

        if (leftPx > barWidth) continue;

        const marker = document.createElement("div");
        marker.className = "action-marker";
        marker.style.left = `${leftPx - 5}px`;
        marker.style.backgroundColor = color;
        marker.title = `${name} - Turn ${i + 1} - ${avUsed} AV`;
        bar.appendChild(marker);
      }

      const legendItem = document.createElement("span");
      legendItem.innerHTML = `<strong style="color:${color}">${name}</strong> (${speed} SPD, ${ultimates} Ults${eagle ? ", Eagle" : ""}${ddd ? ", DDD" : ""})`;
      legend.appendChild(legendItem);
    });
  }

  form.addEventListener("submit", (e) => {
    e.preventDefault();

    const name = document.getElementById("name").value.trim();
    const speed = parseFloat(document.getElementById("speed").value);
    const ultimates = parseInt(document.getElementById("ultimates").value, 10) || 0;
    const eagle = document.getElementById("eagle").checked;
    const ddd = document.getElementById("ddd").checked;

    if (!name || isNaN(speed) || speed <= 0) return;

    characters.push({
      name,
      speed,
      ultimates,
      eagle,
      ddd,
      color: generateColor(characters.length)
    });

    form.reset();
    render();
  });
});
