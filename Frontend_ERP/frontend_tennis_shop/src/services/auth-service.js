import { getUserByUsername } from "./user-service";

export const token = () => localStorage.getItem('accessToken');
export const username = () => !!token() ? JSON.parse(window.atob(token().split('.')[1])).sub : '';
export const role = async () => getUserByUsername(username());