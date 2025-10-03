import { useState } from "react";
import "./App.css";
import AddCharacter from "./Components/addCharacter";
import Navbar from "./Components/navbar";
import Graph from "./Components/graph";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div className="container">
        <Navbar />
        {/* <AddCharacter stringParam="Sparkle" />*/}
      </div>
      <Graph></Graph>
    </>
  );
}

export default App;
