const express = require('express'); // importing express
const app = express(); // creating app

app.use(express.json());

app.get('/', (req, res) => {
    res.json({ message: "Server is Running" }); // Lets Express Reads JSON Request Bodies
})

const PORT = process.env.PORT || 5000; // defining port
app.listen(PORT, () => {
    console.log(`Server is running on ${PORT}`);
})