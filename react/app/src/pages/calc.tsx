import React, { useState, useEffect, useRef } from "react";
import "./calc.css";

interface Turn {
  actionValue: number;
  actionAdvance: number;
  actionType: string; // "NORMAL" | "ADVANCE"
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
  // --- State ---
  const [turns, setTurns] = useState<Turn[]>([]);
  const [speed, setSpeed] = useState<number>(133.4);
  const [cycleOption, setCycleOption] = useState<string>("0");

  // User input points
  const [actionAdvancePoints, setActionAdvancePoints] = useState<Turn[]>([]);

  // Ghost State for Drag Hover
  const [dragHoverAV, setDragHoverAV] = useState<number | null>(null);

  const [breakpoints, setBreakpoints] = useState<Breakpoint[]>([]);
  const [numDDD, setNumDDD] = useState<number>(0);
  const [numEagle, setNumEagle] = useState<number>(0);

  const [loading, setLoading] = useState<boolean>(false);

  // Filters
  const [showNormal, setShowNormal] = useState<boolean>(true);
  const [showAdvance, setShowAdvance] = useState<boolean>(true);

  const MAX_AV = CYCLE_AV_MAP[cycleOption];
  const barRef = useRef<HTMLDivElement>(null);

  // --- Fetch Data ---
  const fetchData = async () => {
    setLoading(true);
    try {
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
  }, [speed, cycleOption, actionAdvancePoints]);

