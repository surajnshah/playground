const express = require('express')
const bodyParser = require('body-parser')
const app = express()
const mongoose = require('mongoose');
const url = 'mongodb://localhost:27017/user';

const User = require('./model/user');

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended : false}))

app.post('/api/user/login', (req, res) => {
    mongoose.connect(url, { useNewUrlParser: true }, function(err){
    if(err) throw err;
    User.find({
        username : req.body.username, password : req.body.password
    }, function(err, user){
        if(err) throw err;
        if(user.length === 1){
            return res.status(200).json({
                status: 'success',
                data: user
            })
        } else {
            return res.status(200).json({
                status: 'fail',
                message: 'Login Failed'
            })
        }

    })
});
})

app.listen(3000, () => console.log('Blog server running on port 3000!'))