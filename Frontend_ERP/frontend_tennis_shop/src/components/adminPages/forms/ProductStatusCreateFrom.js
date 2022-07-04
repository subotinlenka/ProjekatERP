import React, { useState } from "react";
import '../css/Buttons.css'
import Swal from "sweetalert2";

export default function CreateStatusForm(props) {
  const initialFormData = Object.freeze({
    productStatusName: ""
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
    const productStatusCreate = {
      productStatusName: formData.productStatusName
    };

    if(formData.productStatusName === "") {
      Swal.fire({
          icon: 'warning',
          text: 'Product Status name is required field and must be filled and must be filled!'
      });
      return false;
    }

    const url = "http://localhost:8085/productStatus";
    const token = localStorage.getItem("token");
    const bearerToken = "Bearer " + token;

    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: bearerToken,
      },
      body: JSON.stringify(productStatusCreate),
    })
      .then((responseFromServer) => {
        console.log(responseFromServer);
      })
      .catch((error) => {
        console.log(error);
      });

    props.onStatusCreated(productStatusCreate);
  };

  return (
    <div>
        <div className="reg">
            <h3>Adding new Product Status</h3>
                <input
                    value={formData.productStatusName}
                    name="productStatusName"
                    type="text"
                    placeholder="Product status name"
                    onChange={handleChange}
                    className="productStatusName"
                    disabled={false}>
                </input>
            <button onClick={handleSubmit} className="addBtn">Add</button>
            <br/>
            <button onClick={() => props.onStatusCreated(null)} className="cancel"> Cancel</button>
        </div>
    </div>
  );
}
