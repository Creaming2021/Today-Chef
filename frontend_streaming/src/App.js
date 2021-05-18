import React from 'react';
import { Route, Switch, useHistory } from 'react-router-dom';
import Dictaphone from './components/Dictaphone';
import VideoRoomComponent from './components/VideoRoomComponent';

class App extends React.Component {
  // const history = useHistory();
  render() {


    return (
      <>      	
        <Switch>
          {/* <Route exact path="/live/:sessionName/:memberId">
            <VideoRoomComponent/>
          </Route> */}
          <Route exact path="/:sessionName/:memberId/:type" component={VideoRoomComponent}/>
          <Route exact path="/dictaphone" component={Dictaphone} />
        </Switch>
        {/* <Dictaphone /> */}
      </>
    )
  }
}

export default App;