import React, { useState } from "react";
import '../css/Buttons.css';
import Swal from "sweetalert2";

export default function CreateStatusForm(props) {
  const initialFormData = Object.freeze({
    orderStatusName: ""
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
    const orderStatusCreate = {
        orderStatusName: formData.orderStatusName
    };

    if(formData.orderStatusName === "") {
      Swal.fire({
          icon: 'warning',
          text: 'Order Status name is required field and must be filled and must be filled!'
      });
      return false;
    }

    const url = "http://localhost:8085/orderStatus";
    const token = localStorage.getItem("token");
    const bearerToken = "Bearer " + token;

    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: bearerToken,
      },
      body: JSON.stringify(orderStatusCreate),
    })
      .then((responseFromServer) => {
        console.log(responseFromServer);
      })
      .catch((error) => {
        console.log(error);
      });

    props.onStatusCreated(orderStatusCreate);
  };

  return (
    <div>
        <div className="reg">
            <h3>Adding new Order Status</h3>
                <input
                    value={formData.orderStatusName}
                    name="orderStatusName"
                    type="text"
                    placeholder="Order status name"
                    onChange={handleChange}
                    className="orderStatusName"
                    disabled={false}>
                </input>
            <button onClick={handleSubmit} className="addBtn">Add</button>
            <br/>
            <button onClick={() => props.onStatusCreated(null)} className="cancel"> Cancel</button>
        </div>
    </div>
  );
}
