import './App.css';
import Navbar from './components/Navbar';
import Home from './components/Home';
import LogIn from './components/LogIn';
import Registration from './components/Registration';
import AdminPage from './components/AdminPage';
import ProductCategory from './components/adminPages/ProductCategory'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { role } from "./services/auth-service";
import { React, useState, useEffect} from 'react';


function App() {

  const [loged, setLoged] = useState({});
  const [user, setUser] = useState({});
  var component = <Home/>;

  useEffect( () => {
    if(localStorage.getItem('accessToken') !== null)
        getUserRole();
}, []);

const getUserRole = () => {
    role().then(user => {
        setLoged(true);
        setUser(user.role.authority);
    });
}

  return (
    <div>
      <Router>
      <Navbar/>
      <Routes>
          <Route key='/' exact path="/" element={component}/>
          <Route key='/home' exact path="home" element={<Home/>}/>
          <Route key='/logIn' exact path="/logIn" element={<LogIn/>}/>
          <Route key='/signUp' exact path="/signUp" element={<Registration/>}/>
          <Route key='/adminPage' exact path="/adminPage" element={<AdminPage/>}/>
          <Route key='/productCategory' exact path='/productCategory' element={<ProductCategory/>}/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;

