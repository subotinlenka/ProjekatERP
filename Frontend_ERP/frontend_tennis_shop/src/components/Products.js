import React, { useState, useEffect } from "react";
import axios from "axios";
import Swal from "sweetalert2";
import ProductsList from "./ProductsList";

function Products() {
  const url = "http://localhost:8085/products";
  const [products, setProducts] = useState(null);
  const [loading, setLoading] = useState(true);

  const fetchProducts = async () => {
    setLoading(true);
    axios.get(url)
        .then(res => {
            console.log(res);
            let products = Array.from(res.data)
            setProducts(products);
            setLoading(false);
        }).catch(err => {
            console.log(err)
            Swal.fire({
                icon: 'error',
                title: 'Error loading products!',
                text: err.data,
            });
        });
};

useEffect(() => {
    fetchProducts();
}, [])

  return (
    <div className="col-12">
        {loading && <h3>Loading...</h3>}
        {!loading && products && <ProductsList products={products} allProducts={true}/>}
    </div>
  );
}
export default Products;
