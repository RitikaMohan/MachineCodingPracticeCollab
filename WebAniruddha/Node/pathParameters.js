const express = require("express");
const app = express();

const PORT = process.env.PORT || 5000;

app.listen(PORT, () => {
    console.log(`Server is running on ${PORT}`);
})

app.get("/", (req, res) => {
    res.send("Hello, I am Root");
})

app.get("/:username", (req, res) => {
    console.log(req.params); // it will console the path parameter, here whatever username would be there in URL
    // If My URL is localhost:5000/aniruddha
    // it will print { username: 'aniruddha' }
    res.send(`Path Parameters`)
})

app.get("/:username/:id", (req, res) => {
    let { username, id } = req.params; // here path parameters are username and id
    res.send(`Welcome to the @${username}`); // Welcome to the @aniruddha 
})