import React, { useState } from "react";

function App() {
  // State
  const [tasks, setTasks] = useState([]);
  const [input, setInput] = useState("");

  // Add Task
  const handleAdd = () => {
    // Prevent empty task
    if (!input.trim()) return;

    const newTask = {
      id: Date.now(),
      text: input,
      completed: false,
    };

    setTasks([...tasks, newTask]);

    setInput("");
  };

  // Delete Task
  const handleDelete = (id) => {
    const updatedTasks = tasks.filter(
      (task) => task.id !== id
    );

    setTasks(updatedTasks);
  };

  // Toggle Complete
  const handleToggle = (id) => {
    const updatedTasks = tasks.map((task) => {
      if (task.id === id) {
        return {
          ...task,
          completed: !task.completed,
        };
      }

      return task;
    });

    setTasks(updatedTasks);
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Todo App</h1>

      {/* Input */}
      <input
        type="text"
        placeholder="Enter task"
        value={input}
        onChange={(e) => setInput(e.target.value)}
      />

      {/* Add Button */}
      <button onClick={handleAdd}>
        Add
      </button>

      {/* Task List */}
      {tasks.map((task) => (
        <div
          key={task.id}
          style={{
            display: "flex",
            gap: "10px",
            marginTop: "10px",
            alignItems: "center",
          }}
        >
          {/* Checkbox */}
          <input
            type="checkbox"
            checked={task.completed}
            onChange={() => handleToggle(task.id)}
          />

          {/* Task Text */}
          <span
            style={{
              textDecoration: task.completed
                ? "line-through"
                : "none",
            }}
          >
            {task.text}
          </span>

          {/* Delete Button */}
          <button
            onClick={() => handleDelete(task.id)}
          >
            Delete
          </button>
        </div>
      ))}
    </div>
  );
}

export default App;
