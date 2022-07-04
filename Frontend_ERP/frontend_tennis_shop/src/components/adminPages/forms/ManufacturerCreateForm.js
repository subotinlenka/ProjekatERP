import React, { useState } from "react";
import '../css/Buttons.css'
import Swal from "sweetalert2";
import validator from 'validator';

export default function CreateManufacturerForm(props) {
  const initialFormData = Object.freeze({
    manufacturerName: "",
    manufacturerPhoneNumber: "",
    manufacturerAddress: "",
    manufacturerCity: "",
    manufacturerCountry: "",
    manufacturerEmail: ""
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
    const manufacturerCreate = {
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
     
    const url = "http://localhost:8085/productManufacturer";
    const token = localStorage.getItem("token");
    const bearerToken = "Bearer " + token;

    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: bearerToken,
      },
      body: JSON.stringify(manufacturerCreate),
    })
      .then((responseFromServer) => {
        console.log(responseFromServer);
      })
      .catch((error) => {
        console.log(error);
      });

    props.onManufacturerCreated(manufacturerCreate);
  };

  return (
    <div>
        <div className="reg">
            <h3>Adding new Product Manufacturer</h3>
                <input
                    value={formData.manufacturerName}
                    name="manufacturerName"
                    type="text"
                    placeholder="Manufacturer name"
                    onChange={handleChange}
                    className="manufacturerName"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.manufacturerPhoneNumber}
                    name="manufacturerPhoneNumber"
                    type="text"
                    placeholder="Manufacturer phone number"
                    onChange={handleChange}
                    className="manufacturerPhoneNumber"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.manufacturerAddress}
                    name="manufacturerAddress"
                    type="text"
                    placeholder="Manufacturer address"
                    onChange={handleChange}
                    className="manufacturerAddress"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.manufacturerCity}
                    name="manufacturerCity"
                    type="text"
                    placeholder="Manufacturer city"
                    onChange={handleChange}
                    className="manufacturerCity"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.manufacturerCountry}
                    name="manufacturerCountry"
                    type="text"
                    placeholder="Manufacturer country"
                    onChange={handleChange}
                    className="manufacturerCountry"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.manufacturerEmail}
                    name="manufacturerEmail"
                    type="text"
                    placeholder="Manufacturer email"
                    onChange={handleChange}
                    className="manufacturerEmail"
                    disabled={false}>
                </input>
            <br/>
            <br/>
            <button onClick={() => props.onManufacturerCreated(null)} className="cancel"> Cancel</button>
            <button onClick={handleSubmit} className="addBtn">Add</button>
        </div>
    </div>
  );
}
