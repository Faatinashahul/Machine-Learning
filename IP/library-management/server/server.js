const express = require("express");
const cors = require("cors");

const app = express();

app.use(cors());
app.use(express.json());

let books = [
  { id: 1, title: "React Basics", author: "John Doe", year: 2022 },
  { id: 2, title: "Node Guide", author: "Alice Smith", year: 2023 }
];

app.get("/books", (req, res) => {
  res.json(books);
});

app.post("/books", (req, res) => {
  const newBook = {
    id: books.length + 1,
    ...req.body
  };

  books.push(newBook);
  res.json(newBook);
});

app.put("/books/:id", (req, res) => {
  const id = parseInt(req.params.id);

  books = books.map(book =>
    book.id === id ? { ...book, ...req.body } : book
  );

  res.send("Book updated");
});

// DELETE book
app.delete("/books/:id", (req, res) => {
  const id = parseInt(req.params.id);

  books = books.filter(book => book.id !== id);

  res.send("Book deleted");
});

app.listen(3001, () => {
  console.log("Server running on port 3001");
});