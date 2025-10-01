import { useState } from "react";

function TestComponent() {
    let [isPressed, setIsPressed] = useState(false);
    
    function buttonPressed(e) {
        setIsPressed(!isPressed);
    }
    
    return(
        <button onclick={buttonPressed}>L</button>
    );
}