  // --- Handlers ---
  const handleSpeedChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSpeed(Number(e.target.value));
  };

  const handleCycleChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setCycleOption(e.target.value);
  };

  // --- Helper: Calculate AV from Mouse Position ---
  const calculateAVFromEvent = (clientX: number): number => {
    if (!barRef.current) return 0;
    const rect = barRef.current.getBoundingClientRect();
    const offsetX = clientX - rect.left;
    const validX = Math.max(0, Math.min(offsetX, rect.width));
    const percentage = validX / rect.width;
    return percentage * MAX_AV;
  };

  // --- Drag & Drop Handlers ---
  const handleDragStart = (e: React.DragEvent) => {
    e.dataTransfer.setData("type", "advance_token");
    e.dataTransfer.effectAllowed = "copy";
  };

  const handleDragOver = (e: React.DragEvent) => {
    e.preventDefault();
    e.dataTransfer.dropEffect = "copy";

    // Calculate and set ghost state
    const currentAV = calculateAVFromEvent(e.clientX);
    setDragHoverAV(currentAV);
  };

  const handleDragLeave = (e: React.DragEvent) => {
    // Only clear if we actually left the container (not just entered a child element)
    if (barRef.current && !barRef.current.contains(e.relatedTarget as Node)) {
      setDragHoverAV(null);
    }
  };

  const handleDrop = (e: React.DragEvent) => {
    e.preventDefault();
    setDragHoverAV(null); // Clear ghost

    if (!barRef.current) return;

    const type = e.dataTransfer.getData("type");
    if (type !== "advance_token") return;

    const droppedAV = calculateAVFromEvent(e.clientX);

    const newPoint: Turn = {
      actionValue: Number(droppedAV.toFixed(1)),
      actionAdvance: 0.24,
      actionType: "ADVANCE",
    };

    setActionAdvancePoints((prev) => [...prev, newPoint]);
  };

  const removeAdvancePoint = (indexToRemove: number) => {
    setActionAdvancePoints((prev) =>
      prev.filter((_, idx) => idx !== indexToRemove),
    );
  };

  // --- Filtering Logic ---
  const filteredTurns = turns.filter((t) => {
    if (t.actionType === "NORMAL") return showNormal;
    if (t.actionType !== "NORMAL") return showAdvance;
    return true;
  });

  return (
    <div className="app-background">
      <div className="sim-container">
        <div className="title-container">
          <h1 className="header">HSR Speed Simulator</h1>
          <p>
            Drag "Advance" tokens onto the timeline to simulate AV adjustments.
          </p>
        </div>

        {/* Controls */}
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

        {/* Toolbox */}
        <div className="control-panel toolbox-panel">
          <div className="toolbox-item">
            <span className="toolbox-label">Drag me:</span>
            <div
              className="draggable-token"
              draggable
              onDragStart={handleDragStart}
            >
              + Advance Action
            </div>
          </div>

          <div className="filter-group">
            <span className="toolbox-label">Show:</span>
            <label className="checkbox-label">
              <input
                type="checkbox"
                checked={showNormal}
                onChange={(e) => setShowNormal(e.target.checked)}
              />{" "}
              Normal
            </label>
            <label className="checkbox-label">
              <input
                type="checkbox"
                checked={showAdvance}
                onChange={(e) => setShowAdvance(e.target.checked)}
              />{" "}
              Advance
            </label>
          </div>
        </div>

        {/* Visualization */}
        <div className="section">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <div className="bar-container">
              <h3 className="header">Action Timeline (0 - {MAX_AV} AV)</h3>

              <div
                className="bar-background drop-target"
                ref={barRef}
                onDragOver={handleDragOver}
                onDragLeave={handleDragLeave}
                onDrop={handleDrop}
              >
                {/* 1. GHOST MARKER (Preview) */}
                {dragHoverAV !== null && (
                  <div
                    className="marker marker-ghost"
                    style={{ left: `${(dragHoverAV / MAX_AV) * 100}%` }}
                  >
                    <div className="marker-tooltip tooltip-ghost">
                      {dragHoverAV.toFixed(1)} AV
                    </div>
                  </div>
                )}

                {/* 2. User Inputs */}
                {actionAdvancePoints.map((pt, idx) => {
                  const leftPos = (pt.actionValue / MAX_AV) * 100;
                  if (leftPos > 100) return null;

                  return (
                    <div
                      key={`input-${idx}`}
                      className="marker marker-input"
                      style={{ left: `${leftPos}%` }}
                      onClick={() => removeAdvancePoint(idx)}
                      title={`Remove Input: ${pt.actionValue} AV`}
                    >
                      <div className="marker-tooltip tooltip-input">
                        {pt.actionValue.toFixed(1)}
                      </div>
                    </div>
                  );
                })}

                {/* 3. Sim Outputs */}
                {filteredTurns.map((turn, idx) => {
                  const leftPos = (turn.actionValue / MAX_AV) * 100;
                  if (leftPos > 100) return null;

                  const isAdvance = turn.actionType !== "NORMAL";
                  const markerClass = isAdvance
                    ? "marker-advance"
                    : "marker-normal";

                  return (
                    <div
                      key={`turn-${idx}`}
                      className={`marker ${markerClass}`}
                      style={{ left: `${leftPos}%` }}
                    >
                      <div className="marker-tooltip">
                        {turn.actionValue.toFixed(1)}
                      </div>
                    </div>
                  );
                })}
              </div>

              {/* Turns Table (omitted for brevity, same as before) */}
              <div className="turns-container">
                <h4 className="turn-header">Resulting Turn Order</h4>
                <table>
                  <thead>
                    <tr>
                      <th>#</th>
                      <th>Action Type</th>
                      <th>AV</th>
                    </tr>
                  </thead>
                  <tbody>
                    {turns
                      .concat(actionAdvancePoints)
                      .sort((a, b) => {
                        if (a.actionValue !== b.actionValue) {
                          return a.actionValue - b.actionValue;
                        }

                        if (
                          a.actionType === "ADVANCE" &&
                          b.actionType !== "ADVANCE"
                        ) {
                          return -1;
                        }

                        if (
                          a.actionType !== "ADVANCE" &&
                          b.actionType === "ADVANCE"
                        ) {
                          return 1;
                        }

                        return 0;
                      })
                      .map((turn, idx) => (
                        <tr key={idx} className="turn">
                          <td>{idx + 1}</td>
                          <td
                            style={{
                              color:
                                turn.actionType !== "NORMAL"
                                  ? "#ff9f43"
                                  : "inherit",
                              fontWeight:
                                turn.actionType !== "NORMAL"
                                  ? "bold"
                                  : "normal",
                            }}
                          >
                            {turn.actionType}
                          </td>
                          <td>{turn.actionValue.toFixed(1)}</td>
                        </tr>
                      ))}
                  </tbody>
                </table>
              </div>
            </div>
          )}
        </div>

        {/* --- Breakpoints Data Table --- */}
        <div className="section">
          <div className="table-container">
            <h3 className="header">Speed Breakpoints</h3>
            <table className="sim-table">
              <thead>
                <tr>
                  <th className="sim-th">Actions</th>
                  <th className="sim-th">Threshold</th>
                  <th className="sim-th">Status</th>
                </tr>
              </thead>
              <tbody>
                {breakpoints.slice(1).map((bp) => {
                  const achieved = speed >= bp.speed;
                  return (
                    <tr
                      key={bp.numActions}
                      className={`sim-tr ${achieved ? "active" : "inactive"}`}
                    >
                      <td className="sim-td">{bp.numActions} Actions</td>
                      <td className="sim-td">
                        {`${breakpoints[breakpoints.indexOf(bp) - 1].speed.toFixed(1)}
                            - ${bp.speed.toFixed(1)} SPD`}
                      </td>
                      <td className="sim-td">
                        {achieved
                          ? <div className="status"> {`${(bp.speed - speed).toFixed(1)}`} </div>
                          : <div className="status">{`Need + ${(bp.speed - speed).toFixed(1)}`}</div>}
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CombatSim;
