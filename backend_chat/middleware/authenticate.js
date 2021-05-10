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

// 회원가입 => user 정보 mongoDB 넣기
const checkRegistrationFields = async (req, res, next) => {

    // Here : nickname 으로 옴

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

// 로그인 체크 !!!
const checkLoginFields = async (req, res, next) => {
    let errors = [];
    const user = await User.findOne({ email: req.body.email });
    if (!user) {
        errors.push({ param: 'email', msg: 'Invalid Details Entered' });
    } else {
        // if (req.body.password !== null && !(await user.isValidPassword(req.body.password))) {
        //     errors.push({ param: 'password', msg: 'Invalid Details Entered' });
        // }
    }

    if (errors.length !== 0) {
        res.send({
            errors: createErrorObject(errors)
        });
    } else {
        next();
    }
};

// 채팅 회원 정보 수정 => Here : 되게 버튼 하나 생성하기
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
            .withMessage('Room name is required');
    } else {
        req.check('room_name')
            .isString()
            .isLength({ min: 3, max: 20 })
            .withMessage('Room name must be between 5 and 20 characters');
    }

    if (req.body.password) {
        req.check('password')
            .not()
            .isEmpty()
            .isLength({ min: 5, max: 15 })
            .withMessage('Password should be between 5 and 15 characters');
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
