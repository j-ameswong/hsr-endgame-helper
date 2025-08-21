import './App.css';

function Dropdown({startNum, stopNum}) {
  const options = [];
  for (let i = startNum; i <= stopNum; i++) {
    options.push(i);
  };

  return(
  <select id="dddLevel" name="dddLevel">
    <option value="">Choose...</option>
    {options.map((number) => (
      <option key={number} value={number}>
        {number}
      </option>
    ))}
  </select>
  );
}

function CreateCharacter() {
  return(
    <div className="App-character">
      <form>
        <label for="characterName">Character</label>
        <input type="text" id="characterName" name="characterName"></input>

        <label for="dddLevel">DDD Level</label>
        <Dropdown startNum={1} stopNum={5}/>

        <label for="dddProcs">Number of Ultimates</label>
        <input type="checkbox" id="dddProcs" name="dddProcs"></input>
      </form>
    </div>
  );
}

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <p className="App-link">HSR Endgame Helper </p>
      </header>

      <div className="App-graph">
        Placeholder Graph
      </div>

      <form>
        <input></input>
      </form>

      <CreateCharacter />
    </div>
  );
}

export default App;
