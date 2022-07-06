import "../css/Card.css";
import ProductCard from "./ProductCard";

const ProductsList = (props) => {


    return (
        <section className="our-webcoderskull padding-lg">
            <ul className="row">
                {props.products.length == 0 && <h3 style={{ textAlign: "center" }}>No products found.</h3>}
                {
                    (props.products).map((product, index) => {
                        return (
                            <ProductCard key={index}
                                product={product}
                                orderDisplay={props.orderDisplay}
                                cartDisplay={props.cartDisplay}
                                allProducts={props.allProducts}
                            />
                             
                        );
                    })
                    
                }
            </ul>
        </section>
    );
}

export default ProductsList;