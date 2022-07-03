import { Link } from "react-router-dom";
import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Swal from "sweetalert2";
import image from "../logIn.png";

const LogIn = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [isPending, setIsPending] = useState(false);
    const history = useNavigate();

    const Validate = () => {
        if (username === "" || password === "") {
            Swal.fire({
                icon: 'warning',
                text: 'Username and Password are required fields!'
            });
            return false;
        }
        return true;
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        if (!Validate())
            return;
        const login = {
            "username": username,
            "password": password
        };
        setIsPending(true);
        axios.post('http://localhost:8085/auth/logIn', login).then(res => {
            console.log(res);
            if (res.status === 200) {
                setIsPending(false);
                localStorage.setItem('accessToken', res.data.accessToken);
                console.log(localStorage.getItem('accessToken'));
                const token  = JSON.parse(atob( res.data.accessToken.split('.')[1])).sub;
                console.log(token);
                history('/');
            } else {
                setIsPending(false);
                Swal.fire({
                    icon: 'error',
                    title: 'Error occured while logging in!',
                    text: res.data.error,
                });
            }
        }).catch((error) => {
            if( error.response ){
                setIsPending(false);
                console.log(error.response.data.message);
                Swal.fire({
                    icon: 'error',
                    text: "Incorrect login data, try again!"
                });
            }
        });
    }

    return (
        <div className="div-login">
            <form style={{ maxWidth: "50%", margin: "auto" }}>
            <img src={image} style={{width: "25%", height: "25%", alignContent: "center"}} />
                <div className="mb-3">
                    <label className="form-label">Username</label>
                    <input value={username} onChange={(e) => setUsername(e.target.value)} required type="text" className="form-control" id="InputUsername" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input value={password} onChange={(e) => setPassword(e.target.value)} required type="password" className="form-control" id="InputPassword" />
                </div>
                <div className="mb-3">
                    {!isPending && <span className="right">
                        <Link to="#" onClick={(e) => onSubmit(e)} type="submit" className="btn-primary">Log In</Link>
                    </span>}
                    {isPending && <label>Logging in...</label>}
                </div>
            </form>
        </div>
    );
}

export default LogIn;