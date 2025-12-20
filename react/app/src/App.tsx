import "./App.css";
import { Routes, Route } from "react-router";

function App() {
  return (
    <>
      {/* <Navbar /> */}
      <Routes>
        <Route path="/" element="./pages/calc.tsx" />
      </Routes>
    </>
  );
}

export default App;
