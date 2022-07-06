import React, { useState, useEffect } from "react";
import ProductStatusCreateForm from "./forms/ProductStatusCreateFrom";
import ProductStatusUpdateForm from "./forms/ProductStatusUpdateForm";
import './css/Buttons.css'

function ProductStatus() {
  const [statuses, setStatuses] = useState([]);
  const [showCreateNewStatusForm, setShowingCreateNewStatusForm] = useState(false);
  const [statusCurrentlyUpdated, setStatusBeingUpdated] = useState(null);

  const url = "http://localhost:8085/productStatuses";
  const token = localStorage.getItem("token");
  const bearerToken = "Bearer " + token;
  const url_1 = "http://localhost:8085/productStatus";

  function getProductStatuses() {
    fetch(url, {
      method: "GET",
      headers: {
        Authorization: bearerToken,
      },
    })
      .then((response) => response.json())
      .then((statusesFromServer) => {
       setStatuses(statusesFromServer);
       console.log(statusesFromServer);
    })
      .catch((error) => {
        console.log(error);
      });
  }

  function deleteProductStatus(productStatusID) {
    const deleteURL = url_1 + "/" + productStatusID;
    fetch(deleteURL, {
      method: "DELETE",
      headers: {
        Authorization: bearerToken,
      },
    })
      .then((response) => response.json())
      .then((responseFromServer) => {
        console.log(responseFromServer);
        onStatusDeleted(productStatusID);
      })
      .catch((error) => {
        console.log(error);
      });

    window.location.reload();
  }

  useEffect(() => {
    getProductStatuses();
  }, []);

  const [currentPage, setCurrentPage] = useState(1);
  const [perPage, setPerPage] = useState(10);
  const indexOfLast = currentPage * perPage;
  const indexofFirst = indexOfLast - perPage;
  const current = statuses.slice(indexofFirst, indexOfLast);

  return (
    <section className="add-new">
      {showCreateNewStatusForm === false &&
        statusCurrentlyUpdated === null && (
          <div>
            <button onClick={() => setShowingCreateNewStatusForm(true)} className="add">
              Add new Product Status
            </button>
          </div>
        )}
      {statuses.length > 0 &&
        showCreateNewStatusForm === false &&
        statusCurrentlyUpdated === null &&
        renderStatusesTable()}

      {showCreateNewStatusForm && (
        <ProductStatusCreateForm onStatusCreated={onStatusCreated} />
      )}

      {statusCurrentlyUpdated !== null && (
        <ProductStatusUpdateForm
          status={statusCurrentlyUpdated}
          onStatusUpdated={onStatusUpdated}
        />
      )}
    </section>
  );

  function renderStatusesTable() {
    return (
      <section className="table">
        <div className="container">
          <table className="table">
            <thead>
              <tr className="row">
                <th scope="col">Product Status Id</th>
                <th scope="col">Product Status name</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              {current.map((status) => (
                <tr key={status.productStatusID}>
                  <th scope="row">{status.productStatusID}</th>
                  <td>{status.productStatusName}</td>
                  <td>
                    <ul className="editList">
                      <li>
                        <button
                          onClick={() => setStatusBeingUpdated(status)} className="edit">
                        Modify
                        </button>
                      </li>
                    </ul>
                    <br/>
                    <ul className="deleteList">
                      <li>
                        <button
                          onClick={() => {
                            if (window.confirm("Are you sure you want to delete Product Status?"))
                              deleteProductStatus(status.productStatusID);
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

  function onStatusCreated(createdStatus) {
    setShowingCreateNewStatusForm(false);
    if (createdStatus === null) {
      return;
    }

    window.location.reload();
  }

  function onStatusUpdated(updatedStatus) {
    setStatusBeingUpdated(null);

    if (updatedStatus === null) {
      return;
    }

    let statusesCopy = [...statuses];
    const index = statusesCopy.findIndex((statusesCopyStatus) => {
      if (statusesCopyStatus.productStatusID === updatedStatus.productStatusID) {
        return true;
      }
    });

    if (index !== -1) {
        statusesCopy[index] = updatedStatus;
    }

    setStatuses(statusesCopy);
  }

  function onStatusDeleted(deletedStatusId) {
    let statusesCopy = [...statuses];

    const index = statusesCopy.findIndex((statusesCopyStatus) => {
      if (statusesCopyStatus.productStatusID === deletedStatusId) {
        return true;
      }
    });

    if (index !== -1) {
        statusesCopy.splice(index, 1);
    }

    setStatuses(statusesCopy);
  }
}
export default ProductStatus;
