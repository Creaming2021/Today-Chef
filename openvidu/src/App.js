import React from 'react';
import { Route, Switch, useHistory } from 'react-router-dom';
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
          <Route exact path="/live/:sessionName/:memberId" component={VideoRoomComponent}/>
        </Switch>
      </>
    )
  }
}

export default App;