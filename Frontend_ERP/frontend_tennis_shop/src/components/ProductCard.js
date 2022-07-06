import "../css/Card.css";
import { useState, useEffect } from "react";
import showOrderForm from './Cart';

const ProductCard = (props) => {
    const [quantity, setQuantity] = useState(1);
    const [product, setProduct] = useState({});
    const addToCart = (product) => {
        if (!localStorage.getItem('cart')) {
            localStorage.setItem('cart', JSON.stringify([]));
        }
        var cartItems = JSON.parse(localStorage.getItem('cart'));
        var exists = false;
        cartItems.map((item) => {
            if (item.product.productID == product.productID)
                exists = true
        });
        if (!exists) {
            cartItems = cartItems.concat({
                quantity: quantity,
                product: product
            })
            localStorage.setItem('cart', JSON.stringify(cartItems));
        } else {
            var newCartItems = []
            var oldQuantity = 0
            cartItems.map((item) => {
                console.log(item.product.productID, product.productID)
                if (item.product.productID != product.productID)
                    newCartItems = newCartItems.concat(item);
                else
                    oldQuantity = item.quantity
            })
            var newQuantity = parseInt(quantity) + parseInt(oldQuantity);
            newCartItems = newCartItems.concat({
                quantity: newQuantity,
                product: product
            })
            localStorage.setItem('cart', JSON.stringify(newCartItems));
        }
    }

    const changeQuantity = (value) => {
        setQuantity(value);
        console.log(value)
        if (!localStorage.getItem('cart')) {
            localStorage.setItem('cart', JSON.stringify([]));
        }
        var cartItems = JSON.parse(localStorage.getItem('cart'));
        var newCartItems = []
        cartItems.map((item) => {
            console.log(item.product.productID, product.productID)
            if (item.product.productID != product.productID)
                newCartItems = newCartItems.concat(item);
        })
        newCartItems = newCartItems.concat({
            quantity: value,
            product: product
        })
        console.log(cartItems);
        localStorage.setItem('cart', JSON.stringify(newCartItems));
    }

    const removeFromCart = () => {
        if (!localStorage.getItem('cart')) {
            localStorage.setItem('cart', JSON.stringify([]));
        }
        var cartItems = JSON.parse(localStorage.getItem('cart'));
        var newCartItems = []
        cartItems.map((item) => {
            if (item.product.productID != product.productID)
                newCartItems = newCartItems.concat(item);
        })
        console.log(cartItems);
        localStorage.setItem('cart', JSON.stringify(newCartItems));
        window.location.reload();
    }

    useEffect(() => {
        console.log(props.product)
        if (props.cartDisplay) {
            setProduct(props.product.product);
            setQuantity(props.product.quantity);
        } else {
            setProduct(props.product);
        }
    }, [])

    return (
        <li className="col-12 col-md-4 col-lg-3">
            {product &&
                <div className="cnt-block equal-hightcnt-block equal-hight" style={{ height: "360px" }} >
                    <figure><img src={require("../img/productImg.png")} className="img-responsive" alt=""></img></figure>
                    <h3>{product.productName}</h3>
                    <p>Description: {product.productDescription}</p>
                    <h4>Price : {product.productPrice}</h4>
                    {
                        props.allProducts && <>
                            <input type="number" onChange={(e) => setQuantity(e.target.value)} className="form-control" min={1} placeholder="Enter quantity" value={quantity} />
                            <button onClick={(e) => addToCart(product)} type="button" className="cartBtn">Add to cart</button>
                        </>
                    }
                    <br/>
                    {
                        props.cartDisplay && <>
                            <input type="number" onChange={(e) => changeQuantity(e.target.value)} className="form-control" min={1} placeholder="Enter quantity" value={quantity} />
                            <button onClick={(e) => removeFromCart(product)} type="button" className="removeBtn">Remove</button>
                        </>
                    }
                    <br/>
                    {
                       props.cartDisplay && <>
                            <button onClick={(e) => showOrderForm()} type="button" className="orderBtn">Create an Order</button>
                        </>
                    }
                </div>
            }
        </li>
    );
}

export default ProductCard;