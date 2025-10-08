import {useState} from "react";

function Graph() {
    let speed = 100;
    let cycleType = 0;

    const [av, setAv] = useState(150);

    return (
        <div className="row border" id="graph-wrapper">
            <div className="col-8" id="av-graph">
                <div className="line col"></div>
            </div>
            <div className="col-4" id="selector">
                <div className="col">
                    <label htmlFor="cycleCount">Cycle</label>
                    <select id="cycleCount">
                        <option>0</option>
                    </select>
                </div>

                <div className="col">
                    <label htmlFor="gameMode">Game Mode</label>
                    <select id="gameMode" onChange={(e) => setAv(Number(e.target.value))}>
                        <option value={150}>Memory of Chaos</option>
                        <option value={300}>Anomaly Arbitration</option>
                    </select>
                </div>

                <div className={"col"}><label htmlFor="current-av">Current AV: </label>
                    <input id="current-av" value={av}></input></div>
            </div>
        </div>
    );
}

export default Graph;
