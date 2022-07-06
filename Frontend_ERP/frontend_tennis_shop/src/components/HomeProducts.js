import React, { useState, useEffect } from "react";
import axios from "axios";
import Image from "../img/productImg.png";
import './adminPages/css/Buttons.css';

function HomeProducts() {
  const url = "http://localhost:8085/products";
  const [products, setProducts] = useState(null);

  useEffect(() => {
    axios
      .get(url)
      .then((response) => {
        setProducts(response.data);
      })
      .catch((error) => {
        console.log(error);
        alert(error);
      });
  }, [url]);

  if (products) {
    return (
      <section className="products">
        <div className="parent">
              <div className="child">
                {products.map((product) => (
                  <article key={product.productID}>
                    <br/>
                    <div className="element">
                    <img src={Image} className="img-fluid" width="300px" height="230px"></img> 
                    </div>
                    <div>
                    <h3>Product: {product.productName}</h3>
                    </div>
                    <br/>
                    <div>
                    <a>Description: {product.productDescription}</a>
                    <br/>
                    </div>
                    <div>
                    <br/>
                    <h4>Price: {product.productPrice} RSD</h4>
                    </div>
                    
                    <button className="cartBtn">Add to cart</button>
                  </article>
                ))}
              </div>
            </div>
      </section>
    );
  }
  return <div></div>;
}
export default HomeProducts;
