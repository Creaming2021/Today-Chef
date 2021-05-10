const express = require('express');
const router = express.Router();

const { User } = require('../models/User');

const { checkEditProfileFields } = require('../middleware/authenticate');

/**
 * @description  GET /chat/user/users
 * @param  {false} session
 * @param  {Object} request
 * @param  {Object} response
 * @access private
 */

router.get('/users', async (req, res) => {
    const users = await User.find({}, 'image email username').exec();

    if (users) {
        return res
            .status(200)
            .json(users)
            .end();
    } else {
        return res.status(404).json({ error: 'No Users Found' });
    }
});

/**
 * @description PUT /chat/user/current
 * @param  {String} id
 * @param  {false} session
 * @param  {Object} request
 * @param  {Object} response
 */
router.put(
    '/current',
    [checkEditProfileFields],
    async (req, res) => {
        const updateFields = {};

        for (let key of Object.keys(req.body)) {
            if (req.body[key] !== null) {
                updateFields[key] = req.body[key];
            }
        }

        User.findOneAndUpdate({ _id: req.user.id }, { $set: updateFields }, { new: true })
            .select('-password')
            .then(doc => res.json({ success: true, user: doc }))
            .catch(err => res.json({ error: err }));
    }
);

/**
 * @description GET chat/user/current
 * @param  {String} id
 * @param  {false} session
 * @param  {Object} request
 * @param  {Object} response
 */
router.get('/current', (req, res) => {
    res.json(req.user);
});

/**
 * @description DELETE chat/user/current
 * @param  {String} id
 * @param  {false} session
 * @param  {Object} request
 * @param  {Object} response
 */
router.delete('/current', async (req, res) => {
    /** Delete the user */
    await User.findOneAndDelete({ _id: req.user.id });

    res.json({ success: true });
});

module.exports = router;
