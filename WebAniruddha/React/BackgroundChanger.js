import { useState } from "react";

function App() {

  const [color, setColor] = useState("black");

  return (
    <>
      <div
        className="w-full h-screen duration-300"
        style={{backgroundColor: color}}
      >
        <div className="fixed flex flex-wrap justify-center bottom-12 inset-x-0 px-2">
          <div className="flex flex-wrap justify-center gap-3 bg-white px-3 py-2 rounded shadow-2xl ">
            <button
              onClick={color => setColor("red")}
              className="rounded px-2 py-1 outline-none shadow-md text-white"
              style={{backgroundColor: "red"}}
            >
              Red
            </button>
            <button
              onClick={color => setColor("green")}
              className="rounded px-2 py-1 outline-none shadow-md text-white"
              style={{backgroundColor: "green"}}
            >
              Green
            </button>
            <button
              onClick={color => setColor("blue")}
              className="rounded px-2 py-1 outline-none shadow-md text-white"
              style={{backgroundColor: "blue"}}
            >
              Blue
            </button>
            <button
              onClick={color => setColor("olive")}
              className="rounded px-2 py-1 outline-none shadow-md text-white"
              style={{backgroundColor: "olive"}}
            >
              Olive
            </button>
            <button
              onClick={color => setColor("gray")}
              className="rounded px-2 py-1 outline-none shadow-md text-white"
              style={{backgroundColor: "gray"}}
            >
              Gray
            </button>
            <button
              onClick={color => setColor("yellow")}
              className="rounded px-2 py-1 outline-none shadow-md text-gray"
              style={{backgroundColor: "yellow"}}
            >
              Yellow
            </button>
            <button
              onClick={color => setColor("purple")}
              className="rounded px-2 py-1 outline-none shadow-md text-white"
              style={{backgroundColor: "purple"}}
            >
              Purple
            </button>
            <button
              onClick={color => setColor("pink")}
              className="rounded px-2 py-1 outline-none shadow-md text-gray"
              style={{backgroundColor: "pink"}}
            >
              Pink
            </button>
            <button
              onClick={color => setColor("lavender")}
              className="rounded px-2 py-1 outline-none shadow-md text-gray"
              style={{backgroundColor: "lavender"}}
            >
              Lavender
            </button>
            <button
              onClick={color => setColor("white")}
              className="rounded px-2 py-1 outline-none shadow-md text-black"
              style={{backgroundColor: "white"}}
            >
              White
            </button>
            <button
              onClick={color => setColor("black")}
              className="rounded px-2 py-1 outline-none shadow-md text-white"
              style={{backgroundColor: "black"}}
            >
              Black
            </button>
          </div>
        </div>
      </div>
    </>
  )
}

export default App;
