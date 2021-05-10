const _ = require('lodash');
const express = require('express');
const router = express.Router();
const { User } = require('../models/User');
const gravatar = require('gravatar');

/** Middleware */
const {
    checkRegistrationFields,
    checkLoginFields,
} = require('../middleware/authenticate');

/**
 * @description  POST /register
 * @param  {} [checkRegistrationFields]
 * @param  {} request
 * @param  {} response
 * @access public
 */
router.post('/register', [checkRegistrationFields], (req, res) => {
    let errors = [];

    User.findOne({ email: req.body.email }).then(user => {
        if (user === null) {
            /** Assign Gravatar */
            const avatar = gravatar.url(req.body.email, {
                s: '220',
                r: 'pg',
                d: 'identicon'
            });

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
                        // Here : 회원가입이므로 안보내도됨.
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
 * @description POST /login
 * @param  {} checkLoginFields
 * @param  {} request
 * @param  {} response
 * @access public
 */
// 로그인 => 채팅 자동 로그인
router.post('/login', checkLoginFields, async (req, res) => {
    const user = await User.findOne({ email: req.body.email });

    if (!user) {
        return res.status(404).send({
            error: 'No Chat User Found'
        });
    }
    
    res.status(200).send({ user });
});

/**
 * @description POST /logout
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
