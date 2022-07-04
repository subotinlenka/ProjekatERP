import React, { useState, useEffect } from "react";
import ManufacturerCreateForm from "./forms/ManufacturerCreateForm";
import ManufacturerUpdateForm from "./forms/ManufacturerUpdateForm";
import './css/Buttons.css'

function ProductManufacturer() {
  const [manufacturers, setManufacturers] = useState([]);
  const [showingCreateNewManufacturerForm, setShowingCreateNewManufacturerForm] = useState(false);
  const [manufacturerCurrentlyUpdated, setManufacturerBeingUpdated] = useState(null);

  const url = "http://localhost:8085/productManufacturers";
  const token = localStorage.getItem("token");
  const bearerToken = "Bearer " + token;
  const url_1 = "http://localhost:8085/productManufacturer";

  function getProductManufacturers() {
    fetch(url, {
      method: "GET",
      headers: {
        Authorization: bearerToken,
      },
    })
      .then((response) => response.json())
      .then((manufacturersFromServer) => {
       setManufacturers(manufacturersFromServer);
       console.log(manufacturersFromServer);
    })
      .catch((error) => {
        console.log(error);
      });
  }

  function deleteProductManufacturer(productManufacturerID) {
    const deleteURL = url_1 + "/" + productManufacturerID;
    fetch(deleteURL, {
      method: "DELETE",
      headers: {
        Authorization: bearerToken,
      },
    })
      .then((response) => response.json())
      .then((responseFromServer) => {
        console.log(responseFromServer);
        onManufacturerDeleted(productManufacturerID);
      })
      .catch((error) => {
        console.log(error);
      });

    window.location.reload();
  }

  useEffect(() => {
    getProductManufacturers();
  }, []);

  const [currentPage, setCurrentPage] = useState(1);
  const [perPage, setPerPage] = useState(5);
  const indexOfLast = currentPage * perPage;
  const indexofFirst = indexOfLast - perPage;
  const current = manufacturers.slice(indexofFirst, indexOfLast);

  return (
    <section className="add-new">
      {showingCreateNewManufacturerForm === false &&
        manufacturerCurrentlyUpdated === null && (
          <div>
            <button onClick={() => setShowingCreateNewManufacturerForm(true)} className="add1">
              Add new Product Manufacturer
            </button>
          </div>
        )}
      {manufacturers.length > 0 &&
        showingCreateNewManufacturerForm === false &&
        manufacturerCurrentlyUpdated === null &&
        renderManufacturersTable()}

      {showingCreateNewManufacturerForm && (
        <ManufacturerCreateForm onManufacturerCreated={onManufacturerCreated} />
      )}

      {manufacturerCurrentlyUpdated !== null && (
        <ManufacturerUpdateForm
          manufacturer ={manufacturerCurrentlyUpdated}
          onManufacturerUpdated={onManufacturerUpdated}/>
      )}
    </section>
  );

  function renderManufacturersTable() {
    return (
      <section className="table">
        <div className="container">
          <table className="table1">
            <thead>
              <tr>
                <th scope="col">Manufacturer Id</th>
                <th scope="col">Name</th>
                <th scope="col">Phone number</th>
                <th scope="col">Address</th>
                <th scope="col">City</th>
                <th scope="col">Country</th>
                <th scope="col">Email</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              {current.map((manufacturer) => (
                <tr key={manufacturer.productManufacturerID}>
                  <th scope="row">{manufacturer.productManufacturerID}</th>
                  <td>{manufacturer.manufacturerName}</td>
                  <td>{manufacturer.manufacturerPhoneNumber}</td>
                  <td>{manufacturer.manufacturerAddress}</td>
                  <td> {manufacturer.manufacturerCity}</td>
                  <td>{manufacturer.manufacturerCountry}</td>
                  <td>{manufacturer.manufacturerEmail}</td>
                  <td>
                    <ul className="editList">
                      <li>
                        <button
                          onClick={() => setManufacturerBeingUpdated(manufacturer)} className="edit">
                        Modify
                        </button>
                      </li>
                    </ul>
                    <ul className="deleteList">
                      <li>
                        <button
                          onClick={() => {
                            if (window.confirm("Are you sure you want to delete Product Manufacturer?"))
                              deleteProductManufacturer(manufacturer.productManufacturerID);
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

  function onManufacturerCreated(createdManufacturer) {
    setShowingCreateNewManufacturerForm(false);
    if (createdManufacturer === null) {
      return;
    }

    window.location.reload();
  }

  function onManufacturerUpdated(updatedManufacturer) {
    setManufacturerBeingUpdated(null);

    if (updatedManufacturer === null) {
      return;
    }

    let manufacturersCopy = [...manufacturers];
    const index = manufacturersCopy.findIndex((manufacturersCopyManufacturer) => {
      if (manufacturersCopyManufacturer.productManufacturerID === updatedManufacturer.productManufacturerID) {
        return true;
      }
    });

    if (index !== -1) {
        manufacturersCopy[index] = updatedManufacturer;
    }

    setManufacturers(manufacturersCopy);
  }

  function onManufacturerDeleted(deletedManufacturerId) {
    let manufacturersCopy = [...manufacturers];

    const index = manufacturersCopy.findIndex((manufacturersCopyManufacturer) => {
      if (manufacturersCopyManufacturer.productManufacturerID === deletedManufacturerId) {
        return true;
      }
    });

    if (index !== -1) {
        manufacturersCopy.splice(index, 1);
    }

    setManufacturers(manufacturersCopy);
  }
}
export default ProductManufacturer;
