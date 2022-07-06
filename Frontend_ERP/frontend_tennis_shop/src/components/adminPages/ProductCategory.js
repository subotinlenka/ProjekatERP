import React, { useState, useEffect } from "react";
import ProductCategoryCreateForm from "./forms/ProductCategoryCreateForm";
import ProductCategoryUpdateForm from "./forms/ProductCategoryUpdateForm";
import './css/Buttons.css'

function ProductCategory() {
  const [categories, setCategories] = useState([]);
  const [showingCreateNewCategoryForm, setShowingCreateNewCategoryForm] = useState(false);
  const [categoryCurrentlyUpdated, setCategoryBeingUpdated] = useState(null);

  const url = "http://localhost:8085/productCategories";
  const token = localStorage.getItem("token");
  const bearerToken = "Bearer " + token;
  const url_1 = "http://localhost:8085/productCategory";

  function getProductCategories() {
    fetch(url, {
      method: "GET",
      headers: {
        Authorization: bearerToken,
      },
    })
      .then((response) => response.json())
      .then((categoriesFromServer) => {
       setCategories(categoriesFromServer);
       console.log(categoriesFromServer);
    })
      .catch((error) => {
        console.log(error);
      });
  }

  function deleteProductCategory(productCategoryID) {
    const deleteURL = url_1 + "/" + productCategoryID;
    fetch(deleteURL, {
      method: "DELETE",
      headers: {
        Authorization: bearerToken,
      },
    })
      .then((response) => response.json())
      .then((responseFromServer) => {
        console.log(responseFromServer);
        onCategoryDeleted(productCategoryID);
      })
      .catch((error) => {
        console.log(error);
      });

    window.location.reload();
  }

  useEffect(() => {
    getProductCategories();
  }, []);

  const [currentPage, setCurrentPage] = useState(1);
  const [perPage, setPerPage] = useState(10);
  const indexOfLast = currentPage * perPage;
  const indexofFirst = indexOfLast - perPage;
  const current = categories.slice(indexofFirst, indexOfLast);

  return (
    <section className="add-new">
      {showingCreateNewCategoryForm === false &&
        categoryCurrentlyUpdated === null && (
          <div>
            <button onClick={() => setShowingCreateNewCategoryForm(true)} className="add">
              Add new Product Category
            </button>
          </div>
        )}
      {categories.length > 0 &&
        showingCreateNewCategoryForm === false &&
        categoryCurrentlyUpdated === null &&
        renderCategoriesTable()}

      {showingCreateNewCategoryForm && (
        <ProductCategoryCreateForm onCategoryCreated={onCategoryCreated} />
      )}

      {categoryCurrentlyUpdated !== null && (
        <ProductCategoryUpdateForm
          category={categoryCurrentlyUpdated}
          onCategoryUpdated={onCategoryUpdated}
        />
      )}
    </section>
  );

  function renderCategoriesTable() {
    return (
      <section className="table">
        <div className="container">
          <table className="table">
            <thead>
              <tr>
                <th scope="col">Category Id</th>
                <th scope="col">Category name</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              {current.map((category) => (
                <tr key={category.productCategoryID}>
                  <th scope="row">{category.productCategoryID}</th>
                  <td>{category.productCategoryName}</td>
                  <td>
                    <ul className="editList">
                      <li>
                        <button
                          onClick={() => setCategoryBeingUpdated(category)} className="edit">
                        Modify
                        </button>
                      </li>
                    </ul>
                    <br/>
                    <ul className="deleteList">
                      <li>
                        <button
                          onClick={() => {
                            if (window.confirm("Are you sure you want to delete Product Category?"))
                              deleteProductCategory(category.productCategoryID);
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

  function onCategoryCreated(createdCategory) {
    setShowingCreateNewCategoryForm(false);
    if (createdCategory === null) {
      return;
    }

    window.location.reload();
  }

  function onCategoryUpdated(updatedCategory) {
    setCategoryBeingUpdated(null);

    if (updatedCategory === null) {
      return;
    }

    let categoriesCopy = [...categories];
    const index = categoriesCopy.findIndex((categoriesCopyCategory) => {
      if (categoriesCopyCategory.productCategoryID === updatedCategory.productCategoryID) {
        return true;
      }
    });

    if (index !== -1) {
        categoriesCopy[index] = updatedCategory;
    }

    setCategories(categoriesCopy);
  }

  function onCategoryDeleted(deletedCategoryId) {
    let categoriesCopy = [...categories];

    const index = categoriesCopy.findIndex((categoriesCopyCategory) => {
      if (categoriesCopyCategory.productCategoryID === deletedCategoryId) {
        return true;
      }
    });

    if (index !== -1) {
        categoriesCopy.splice(index, 1);
    }

    setCategories(categoriesCopy);
  }
}
export default ProductCategory;
