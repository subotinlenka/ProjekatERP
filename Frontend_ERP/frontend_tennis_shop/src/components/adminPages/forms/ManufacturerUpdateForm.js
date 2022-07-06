import React, { useState, useEffect } from "react";
import '../css/Buttons.css'
import Swal from "sweetalert2";
import validator from 'validator';

export default function ManufacturerUpdateForm(props) {
    const initialFormData = Object.freeze({
    productManufacturerID: props.manufacturer.productManufacturerID,
    manufacturerName: props.manufacturer.manufacturerName,
    manufacturerPhoneNumber: props.manufacturer.manufacturerPhoneNumber,
    manufacturerAddress: props.manufacturer.manufacturerAddress,
    manufacturerCity: props.manufacturer.manufacturerCity,
    manufacturerCountry: props.manufacturer.manufacturerCountry,
    manufacturerEmail: props.manufacturer.manufacturerEmail
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
    const manufacturerUpdate = {
        productManufacturerID: props.manufacturer.productManufacturerID,
        manufacturerName: formData.manufacturerName,
        manufacturerPhoneNumber: formData.manufacturerPhoneNumber,
        manufacturerAddress: formData.manufacturerAddress,
        manufacturerCity: formData.manufacturerCity,
        manufacturerCountry: formData.manufacturerCountry,
        manufacturerEmail: formData.manufacturerEmail   
    };

    if (formData.manufacturerName === "") {
      Swal.fire({
         icon: 'warning',
         text: 'Manufacturer name is required field and must be filled!'
       });
     return false;
    }

    else if(formData.manufacturerPhoneNumber === "") {    
          Swal.fire({
            icon: 'warning',
            text: 'Manufacturer phone number is required field and must be filled!'
          });
        return false;
    }

    else if(formData.manufacturerAddress === "") {
      Swal.fire({
          icon: 'warning',
          text: 'Manufacturer address is required field and must be filled!'
      });
      return false;
    }

    else if(formData.manufacturerCity === "") {
      Swal.fire({
          icon: 'warning',
          text: 'Manufacturer city is required field and must be filled and must be filled!'
      });
      return false;
    }

    else if(formData.manufacturerCountry === "") {
      Swal.fire({
          icon: 'warning',
          text: 'Manufacturer country is required field and must be filled!',
      });
      return false;
    }

    else if(formData.manufacturerEmail === "") {
      Swal.fire({
          icon: 'warning',
          text: 'Manufacturer email is required field and must be filled!'
      });
      return false;
    }

    else if (!validator.isEmail(formData.manufacturerEmail)) {
      Swal.fire({
          icon: 'warning',
          text: 'Please enter valid email adress!'
      });
      return false;
    }
      

    const url = "http://localhost:8085/productManufacturer" + "/" +  props.manufacturer.productManufacturerID;
    const token = localStorage.getItem("token");
    const bearerToken = "Bearer " + token;

    fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: bearerToken,
      },
      body: JSON.stringify(manufacturerUpdate),
    })
      .then((responseFromServer) => {
        console.log(responseFromServer);
    })
    .catch((error) => {
        console.log(error);
    });

    props.onManufacturerUpdated(manufacturerUpdate);
  };

  return (
    <div className="reg">
        <h3>Modifying the existing Product Manufacturer</h3>
        <br/>
            <input
                type="text"
                value={formData.productManufacturerID} 
                name="productManufacturerID"
                placeholder="Product manufacturer ID"
                onChange={handleChange}
                disabled
                className="manufacturerID"
            />
            <br/>
            <input
                value={formData.manufacturerName}
                name="manufacturerName"
                type="text"
                placeholder="Manufacturer name"
                onChange={handleChange}
                className="manufacturerName">
            </input>
            <br/>
            <input
                value={formData.manufacturerPhoneNumber}
                name="manufacturerPhoneNumber"
                type="text"
                placeholder="Manufacturer phone number"
                onChange={handleChange}
                className="manufacturerPhoneNumber">
            </input>
            <br/>
            <input
                value={formData.manufacturerAddress}
                name="manufacturerAddress"
                type="text"
                placeholder="Manufacturer address"
                onChange={handleChange}
                className="manufacturerAddress">
            </input>
            <br/>
            <input
                value={formData.manufacturerCity}
                name="manufacturerCity"
                type="text"
                placeholder="Manufacturer city"
                onChange={handleChange}
                className="manufacturerCity">
            </input>
            <br/>
            <input
                value={formData.manufacturerCountry}
                name="manufacturerCountry"
                type="text"
                placeholder="Manufacturer country"
                onChange={handleChange}
                className="manufacturerCountry">
            </input>
            <br/>
            <input
                value={formData.manufacturerEmail}
                name="manufacturerEmail"
                type="text"
                placeholder="Manufacturer email"
                onChange={handleChange}
                className="manufacturerEmail">
            </input>
            <br/>
            <br/>
            <button onClick={() => props.onManufacturerUpdated(null)} className="cancel"> Cancel</button>
            <button onClick={handleSubmit} className="modifyBtn">Modify</button>
    </div>
  );
}
