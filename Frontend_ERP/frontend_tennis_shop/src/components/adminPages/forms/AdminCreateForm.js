import React, { useState } from "react";
import '../css/Buttons.css'
import Swal from "sweetalert2";
import validator from 'validator';

export default function AdminCreateForm(props) {
  const initialFormData = Object.freeze({
    userFirstName: "",
    userLastName: "",
    userDateOfBirth: "",
    userPhoneNumber: "",
    userEmail: "",
    userAddress: "",
    userCity: "",
    userCountry: "",
    userUserName: "",
    userPassword: ""
   
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
    const adminCreate = {
        userFirstName: formData. userFirstName,
        userLastName: formData.userLastName,
        userDateOfBirth: formData.userDateOfBirth,
        userPhoneNumber: formData.userPhoneNumber,
        userEmail: formData.userEmail,
        userAddress: formData.userAddress,
        userCity: formData.userCity,
        userCountry: formData.userCountry,
        userUserName: formData.userUserName,
        userPassword: formData.userPassword
    };

    if (formData.userFirstName === "") {
         Swal.fire({
            icon: 'warning',
            text: 'Admin first name is required field and must be filled!'
          });
        return false;
    }

    else if (formData.userLastName === "") {
        Swal.fire({
           icon: 'warning',
           text: 'Admin last name is required field and must be filled!'
         });
       return false;
    }

    else if(formData.userDateOfBirth === "") {    
          Swal.fire({
            icon: 'warning',
            text: 'Admin date of birth is required field and must be filled!'
          });
        return false;
    }

    else if(formData.userPhoneNumber === "") {    
        Swal.fire({
          icon: 'warning',
          text: 'Admin phone number is required field and must be filled!'
        });
      return false;
    }

    else if(formData.userEmail === "") {
      Swal.fire({
          icon: 'warning',
          text: 'Admin email is required field and must be filled!'
      });
      return false;
    }

    else if (!validator.isEmail(formData.userEmail)) {
        Swal.fire({
            icon: 'warning',
            text: 'Please enter valid email adress!'
        });
        return false;
    }

    else if(formData.userAddress === "") {
        Swal.fire({
            icon: 'warning',
            text: 'Admin address is required field and must be filled and must be filled!'
        });
        return false;
    }

    else if(formData.userCity === "") {
        Swal.fire({
            icon: 'warning',
            text: 'Admin city is required field and must be filled!',
        });
        return false;
    }

    else if(formData.userCountry === "") {
        Swal.fire({
            icon: 'warning',
            text: 'Admin country is required field and must be filled!',
        });
        return false;
    }

    else if(formData.userUserName === "") {
        Swal.fire({
            icon: 'warning',
            text: 'Admin username is required field and must be filled!',
        });
        return false;
    }

    else if(formData.userPassword === "") {
        Swal.fire({
            icon: 'warning',
            text: 'Password is required field and must be filled!'
        });
        return false;
    }

    else if (formData.userPassword.length < 8) {
        Swal.fire({
            icon: 'warning',
            text: 'Password must have at least 8 characters!'
        });
        return false;
    }

    const url = "http://localhost:8085/auth/admin";
    const token = localStorage.getItem("token");
    const bearerToken = "Bearer " + token;

    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: bearerToken,
      },
      body: JSON.stringify(adminCreate),
    })
      .then((responseFromServer) => {
        console.log(responseFromServer);
      })
      .catch((error) => {
        console.log(error);
      });

    props.onAdminCreated(adminCreate);
  };

  return (
    <div>
        <div className="reg">
            <h3>Adding new Admin of the web shop</h3>
                <input
                    value={formData.userFirstName}
                    name="userFirstName"
                    type="text"
                    placeholder="Admin first name"
                    onChange={handleChange}
                    className="userFirstName"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.userLastName}
                    name="userLastName"
                    type="text"
                    placeholder="Admin last name"
                    onChange={handleChange}
                    className="userLastName"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.userDateOfBirth}
                    name="userDateOfBirth"
                    type="text"
                    placeholder="Admin date of birth"
                    onChange={handleChange}
                    className="userDateOfBirth"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.userPhoneNumber}
                    name="userPhoneNumber"
                    type="text"
                    placeholder="Admin phone number"
                    onChange={handleChange}
                    className="userPhoneNumber"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.userEmail}
                    name="userEmail"
                    type="text"
                    placeholder="Admin email"
                    onChange={handleChange}
                    className="userEmail"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.userAddress}
                    name="userAddress"
                    type="text"
                    placeholder="Admin address"
                    onChange={handleChange}
                    className="userAddress"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.userCity}
                    name="userCity"
                    type="text"
                    placeholder="Admin city"
                    onChange={handleChange}
                    className="userCity"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.userCountry}
                    name="userCountry"
                    type="text"
                    placeholder="Admin country"
                    onChange={handleChange}
                    className="userCountry"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.userUserName}
                    name="userUserName"
                    type="text"
                    placeholder="Admin username"
                    onChange={handleChange}
                    className="userUserName"
                    disabled={false}>
                </input>
                <br/>
                <input
                    value={formData.userPassword}
                    name="userPassword"
                    type="text"
                    placeholder="Admin password"
                    onChange={handleChange}
                    className="userPassword"
                    disabled={false}>
                </input>
            <br/> 
            <br/>
            <button onClick={() => props.onAdminCreated(null)} className="cancel"> Cancel</button>
            <button onClick={handleSubmit} className="addBtn">Add</button>
        </div>
    </div>
  );
}
