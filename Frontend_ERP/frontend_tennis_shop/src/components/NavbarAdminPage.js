import { React } from 'react';

const NavbarAdminPage = () => {


    return ( 
        <nav className="navbarAdminPage">
            <h1>Tennis equipment web shop</h1>
            <div className="links">
                <a href='/home'>Home</a>
                <a href="/productCategory">Product Categories</a>
                <a href="/productManufacturer">Product Manufacturers</a>
                <a href="/productStatus">Product Statuses</a>
                <a href="/product">Products</a>
                <a href="/orderStatus">Order Statuses</a>
                <a href="/admin">Admins</a>
            </div>
        </nav>
        
     );
}
 
export default NavbarAdminPage;