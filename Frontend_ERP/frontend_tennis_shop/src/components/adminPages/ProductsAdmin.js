import React, { useState, useEffect } from "react";
import ProductCreateForm from "./forms/ProductCreateForm";
import ProductUpdateForm from "./forms/ProductUpdateForm";
import './css/Buttons.css';

function ProductsAdmin() {
  const [products, setProducts] = useState([]);
  const [showingCreateNewProductForm, setShowingCreateNewProductForm] =useState(false);
  const [productCurrentlyBeingUpdated, setProductCurrentlyBeingUpdated] =useState(null);

  const url = "http://localhost:8085/products";
  const token = localStorage.getItem("token");
  const bearerToken = "Bearer " + token;
  const url1 = "http://localhost:8085/product";

  function getProducts() {
    fetch(url, {
      method: "GET",
      headers: {
        Authorization: bearerToken,
      },
    })
      .then((response) => response.json())
      .then((productsFromServer) => {
        setProducts(productsFromServer);
        console.log(productsFromServer);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function deleteProduct(productID) {
    const deleteURL = url1 + "/" + productID;
    fetch(deleteURL, {
      method: "DELETE",
      headers: {
        Authorization: bearerToken,
      },
    })
      .then((response) => response.json())
      .then((responseFromServer) => {
        console.log(responseFromServer);
        onProductDeleted(productID);
      })
      .catch((error) => {
        console.log(error);
      });

    window.location.reload();
  }

  useEffect(() => {
    getProducts();
  }, []);

  //Pagination
  const [currentPage, setCurrentPage] = useState(1);
  const [perPage, setPerPage] = useState(5);
  const indexOfLast = currentPage * perPage;
  const indexofFirst = indexOfLast - perPage;
  const current = products.slice(indexofFirst, indexOfLast);
  const total = products.length;

  return (
    <section className="add-new">
      {showingCreateNewProductForm === false &&
        productCurrentlyBeingUpdated === null && (
          <div>
            <button onClick={() => setShowingCreateNewProductForm(true)} className="add2">
              Add new Product
            </button>
          </div>
        )}
      {products.length > 0 &&
        showingCreateNewProductForm === false &&
        productCurrentlyBeingUpdated === null &&
        renderProductsTable()}

      {showingCreateNewProductForm && (
        <ProductCreateForm onProductCreated={onProductCreated} />
      )}

      {productCurrentlyBeingUpdated !== null && (
        <ProductUpdateForm
          product={productCurrentlyBeingUpdated}
          onProductUpdated={onProductUpdated}
        />
      )}
    </section>
  );

  function renderProductsTable() {
    return (
      <section className="table">
        <div className="container">
          <table className="table2">
            <thead>
              <tr>
                <th scope="col" align="center">Id</th>
                <th scope="col" align="center">Name</th>
                <th scope="col" align="center">Description</th>
                <th scope="col" align="center">Size</th>
                <th scope="col" align="center">Quantity</th>
                <th scope="col"align="center">Price</th>
                <th scope="col">Image</th>
                <th scope="col">Discount</th>
                <th scope="col">PriceWithDiscount</th>
                <th scope="col" align="center">Status</th>
                <th scope="col" align="center">Category</th>
                <th scope="col" align="center">Manufacturer</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              {current.map((product) => (
                <tr key={product.productID}>
                  <th scope="row">{product.productID}</th>
                  <td>{product.productName}</td>
                  <td>{product.productDescription}</td>
                  <td>{product.productSize}</td>
                  <td>{product.productQuantity}</td>
                  <td>{product.productPrice}</td>
                  <td>{product.productImage}</td>
                  <td>{product.productDiscount}</td>
                  <td>{product.productPriceWithDiscount}</td>
                  <td>{product.productStatus.productStatusName}</td>
                  <td>{product.productCategory.productCategoryName}</td>
                  <td>{product.productManufacturer.manufacturerName}</td>
                  <td>
                    <ul className="editList">
                      <li>
                        <button
                          onClick={() => setProductCurrentlyBeingUpdated(product)} className="edit">
                        Modify
                        </button>
                      </li>
                    </ul>
                    <br/>
                    <ul className="deleteList">
                      <li>
                        <button
                          onClick={() => {
                            if (window.confirm("Are you sure you want to delete Product?"))
                              deleteProduct(product.productID);
                          } } className="delete">
                        Delete
                        </button>
                      </li>
                    </ul>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </section>
    );
  }

  function onProductCreated(createdProduct) {
    setShowingCreateNewProductForm(false);
    if (createdProduct === null) {
      return;
    }

    window.location.reload();
  }

  function onProductUpdated(updatedProduct) {
    setProductCurrentlyBeingUpdated(null);

    if (updatedProduct === null) {
      return;
    }

    let productsCopy = [...products];
    const index = productsCopy.findIndex((productsCopyProduct) => {
      if (productsCopyProduct.productID === updatedProduct.productID) {
        return true;
      }
    });

    if (index !== -1) {
        productsCopy[index] = updatedProduct;
    }

    setProducts(productsCopy);
  }

  function onProductDeleted(deleteProductId) {
    let productsCopy = [...products];

    const index = productsCopy.findIndex((productsCopyProduct) => {
      if (productsCopyProduct.productID === deleteProductId) {
        return true;
      }
    });

    if (index !== -1) {
      productsCopy.splice(index, 1);
    }

    setProducts(productsCopy);
  }
}
export default ProductsAdmin;
