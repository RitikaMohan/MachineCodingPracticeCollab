import React, { useState, useEffect } from "react";

function App() {
  const [query, setQuery] = useState("");
  const [debouncedQuery, setDebouncedQuery] = useState("");
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => {
      setDebouncedQuery(query); 
    }, 500); 
    
    return () => clearTimeout(timer);
  }, [query]);

  useEffect(() => {
    if (!debouncedQuery) {
      setUsers([]);
      return;
    }
  
    const fetchUsers = async () => {
      setLoading(true);
  
      try {
        const res = await fetch(
          `https://jsonplaceholder.typicode.com/users?name_like=${debouncedQuery}`
        );
  
        const data = await res.json();
  
        setUsers(data);
      } catch (err) {
        console.log(err);
        setUsers([]);
      } finally {
        setLoading(false);
      }
    };
  
    fetchUsers();
  }, [debouncedQuery]);

  return (
    <div>
      <h1>Debounced Query</h1>

      <input 
        type="text"
        placeholder="Enter Query"
        value={query} onChange={(e) => setQuery(e.target.value)}
      />

      {loading && <p>Loading...</p>}

      {!loading && users.length === 0 && debouncedQuery && (
        <p>No users found</p>
      )}

      {!loading && users.length > 0 && (
        <ul>
          {users.map((user) => (
            <li key={user.id}>
              {user.name}
            </li>
          ))}
        </ul>
      )}
    </div>
  )
}

export default App;