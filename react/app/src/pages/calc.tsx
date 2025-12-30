import React, { useState, useEffect } from "react";
import "./calc.css";

interface Turn {
  actionValue: number;
  actionType: string;
}

interface ActionResponse {
  unitName: string;
  turns: Turn[];
}

interface Breakpoint {
  numActions: number;
  speed: number;
}

interface BreakpointResponse {
  breakpoints: Breakpoint[];
}

const CYCLE_AV_MAP: Record<string, number> = {
  "0": 150,
  "1": 250,
  "2": 350,
  "3": 450,
};

const CombatSim: React.FC = () => {
  // State
  const [turns, setTurns] = useState<Turn[]>([]);
  const [speed, setSpeed] = useState<number>(133.4);
  const [cycleOption, setCycleOption] = useState<string>("0");
  const [actionAdvancePoints, setActionAdvancePoints] = useState<Turn[]>([]);

  const [breakpoints, setBreakpoints] = useState<Breakpoint[]>([]);
  const [numDDD, setNumDDD] = useState<number>(0);
  const [numEagle, setNumEagle] = useState<number>(0);

  const [loading, setLoading] = useState<boolean>(false);

  const MAX_AV = CYCLE_AV_MAP[cycleOption];

  // Fetch Data
  const fetchData = async () => {
    setLoading(true);
    try {
      // 1. Get Turns
      const turnRes = await fetch("/api/sim/calculate-actions", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          unitName: "TestUnit",
          speed: speed,
          maxAv: MAX_AV,
          actionAdvancePoints: actionAdvancePoints,
        }),
      });

      if (!turnRes.ok) throw new Error("Failed to fetch actions");
      const turnData: ActionResponse = await turnRes.json();
      setTurns(turnData.turns);

      // 2. Get Breakpoints
      const breakRes = await fetch("/api/sim/calculate-breakpoints", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          speed: speed,
          maxAv: MAX_AV,
          numActions: 6,
          numDDD: numDDD,
          numEagle: numEagle,
        }),
      });

      if (!breakRes.ok) throw new Error("Failed to fetch breakpoints");
      const breakData: BreakpointResponse = await breakRes.json();
      setBreakpoints(breakData.breakpoints);
    } catch (err) {
      console.error("Failed to fetch simulation data", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, [speed, cycleOption]);

  // Handlers
  const handleSpeedChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSpeed(Number(e.target.value));
  };

  const handleCycleChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setCycleOption(e.target.value);
  };

  return (
    <div className="app-background">
      <div className="sim-container">
        <div className="title-container">
          <h1 className="header">HSR Speed Simulator</h1>
          <p>
            An interactive timeline for visualization of turn order and speed
            breakpoints
          </p>
        </div>

        {/* --- Controls --- */}
        <div className="control-panel">
          <div className="input-group">
            <label className="input-label">Character Speed</label>
            <input
              type="number"
              value={speed}
              step={0.1}
              onChange={handleSpeedChange}
              className="sim-input"
            />
          </div>

          <div className="input-group">
            <label className="input-label">Cycle Limit</label>
            <select
              value={cycleOption}
              onChange={handleCycleChange}
              className="sim-select"
            >
              <option value="0">Cycle 0 (150 AV)</option>
              <option value="1">Cycle 1 (250 AV)</option>
              <option value="2">Cycle 2 (350 AV)</option>
              <option value="3">Cycle 3 (450 AV)</option>
            </select>
          </div>
        </div>

        {/* --- Action Bar Visualization --- */}
        <div className="section">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <div className="bar-container">
              <h3 className="header">Action Timeline (0 - {MAX_AV} AV)</h3>
              <div className="bar-background">
                {turns.map((turn) => {
                  const leftPos = (turn.actionValue / MAX_AV) * 100;
                  if (leftPos > 100) return null;

                  return (
                    <div
                      key={turns.indexOf(turn)}
                      className="marker"
                      // Dynamic style is kept inline for the position
                      style={{ left: `${leftPos}%` }}
                      title={`Action #${turns.indexOf(turn) + 1} at ${turn.actionValue} AV`}
                    >
                      <div className="marker-tooltip">
                        {turn.actionValue.toFixed(1)} AV
                      </div>
                    </div>
                  );
                })}
              </div>

              <div className="turns-container">
                <h4 className="turn-header">Turns</h4>
                <table>
                  <thead>
                    <tr>
                      <th>#</th>
                      <th>Action</th>
                      <th>AV</th>
                    </tr>
                  </thead>

                  <tbody>
                    {turns.map((turn) => {
                      return (
                        <tr key={turns.indexOf(turn)} className="turn">
                          <td>{turns.indexOf(turn)}</td>
                          <td>{turn.actionType}</td>
                          <td>{turn.actionValue}</td>
                      </tr>
                      );
                    })}
                  </tbody>
                </table>
              </div>
            </div>
          )}
        </div>

        {/* --- Breakpoints Data Table --- */}
        <div className="section">
          <h3 className="header">Speed Breakpoints for Selected Cycle</h3>
          <table className="sim-table">
            <thead>
              <tr>
                <th className="sim-th">Actions Possible</th>
                <th className="sim-th">Speed Threshold</th>
                <th className="sim-th">Status</th>
              </tr>
            </thead>
            <tbody>
              {breakpoints.map((bp) => {
                const achieved = speed >= bp.speed;
                return (
                  <tr
                    key={bp.numActions}
                    className={`sim-tr ${achieved ? "active" : ""}`}
                  >
                    <td className="sim-td">{bp.numActions} Actions</td>
                    <td className="sim-td">{bp.speed.toFixed(1)} SPD</td>
                    <td className="sim-td">
                      {achieved
                        ? "Achieved"
                        : `Need +${Number(bp.speed - speed).toFixed()}`}
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default CombatSim;
