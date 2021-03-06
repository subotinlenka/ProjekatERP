import './App.css';
import Navbar from './components/Navbar';
import Home from './components/Home';
import LogIn from './components/LogIn';
import Registration from './components/Registration';
import AdminPage from './components/AdminPage';
import ProductCategory from './components/adminPages/ProductCategory'
import ProductStatus from './components/adminPages/ProductStatus';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { role } from "./services/auth-service";
import { React, useState, useEffect} from 'react';
import ProductManufacturer from './components/adminPages/ProductManufacturer';
import OrderStatus from './components/adminPages/OrderStatus';
import Admin from './components/adminPages/Admin';
import Products from './components/Products';
import Cart from './components/Cart';
import ProductAdmin from './components/adminPages/ProductsAdmin';
import SuccessPayment from './components/SuccessPayment';
import ErrorPayment from './components/ErrorPayment';


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
          <Route key='/home' exact path="/home" element={<Home/>}/>
          <Route key='/logIn' exact path="/logIn" element={<LogIn/>}/>
          <Route key='/signUp' exact path="/signUp" element={<Registration/>}/>
          <Route key='/adminPage' exact path="/adminPage" element={<AdminPage/>}/>
          <Route key='/productCategory' exact path='/productCategory' element={<ProductCategory/>}/>
          <Route key='/productStatus' exact path='/productStatus' element={<ProductStatus/>}/>
          <Route key='/productManufacturer' exact path='/productManufacturer' element={<ProductManufacturer/>}/>
          <Route key='/orderStatus' exact path='/orderStatus' element={<OrderStatus/>}/>
          <Route key='/admin' exact path='/admin' element={<Admin/>}/>
          <Route key='/products' exact path='/products' element={<Products/>}/>
          <Route key='/cart' exact path='/cart' element={<Cart/>}/>
          <Route key='/product' exact path='/product' element={<ProductAdmin/>}/>
          <Route key='/success' exact path='/success' element={<SuccessPayment/>}/>
          <Route key='/cancel' exact path='/cancel' element={<ErrorPayment/>}/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;

