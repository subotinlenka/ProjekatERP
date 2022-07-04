import {useNavigate } from "react-router-dom";
import { React, useState, useEffect} from 'react';
import { role } from "../services/auth-service";
import '../css/Navbar.css';

const Navbar = () => {

    const [loged, setLoged] = useState(false);
    const [user, setUser] = useState(null);
    const navigate = useNavigate();
 
    useEffect( () => {
        if(localStorage.getItem('accessToken') !== null)
            getUserRole();
            else
            {
                setUser(null);
                setLoged(false);
            }
   });

    const getUserRole = async () => {
    const userRole = await role();
    setUser(userRole.role.authority);
    setLoged(true);
}
 
    const logOut = () => {
       localStorage.removeItem('accessToken');
       localStorage.removeItem('it');
       navigate('/logIn');
    }
    return ( 
        <nav className="navbar">
            <div className="title">
                <h1 className="title">Tennis equipment web shop</h1>
            </div>
            <div className="home"> 
                <a href='/home' className="home">Home</a>
            </div>
            <div className="logIn">
                {loged !== true && <a href="/logIn">Log In</a>}
            </div>
            <div className="signUp">
                {loged !== true && <a href="/signUp">Sign Up</a>}
            </div>
            <div className="chart">
                {user === "Customer" && <a href="/chart">Shopping chart</a>}
            </div>
            <div>
                {user === "Admin" && <a href="/productCategory" className="productCategory">Product Categories</a>}
            </div>
            <div>
                {user === "Admin" && <a href="/productManufacturer" className="productManufacturer">Product Manufacturers</a>}
            </div>
            <div>
                {user === "Admin" && <a href="/productStatus" className="productStatus">Product Statuses</a>}
            </div>
            <div>
                {user === "Admin" && <a href="/product">Products</a>}
            </div>
            <div>
                {user === "Admin" && <a href="/orderStatus" className="orderStatus">Order Statuses</a>}
            </div>
            <div>
                {user === "Admin" && <a href="/order">Orders</a>}
            </div>
            <div>
                {user === "Admin" && <a href="/admin">Admins</a>}
            </div>
            <div>
                {loged === true && <button onClick={() => logOut()} className="button">Log out</button>}
            </div>      
        </nav>
        
     );
}
 
export default Navbar;