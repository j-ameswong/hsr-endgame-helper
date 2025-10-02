import { useState } from 'react'
import './App.css'
import AddCharacter from './Components/addCharacter'
import Navbar from './Components/navbar'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Navbar />
      <AddCharacter stringParam='Sparkle' />
    </>
  )
}

export default App
