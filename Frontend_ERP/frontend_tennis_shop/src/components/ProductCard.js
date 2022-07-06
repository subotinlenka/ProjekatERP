import "../css/Card.css";
import { useState, useEffect } from "react";
import { loadStripe } from "@stripe/stripe-js";

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

  let stripePromise;
  const getStripe = () => {
    if (!stripePromise) {
      stripePromise = loadStripe(
        "pk_test_51LIe4zJD4nanDPD0wOiNqBTKUOka4S2UFO1SWSd8rmxeAjuTtIcZIjOOUPnCQJH0RJnW0VREToeKvTPKSe26Ze6s00illOt7SY"
      );
    }
    return stripePromise;
  };

  const item = {
    price: product.priceStripe,
    quantity: 1,
  };

  const checkuoutOptions = {
    lineItems: [item],
    mode: "payment",
    successUrl: `${window.location.origin}/success`,
    cancelUrl: `${window.location.origin}/cancel`,
  };

  const redirectToCheckout = async () => {
    localStorage.setItem("productID", product.productID);
    console.log("redirectToCheckout");
    const stripe = await getStripe();
    const { error } = await stripe.redirectToCheckout(checkuoutOptions);
    console.log("Stripe checkout error", error);
  };

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
                            <button onClick={redirectToCheckout} className="payBtn">Pay product</button>
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
                </div>
            }
        </li>
    );
}

export default ProductCard;