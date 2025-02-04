import React, { useEffect, useState } from "react";

function App() {
  const [todos, setTodos] = useState([]);

  useEffect(() => {
    // Fetch TODOs from the API
    fetch("/api/todos")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        setTodos(data.todos); // Assuming the response structure is { todos: [...] }
      })
      .catch((error) => {
        console.error("There was an error fetching the todos!", error);
      });
  }, []);

  return (
    <div className="min-h-screen bg-gray-100">
      <nav className="bg-blue-600 p-4 shadow-md">
        <div className="container mx-auto">
          <h1 className="text-white text-xl font-bold">Todo App</h1>
        </div>
      </nav>

      <div className="container mx-auto p-4">
        <h2 className="text-2xl font-semibold mb-4">Your TODO List</h2>

        {todos.length === 0 ? (
          <p className="text-gray-600">No TODOs available.</p>
        ) : (
          <ul className="bg-white rounded-lg shadow divide-y divide-gray-200">
            {todos.map((todo) => (
              <li key={todo.id} className="p-4 hover:bg-gray-50">
                <h3 className="text-lg font-medium">{todo.title}</h3>
                <p className="text-gray-600">{todo.description}</p>
                <span
                  className={`inline-block px-2 py-1 mt-2 text-sm font-semibold rounded 
                  ${
                    todo.status === "completed"
                      ? "bg-green-100 text-green-800"
                      : "bg-yellow-100 text-yellow-800"
                  }`}
                >
                  {todo.status.charAt(0).toUpperCase() + todo.status.slice(1)}
                </span>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}

export default App;
