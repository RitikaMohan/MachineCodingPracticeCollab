import { useState, useEffect } from "react";

function RandomUserDetails() {
  const [data, setData] = useState(null);
  const URL = "https://randomuser.me/api/";

  const getRandomUser = async () => {
    try {
      let response = await fetch(URL);
      let jsonResponse = await response.json();
      let user = await jsonResponse.results[0];
      setData({ name: user.name, gender: user.gender, email: user.email });
      console.log(data);
    } catch (error) {
      console.log("fetching", error);
    }
  };

  useEffect(() => {
    getRandomUser();
  }, []);

  return (
    <>
      <h1>Random User Details</h1>

      {data ? (
        <div>
          <h2>
            {data.name.title} {data.name.first} {data.name.last}
          </h2>
          <p>{data.gender}</p>
          <p>{data.email}</p>
        </div>
      ) : (
        <p>Loading or no data yet.</p>
      )}
      <button onClick={getRandomUser}>Get New User</button>
    </>
  );
}

export default RandomUserDetails;
