
import NavbarAdminPage from './NavbarAdminPage';
import Home from './Home'
import ProductCategory from './adminPages/ProductCategory';

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { React } from 'react';

const AdminPage = () => {
 
    return (
      <div>
        <Router>
            <NavbarAdminPage/>
        <Routes>  
            <Route key='/' exact path="/" element={<Home/>}/>
            <Route key='/home' exact path="/home" element={<Home/>}/>
            <Route key='/productCategory' exact path="/productCategory" element={<ProductCategory/>}/>
          </Routes>
        </Router>
      </div>
    );
  }

export default AdminPage;
  