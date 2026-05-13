import { useState } from 'react';

function App() {
  let [value, setValue] = useState(0);

  // let value = 0;
  const increaseValue = () => {
    value = value + 1;
    setValue(value);
    console.log(value);
  }
  const decreaseValue = () => {
    if(value == 0) {
      console.log("Value is 0, can not decrease more!")
    } else {
      value = value - 1;
      setValue(value);
      console.log(value);
    }
    
  }

  return (
    <>
    <h1>Counter project</h1>
    <h3>{value}</h3>
    <button onClick={increaseValue}>Increase Value {value}</button>
    <br></br>
    <button onClick={decreaseValue}>Decrease value {value}</button>
    </>
  )
}

export default App;
