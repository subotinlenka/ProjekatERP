import React, { useState, useEffect } from "react";
import axios from "axios";
import Swal from "sweetalert2";
import ProductsList from "./ProductsList";

function Cart() {
  const [loading, setLoading] = useState(true);
  const [isPending, setIsPending] = useState(false);
  const [products, setProducts] = useState(null);
  const [orderAddress, setAddress] = useState("");
  const [orderCity, setCity] = useState("");
  const [orderPaymentType, setPaymentType] = useState("");

  const fetchProducts = async () => {
    setLoading(false);
    setProducts(Array.from(JSON.parse(localStorage.getItem('cart'))));
};

useEffect(() => {
    fetchProducts();
}, [])

const Validate = () => {

if(orderAddress === "") {
    Swal.fire({
        icon: 'warning',
        text: 'Order address is required field and must be filled!'
    });
    return false;
}

if(orderCity === "") {
    Swal.fire({
        icon: 'warning',
        text: 'Order city is required field and must be filled!'
    });
    return false;
}

if(orderPaymentType === "") {
    Swal.fire({
        icon: 'warning',
        text: 'Order payment type is required field and must be filled!',
    });
    return false;
}
}

const order = () => {
    if (!Validate())
        return;
    setIsPending(true);
    var orderItems = [];
    var totalPrice = 0;
    products.map((item) => {
        orderItems = orderItems.concat({
            productID: item.product.productID,
            quantity : item.quantity
        })
        totalPrice += item.product.productPrice * item.quantity;
    })
    var createOrder = {
        "address" : orderAddress,
        "city" : orderCity,
        "paymentType" : orderPaymentType,
        'items': orderItems,
        //'userId':localStorage.getItem('userID'),
    };
    console.log(createOrder);
    axios.post('http://localhost:8085/order', createOrder).then(res => {
    if (res.status === 201) {
        setIsPending(false);
        Swal.fire({
            icon: 'success',
            text: "Creating order has been successfull!"
        });
       // history('/payment');
    }else{
        setIsPending(false);
        console.log(res.response.data.message);
        Swal.fire({
            icon: 'error',
            text: res.data.response.message,
        });
    }
    }).catch((error) => {
    if( error.response ){
        setIsPending(false);
        console.log(error.response.data.message);
        Swal.fire({
            icon: 'error',
            text: error.response.data.message,
        });
    }
    });;
}
    function showOrderForm()  {
        
        return (
            <div className="div-signUp">
            <h2 className='style'>Creating new order</h2>
            <form style={{ maxWidth: "50%", margin: "auto"}}>
                <div className="mb-3">
                    <label className="form-label">Order address</label>
                    <input value={orderAddress} onChange={(e) => setAddress(e.target.value)} required type="address" className="form-control" id="InputAddress" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Order City</label>
                    <input value={orderCity} onChange={(e) => setCity(e.target.value)} required type="city" className="form-control" id="InputCity" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Order Payment Type</label>
                    <input value={orderPaymentType} onChange={(e) => setPaymentType(e.target.value)} required type="paymentType" className="form-control" id="InputPaymentType" />
                </div> 
            </form>
            </div>
            );  
    }

  return (
    <div className="col-12">
        {loading && <h3>Loading...</h3>}
        {!loading && products && <ProductsList products={products} orderDisplay={false} cartDisplay={true}/>}
    </div>
  );
}
export default Cart;
