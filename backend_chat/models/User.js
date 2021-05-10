const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const UserSchema = new Schema(
    {
        handle: {
            type: String,
            required: true,
            trim: true,
            unique: true
        },
        username: {
            type: String,
            trim: true,
            unique: true,
        },
        email: {
            type: String,
            trim: true,
            unique: true,
            sparse: true
        },
        image: {
            type: String,
            default: null
        },
    },
    {
        timestamps: {
            createdAt: 'created_at',
            updatedAt: 'updated_at'
        }
    }
);

const User = mongoose.model('User', UserSchema);

module.exports = { User };
