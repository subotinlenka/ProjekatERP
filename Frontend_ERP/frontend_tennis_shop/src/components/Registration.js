import React from 'react';
import "../css/Registration.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Swal from "sweetalert2";
import validator from 'validator';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";


const Registration = () => {
    const [userUserName, setUserName] = useState("");
    const [userPassword, setPassword] = useState("");
    const [userFirstName, setFirstName] = useState("");
    const [userLastName, setLastName] = useState("");
    const [userEmail, setEmail] = useState("");
    const [userPhoneNumber, setPhoneNumber] = useState("");
    const [userAddress, setAddress] = useState("");
    const [userCity, setCity] = useState("");
    const [userCountry, setCountry] = useState("");
    const [userDateOfBirth, setDateOfBirth] = useState("");
    const [isPending, setIsPending] = useState(false);
    const history = useNavigate();

    const Validate = () => {
        if (userFirstName === "") {
            Swal.fire({
                icon: 'warning',
                text: 'First name is required field and must be filled!'
            });
            return false;
        }

        else if(userLastName === "") {
            Swal.fire({
                icon: 'warning',
                text: 'Last name is required field and must be filled!'
            });
            return false;
        }
 
        else if(userAddress === "") {
            Swal.fire({
                icon: 'warning',
                text: 'Address is required field and must be filled!'
            });
            return false;
        }

        else if(userCity === "") {
            Swal.fire({
                icon: 'warning',
                text: 'City is required field and must be filled!'
            });
            return false;
        }

        else if(userCountry === "") {
            Swal.fire({
                icon: 'warning',
                title: 'Oops...',
                text: 'Country is required field and must be filled!',
            });
            return false;
        }

        else if(userDateOfBirth === "") {
            Swal.fire({
                icon: 'warning',
                text: 'Date of birth is required field and must be filled!'
            });
            return false;
        }

        else if(userAddress === "") {
            Swal.fire({
                icon: 'warning',
                text: 'Address is required field and must be filled!'
            });
            return false;
        }

        else if(userEmail === "") {
            Swal.fire({
                icon: 'warning',
                text: 'Email is required field and must be filled!'
            });
            return false;
        }

        else if (!validator.isEmail(userEmail)) {
            Swal.fire({
                icon: 'warning',
                text: 'Please enter valid email adress!'
            });
            return false;
        }

        else if(userPhoneNumber === "") {
            Swal.fire({
                icon: 'warning',
                text: 'Phone number is required field and must be filled!'
            });
            return false;
        }

        else if(userUserName === "") {
            Swal.fire({
                icon: 'warning',
                text: 'Username is required field and must be filled!'
            });
            return false;
        }

        else if(userPassword === "") {
            Swal.fire({
                icon: 'warning',
                text: 'Password is required field and must be filled!'
            });
            return false;
        }

        else if (userPassword.length < 8) {
            Swal.fire({
                icon: 'warning',
                text: 'Password must have at least 8 characters!'
            });
            return false;
        }

        return true;
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        if (!Validate())
            return;
        setIsPending(true);
        const signUp = {
            "userUserName": userUserName,
            "userPassword": userPassword,
            "userEmail": userEmail,
            "userPhoneNumber": userPhoneNumber,
            "userFirstName": userFirstName,
            "userLastName": userLastName,
            "userAddress": userAddress,
            "userCity": userCity,
            "userCountry": userCountry,
            "userDateOfBirth": userDateOfBirth,
        };
        console.log(signUp);
        axios.post('http://localhost:8085/auth/signUp', signUp).then(res => {
            if (res.status === 201) {
                setIsPending(false);
                Swal.fire({
                    icon: 'success',
                    text: "Registration has been successfull, please log in!"
                });
                history('/');
            } else {
                setIsPending(false);
                console.log(res.response.data.message);
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: res.data.response.message,
                });
            }
        }).catch((error) => {
            if( error.response ){
                setIsPending(false);
                console.log(error.response.data.message);
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: error.response.data.message,
                });
            }
        });;
    }

    return (
        <div className="div-signUp">
            <h2 className='style'>Registration of the new User</h2>
            <form style={{ maxWidth: "50%", margin: "auto"}}>
            <div className="mb-3">
                    <label className="form-label">First name</label>
                    <input value={userFirstName} onChange={(e) => setFirstName(e.target.value)} required type="string" className="form-control" id="InputFirst_name" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Last name</label>
                    <input value={userLastName} onChange={(e) => setLastName(e.target.value)} required type="string" className="form-control" id="InputLast_name" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Address</label>
                    <input value={userAddress} onChange={(e) => setAddress(e.target.value)} required type="address" className="form-control" id="InputAddress" />
                </div>
                <div className="mb-3">
                    <label className="form-label">City</label>
                    <input value={userCity} onChange={(e) => setCity(e.target.value)} required type="city" className="form-control" id="InputCity" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Country</label>
                    <input value={userCountry} onChange={(e) => setCountry(e.target.value)} required type="country" className="form-control" id="InputCountry" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Date of birth</label>
                    <DatePicker dateFormat="yyyy-MM-dd" selected={userDateOfBirth} onChange={(date) => setDateOfBirth(date)} className="form-control" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input value={userEmail} onChange={(e) => setEmail(e.target.value)} required type="string" className="form-control" id="InputEmail" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Phone number</label>
                    <input value={userPhoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} required type="string" className="form-control" id="InputPhone_number" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Username</label>
                    <input value={userUserName} onChange={(e) => setUserName(e.target.value)} required type="text" className="form-control" id="InputUsername" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input value={userPassword} onChange={(e) => setPassword(e.target.value)} required type="password" className="form-control" id="InputPassword" />
                </div>
                
                <div className="mb-3">
                    {!isPending && <span className="right">
                        <Link to="#" onClick={(e) => onSubmit(e)} type="submit" className="btn-primary" aria-orientation='right'>Sign Up</Link>
                    </span>}
                    {isPending && <label>Signing up...</label>}
                </div>
            </form>
        </div>
    );
}

export default Registration;