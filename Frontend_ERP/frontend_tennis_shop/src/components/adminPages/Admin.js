import React, { useState, useEffect } from "react";
import AdminCreateForm from "./forms/AdminCreateForm";
import AdminUpdateForm from "./forms/AdminUpdateForm"
import './css/Buttons.css'

function Admin() {
  const [admins, setAdmins] = useState([]);
  const [showingCreateNewAdminForm, setShowingCreateNewAdminForm] = useState(false);
  const [adminCurrentlyUpdated, setAdminBeingUpdated] = useState(null);

  const url = "http://localhost:8085/admins";
  const token = localStorage.getItem("token");
  const bearerToken = "Bearer " + token;
  const url_1 = "http://localhost:8085/user";

  function getAdmins() {
    fetch(url, {
      method: "GET",
      headers: {
        Authorization: bearerToken,
      },
    })
      .then((response) => response.json())
      .then((adminsFromServer) => {
       setAdmins(adminsFromServer);
       console.log(adminsFromServer);
    })
      .catch((error) => {
        console.log(error);
      });
  }

  function deleteAdmin(userID) {
    const deleteURL = url_1 + "/" + userID;
    fetch(deleteURL, {
      method: "DELETE",
      headers: {
        Authorization: bearerToken,
      },
    })
      .then((response) => response.json())
      .then((responseFromServer) => {
        console.log(responseFromServer);
        onAdminDeleted(userID);
      })
      .catch((error) => {
        console.log(error);
      });

    window.location.reload();
  }

  useEffect(() => {
    getAdmins();
  }, []);

  const [currentPage, setCurrentPage] = useState(1);
  const [perPage, setPerPage] = useState(5);
  const indexOfLast = currentPage * perPage;
  const indexofFirst = indexOfLast - perPage;
  const current = admins.slice(indexofFirst, indexOfLast);

  return (
    <section className="add-new">
      {showingCreateNewAdminForm === false &&
        adminCurrentlyUpdated === null && (
          <div>
            <button onClick={() => setShowingCreateNewAdminForm(true)} className="add2">
              Add new Admin of tne web shop
            </button>
          </div>
        )}
      {admins.length > 0 &&
        showingCreateNewAdminForm === false &&
        adminCurrentlyUpdated === null &&
        renderAdminsTable()}

      {showingCreateNewAdminForm && (
        <AdminCreateForm onAdminCreated={onAdminCreated} />
      )}

      {adminCurrentlyUpdated !== null && (
        <AdminUpdateForm
          admin ={adminCurrentlyUpdated}
          onAdminUpdated={onAdminUpdated}/>
      )}
    </section>
  );

  function renderAdminsTable() {
    return (
      <section className="table">
        <div className="container">
          <table className="table2">
            <thead>
              <tr>
                <th scope="col">Admin Id</th>
                <th scope="col">First name</th>
                <th scope="col">Last name</th>
                <th scope="col">Date of birth</th>
                <th scope="col">Phone number</th>
                <th scope="col" align="center">Email</th>
                <th scope="col" align="center">Address</th>
                <th scope="col" align="center">City</th>
                <th scope="col">Country</th>
                <th scope="col" align="center">Username</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              {current.map((admin) => (
                <tr key={admin.userID}>
                  <th scope="row">{admin.userID}</th>
                  <td>{admin.userFirstName}</td>
                  <td>{admin.userLastName}</td>
                  <td>{admin.userDateOfBirth}</td>
                  <td>{admin.userPhoneNumber}</td>
                  <td>{admin.userEmail}</td>
                  <td>{admin.userAddress}</td>
                  <td>{admin.userCity}</td>
                  <td>{admin.userCountry}</td>
                  <td>{admin.userUserName}</td>
                  <td>
                    <ul className="editList">
                      <li>
                        <button
                          onClick={() => setAdminBeingUpdated(admin)} className="edit">
                        Modify
                        </button>
                      </li>
                    </ul>
                    <br/>
                    <ul className="deleteList">
                      <li>
                        <button
                          onClick={() => {
                            if (window.confirm("Are you sure you want to delete Admin of the web shop?"))
                              deleteAdmin(admin.userID);
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

  function onAdminCreated(createdAdmin) {
    setShowingCreateNewAdminForm(false);
    if (createdAdmin === null) {
      return;
    }

    window.location.reload();
  }

  function onAdminUpdated(updatedAdmin) {
    setAdminBeingUpdated(null);

    if (updatedAdmin === null) {
      return;
    }

    let adminsCopy = [...admins];
    const index = adminsCopy.findIndex((adminsCopyAdmin) => {
      if (adminsCopyAdmin.userID === updatedAdmin.userID) {
        return true;
      }
    });

    if (index !== -1) {
        adminsCopy[index] = updatedAdmin;
    }

    setAdmins(adminsCopy);
  }

  function onAdminDeleted(deletedUserId) {
    let adminsCopy = [...admins];

    const index = adminsCopy.findIndex((adminsCopyAdmin) => {
      if (adminsCopyAdmin.userID === deletedUserId) {
        return true;
      }
    });

    if (index !== -1) {
        adminsCopy.splice(index, 1);
    }

    setAdmins(adminsCopy);
  }
}
export default Admin;
