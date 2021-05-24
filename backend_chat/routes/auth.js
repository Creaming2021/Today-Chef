const _ = require('lodash');
const express = require('express');
const router = express.Router();
const { User } = require('../models/User');
const gravatar = require('gravatar');

/** Middleware */
const {
    checkLoginFields,
} = require('../middleware/authenticate');

/**
 * @description  POST /auth/register
 * @param  {} [checkRegistrationFields]
 * @param  {} request
 * @param  {} response
 * @access public
 */
router.post('/register', (req, res) => {
    let errors = [];

    User.findOne({ email: req.body.email }).then(user => {
        if (user === null) {
            
            const avatar = "https://creaming-bucket-b204.s3.ap-northeast-2.amazonaws.com/profile_image.png";
            const newUser = new User({
                handle: req.body.handle,
                username: req.body.username,
                email: req.body.email,
                image: avatar
            });

            newUser
                .save()
                .then(userData => {
                    res.status(200).send({
                        userData
                    });
                })
                .catch(err => {
                    res.send({
                        err,
                        error: 'Something went wrong, Please check the fields again'
                    });
                });
        }
    });
});

/**
 * @description POST /auth/login
 * @param  {} checkLoginFields
 * @param  {} request
 * @param  {} response
 * @access public
 */
router.post('/login', async (req, res) => {
    const user = await User.findOne({ email: req.body.email });

    if (!user) {
        return res.status(404).send({
            error: 'No Chat User Found'
        });
    }
    
    res.status(200).send({ user });
});

/**
 * @description POST /auth/logout
 * @param  {} request
 * @param  {} response
 * @access public
 */
router.post('/logout', async (req, res) => {
    const user = await User.findOne({ username: req.body.username });

    if (!user) {
        return res.status(404).send({
            error: 'No User Found'
        });
    }

    res.status(200).send({ success: true });
});

module.exports = router;
