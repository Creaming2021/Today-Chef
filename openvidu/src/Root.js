import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import VideoRoomComponent from './components/VideoRoomComponent';

const Root = () => (
    <BrowserRouter>
        <Route exact path="/live" component={VideoRoomComponent}/>
    </BrowserRouter>
);

export default Root;