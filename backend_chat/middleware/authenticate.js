const { User } = require('../models/User');

const createErrorObject = errors => {
    const errorObject = [];
    errors.forEach(error => {
        let err = {
            [error.param]: error.msg
        };
        errorObject.push(err);
    });

    return errorObject;
};

const checkRegistrationFields = async (req, res, next) => {

    req.check('email').isEmail();
    req.check('username').isString();

    let errors = req.validationErrors() || [];

    const user = await User.findOne({ username: req.body.username });

    if (user) {
        errors.push({ param: 'username', msg: 'Nickname already taken' });
    }

    if (errors.length > 0) {
        res.send({
            errors: createErrorObject(errors)
        });
    } else {
        next();
    }
};

const checkLoginFields = async (req, res, next) => {
    let errors = [];
    const user = await User.findOne({ email: req.body.email });
    if (!user) {
        errors.push({ param: 'email', msg: 'Invalid Details Entered' });
    }

    if (errors.length !== 0) {
        res.send({
            errors: createErrorObject(errors)
        });
    } else {
        next();
    }
};

const checkEditProfileFields = async (req, res, next) => {
    let errors = [];

    if (req.body.email) {
        if (await User.findOne({ email: req.body.email })) {
            errors.push({ param: 'email', msg: 'Email is already taken' });
        }
    }

    if (req.body.handle) {
        if (await User.findOne({ handle: req.body.handle })) {
            errors.push({ param: 'handle', msg: 'Handle is already taken' });
        }
    }
    if (errors.length !== 0) {
        res.send({
            errors: createErrorObject(errors)
        });
    } else {
        next();
    }
};

const checkCreateRoomFields = async (req, res, next) => {
    if (!req.body.room_name) {
        req.check('room_name')
            .not()
            .isEmpty()
            .withMessage('채팅방 이름을 입력해주세요.');
    } else {
        req.check('room_name')
            .isString()
            .isLength({ min: 1, max: 7 })
            .withMessage('채팅방 이름은 1자 이상 7자 이하입니다.');
    }

    if (req.body.password) {
        req.check('password')
            .not()
            .isEmpty()
            .isLength({ min: 5, max: 10 })
            .withMessage('비밀번호는 5자 이상 10자 이하입니다.');
    }

    const errors = req.validationErrors();

    if (errors) {
        res.send({
            errors: createErrorObject(errors)
        });
    } else {
        next();
    }
};

module.exports = {
    checkLoginFields,
    checkRegistrationFields,
    checkEditProfileFields,
    checkCreateRoomFields,
    createErrorObject
};
