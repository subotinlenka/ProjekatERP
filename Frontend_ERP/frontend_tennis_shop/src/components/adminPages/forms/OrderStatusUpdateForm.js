import React, { useState, useEffect } from "react";
import '../css/Buttons.css';
import Swal from "sweetalert2";

export default function OrderStatusUpdateForm(props) {
    const initialFormData = Object.freeze({
    orderStatusID: props.status.orderStatusID,
    orderStatusName: props.status.orderStatusName
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
    const orderStatusUpdate = {
        orderStatusID: props.status.orderStatusID,
        orderStatusName: formData.orderStatusName
    };

    if(formData.orderStatusName === "") {
        Swal.fire({
            icon: 'warning',
            text: 'Order Status name is required field and must be filled and must be filled!'
        });
        return false;
    }

    const url = "http://localhost:8085/orderStatus" + "/" +  props.status.orderStatusID;
    const token = localStorage.getItem("token");
    const bearerToken = "Bearer " + token;

    fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: bearerToken,
      },
      body: JSON.stringify(orderStatusUpdate),
    })
      .then((responseFromServer) => {
        console.log(responseFromServer);
    })
    .catch((error) => {
        console.log(error);
    });

    props.onStatusUpdated(orderStatusUpdate);
  };

  return (
    <div className="reg">
        <h3>Modifying the existing Order Status</h3>
            <input
                type="text"
                value={formData.orderStatusID} 
                name="orderStatusID"
                placeholder="Order status ID"
                onChange={handleChange}
                disabled
            />
            <input
                type="text"
                value={formData.orderStatusName}  
                name="orderStatusName"
                placeholder="Order status name"
                onChange={handleChange}
            />
            <button onClick={handleSubmit} className="modifyBtn">Modify</button>
                <br />
            <button onClick={() => props.onStatusUpdated(null)} className="cancel"> Cancel</button>
    </div>
  );
}