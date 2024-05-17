import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import CustomerList from "./Components/CustomerList.component";
import CustomerPage from "./Pages/CustomerPage.component";




function App() {

  return (
      <Router>
        <Switch>
          <Route path="/customer/:id">
            <CustomerPage />
          </Route>
          <Route path="/">
            <CustomerList />
          </Route>
        </Switch>
      </Router>
  );
}

export default App;
