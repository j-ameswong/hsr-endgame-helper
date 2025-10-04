import { useState } from "react";

function Graph() {
  let speed = 100;
  let cycleType = 0;

  const [av, setAv] = useState(150);

  function gameModeSelected(av: Int) {
    // let selector =
  }

  return (
    <div className="row" id="graph-wrapper">
      <div className="column cl-7" id="av-graph">
        <div id="line"></div>
      </div>
      <div className="column c1-3" id="selector">
        <label htmlFor="cycleCount">Cycle</label>
        <select id="cycleCount">
          <option>0</option>
        </select>
        <label htmlFor="gameMode">Game Mode</label>
        <select id="gameMode" onChange={gameModeSelected}>
          <option value={150}>Memory of Chaos</option>
          <option value={300}>Anomaly Arbitration</option>
        </select>
      </div>
    </div>
  );
}

export default Graph;
