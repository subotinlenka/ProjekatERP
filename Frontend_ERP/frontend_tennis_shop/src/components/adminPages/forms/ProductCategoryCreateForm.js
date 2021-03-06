import React, { useState } from "react";
import '../css/Buttons.css'
import Swal from "sweetalert2";

export default function CreateCategoryForm(props) {
  const initialFormData = Object.freeze({
    productCategoryName: ""
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
    const productCategoryCreate = {
      productCategoryName: formData.productCategoryName
    };
     
    if(formData.productCategoryName === "") {
      Swal.fire({
          icon: 'warning',
          text: 'Product Category name is required field and must be filled and must be filled!'
      });
      return false;
    }

    const url = "http://localhost:8085/productCategory";
    const token = localStorage.getItem("token");
    const bearerToken = "Bearer " + token;

    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: bearerToken,
      },
      body: JSON.stringify(productCategoryCreate),
    })
      .then((responseFromServer) => {
        console.log(responseFromServer);
      })
      .catch((error) => {
        console.log(error);
      });

    props.onCategoryCreated(productCategoryCreate);
  };

  return (
    <div>
        <div className="reg">
            <h3>Adding new Product Category</h3>
                <input
                    value={formData.productCategoryName}
                    name="productCategoryName"
                    type="text"
                    placeholder="Product category name"
                    onChange={handleChange}
                    className="productCategoryName"
                    disabled={false}>
                </input>
            <button onClick={handleSubmit} className="addBtn">Add</button>
            <br/>
            <button onClick={() => props.onCategoryCreated(null)} className="cancel"> Cancel</button>
        </div>
    </div>
  );
}
