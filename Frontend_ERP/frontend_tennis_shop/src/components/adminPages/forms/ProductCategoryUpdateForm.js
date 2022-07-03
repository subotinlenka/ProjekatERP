import React, { useState, useEffect } from "react";
import '../css/Buttons.css'


export default function ProductCategoryUpdateForm(props) {
    const initialFormData = Object.freeze({
    productCategoryID: props.category.productCategoryID,
    productCategoryName: props.category.productCategoryName
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
    const productCategoryUpdate = {
        productCategoryID: props.category.productCategoryID,
        productCategoryName: props.category.productCategoryName
    };

    const url = "http://localhost:8085/productCategory" + "/" +  props.category.productCategoryID;

    fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(productCategoryUpdate),
    })
      .then((responseFromServer) => {
        console.log(responseFromServer);
    })
    .catch((error) => {
        console.log(error);
    });

    props.onCategoryUpdated(productCategoryUpdate);
  };

  return (
    <div className="reg">
        <h3>Modifying the existing Product Category</h3>
            <input
                type="text"
                value={formData.productCategoryID}
                name="Product category ID"
                placeholder="Product category ID"
                onChange={handleChange}
                disabled
            />
            <input
                type="text"
                value={formData.productCategoryName}
                name="Product category name"
                placeholder="Product category name"
                onChange={handleChange}
                disabled={false}
            />
            <button onClick={handleSubmit} className="modifyBtn">Modify</button>
                <br />
            <button onClick={() => props.onCategoryUpdated(null)} className="cancel"> Cancel</button>
    </div>
  );
}
