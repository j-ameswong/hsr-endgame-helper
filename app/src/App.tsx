import { useState } from "react";
import "./App.css";
import AddCharacter from "./Components/addCharacter";
import Navbar from "./Components/navbar";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div className="container">
        <Navbar />
        <AddCharacter stringParam="Sparkle" />
      </div>
    </>
  );
}

export default App;
