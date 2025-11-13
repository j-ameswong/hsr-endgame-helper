import { useState } from "react";

interface AddCharacterProps {
  stringParam: string;
}

function AddCharacter({ stringParam }: AddCharacterProps) {
  const [testData, changeTestData] = useState("AAA");

  function buttonPressed() {
    testData === "AAA" ? changeTestData(stringParam) : changeTestData("AAA");
  }

  return (
    <div className="container">
      <button onClick={buttonPressed}>{testData}</button>
    </div>
  );
}

export default AddCharacter;
