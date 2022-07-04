import React, { useState, useEffect } from "react";
import '../css/Buttons.css';
import Swal from "sweetalert2";

export default function ProductStatusUpdateForm(props) {
    const initialFormData = Object.freeze({
    productStatusID: props.status.productStatusID,
    productStatusName: props.status.productStatusName
});

const [formData, setFormData] = useState(initialFormData);
const handleChange = (e) => {
  setFormData({
    ...formData,
    [e.target.name]: e.target.value,
  });
};

  const handleSubmit = (e) => {
    e.preventDefault();
    const productStatusUpdate = {
        productStatusID: props.status.productStatusID,
        productStatusName: formData.productStatusName
    };

    if(formData.productStatusName === "") {
      Swal.fire({
          icon: 'warning',
          text: 'Product Status name is required field and must be filled and must be filled!'
      });
      return false;
    }

    const url = "http://localhost:8085/productStatus" + "/" +  props.status.productStatusID;
    const token = localStorage.getItem("token");
    const bearerToken = "Bearer " + token;

    fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: bearerToken,
      },
      body: JSON.stringify(productStatusUpdate),
    })
      .then((responseFromServer) => {
        console.log(responseFromServer);
    })
    .catch((error) => {
        console.log(error);
    });

    props.onStatusUpdated(productStatusUpdate);
  };

  return (
    <div className="reg">
        <h3>Modifying the existing Product Status</h3>
            <input
                type="text"
                value={formData.productStatusID} 
                name="productStatusID"
                placeholder="Product status ID"
                onChange={handleChange}
                disabled
            />
            <input
                type="text"
                value={formData.productStatusName}  
                name="productStatusName"
                placeholder="Product status name"
                onChange={handleChange}
            />
            <button onClick={handleSubmit} className="modifyBtn">Modify</button>
                <br />
            <button onClick={() => props.onStatusUpdated(null)} className="cancel"> Cancel</button>
    </div>
  );
}