import { useState } from "react";

function SpeedSettings() {
  const [speed, setSpeed] = useState<number>(100);
  const [eagleEnabled, setEagleEnabled] = useState<boolean>(false);
  const [eagleStacks, setEagleStacks] = useState<string>("0");

  const [dddEnabled, setDddEnabled] = useState<boolean>(false);
  const [dddStacks, setDddStacks] = useState<string>("0");
  const [dddRank, setDddRank] = useState<string>("S1");

  return (
    <div className="container p-4 border rounded">
      <h4 className="mb-4">Speed Settings</h4>

      {/* Speed Slider */}
      <div className="mb-4">
        <label htmlFor="speedRange" className="form-label">
          Speed: <strong>{speed.toFixed(1)}</strong>
        </label>
        <div className="d-flex align-items-center">
          <input
            type="number"
            className="form-control w-auto"
            step={0.1}
            min={0}
            max={200}
            value={speed}
            onChange={(e) => setSpeed(parseFloat(e.target.value) || 0)}
          />
        </div>
      </div>

      {/* Eagle 4 Pc */}
      <div className="mb-4">
        <div className="form-check mb-2">
          <input
            className="form-check-input"
            type="checkbox"
            id="eagleCheck"
            checked={eagleEnabled}
            onChange={(e) => setEagleEnabled(e.target.checked)}
          />
          <label className="form-check-label" htmlFor="eagleCheck">
            Eagle 4 Pc
          </label>
        </div>

        {eagleEnabled && (
          <div className="d-flex align-items-center ms-3">
            <label className="me-2">Stacks:</label>
            <select
              className="form-select w-auto"
              value={eagleStacks}
              onChange={(e) => setEagleStacks(e.target.value)}
            >
              {[...Array(7)].map((_, i) => (
                <option key={i} value={i.toString()}>
                  {i}
                </option>
              ))}
              <option value="custom">Custom</option>
            </select>
          </div>
        )}
      </div>

      {/* DDD */}
      <div>
        <div className="form-check mb-2">
          <input
            className="form-check-input"
            type="checkbox"
            id="dddCheck"
            checked={dddEnabled}
            onChange={(e) => setDddEnabled(e.target.checked)}
          />
          <label className="form-check-label" htmlFor="dddCheck">
            DDD
          </label>
        </div>

        {dddEnabled && (
          <div className="ms-3">
            <div className="d-flex align-items-center mb-2">
              <label className="me-2">Stacks:</label>
              <select
                className="form-select w-auto"
                value={dddStacks}
                onChange={(e) => setDddStacks(e.target.value)}
              >
                {[...Array(7)].map((_, i) => (
                  <option key={i} value={i.toString()}>
                    {i}
                  </option>
                ))}
                <option value="custom">Custom</option>
              </select>
            </div>

            <div className="d-flex align-items-center">
              <label className="me-2">Rank:</label>
              <select
                className="form-select w-auto"
                value={dddRank}
                onChange={(e) => setDddRank(e.target.value)}
              >
                {["S1", "S2", "S3", "S4", "S5"].map((rank) => (
                  <option key={rank} value={rank}>
                    {rank}
                  </option>
                ))}
              </select>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default SpeedSettings;
