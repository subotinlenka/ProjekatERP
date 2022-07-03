import axios from "axios";
import { token } from "./auth-service";

export const getUserById = async (id) => {
    const {data} = await axios.get('http://localhost:8085/user/' + id, { headers: {"Authorization" : `Bearer ${token()}`} });
    return data;
}

export const getUserByUsername = async (userName) => {
    const {data} = await axios.get('http://localhost:8085/user/userName/' + userName, { headers: {"Authorization" : `Bearer ${token()}`} });
    return data;
}


export const getUsers = async () => {
    const {data} = await axios.get('http://localhost:8085/users', { headers: {"Authorization" : `Bearer ${token()}`} });
    return data;
}

export const updateUser = async (user, id) => {
    return await axios.put('http://localhost:8085/user/' + user, id, { headers: {"Authorization" : `Bearer ${token()}`} });
}