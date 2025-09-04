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
        {"S" + number}
      </option>
    ))}
  </select>
  );
}

function CreateCharacter() {
  return(
    <div className="App-character">
      <form>
        <div class="row">
          <label for="characterName">Character</label>
          <input type="text" id="characterName" name="characterName"></input>
        </div>

        <div class="row">
          <label for="characterSpeed">Spd</label>
          <input type="number" step="0.1" id="characterSpeed" name="characterSpeed"></input>
        </div>

        <div class="row">
          <label for="dddLevel">DDD</label>
          <div class="wrapper">
            <Dropdown id="dddLevel" startNum={1} stopNum={5}/>
            <input type="checkbox" id="dddEquipped" name="dddEquipped"></input>
          </div>
        </div>

        <div class="row">
          <label for="eagleEquipped">Eagle Set</label>
          <input type="checkbox" id="eagleEquipped" name="eagleEquipped"></input>
        </div>

        <div id="submitWrapper" class="wrapper"><button type="submit">Add</button></div>
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

      <div class="App-content">
        <CreateCharacter />

        <div className="App-graph">
          Placeholder Graph
        </div>
      </div>
    </div>
  );
}

export default App;
