import { useState, useEffect } from "react";

export default function DogsImages() {
  let [data, setData] = useState({});

  const URL = "https://dog.ceo/api/breeds/image/random";

  const getDogImage = async () => {
    let response = await fetch(URL);
    let jsonResponse = await response.json();
    setData({ message: jsonResponse.message });
  };

  useEffect(() => {
    async function getFirstDogImage() {
      let response = await fetch(URL);
      let jsonResponse = await response.json();
      setData({ message: jsonResponse.message });
    }
    getFirstDogImage();
  }, []);

  return (
    <>
      <h1>Dogs Breed</h1>
      <img src={data.message} height="500px"/>
      <br></br>
      <button onClick={getDogImage}>Get Dog Image</button>
    </>
  );
}
