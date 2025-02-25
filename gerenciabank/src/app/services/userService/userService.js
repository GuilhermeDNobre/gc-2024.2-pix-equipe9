import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080",
})


class UserService {
    static login = async (email, password) => {
        try {
            const response = await api.post(`/users/login`, { email, password }, { withCredentials: true });
            if (response.status === 200) {
                // Login bem-sucedido
                localStorage.setItem('token', response.data.token)
                return { success: true };
            }
        } catch (error) {
            if (error.response && error.response.status === 401) {
                // Credenciais inválidas
                return { success: false, message: 'Usuário ou senha incorretos' };
            } else {
                // Outro erro
                return { success: false, message: 'Ocorreu um erro. Tente novamente mais tarde.' };
            }
        }
    }

    static register = async (data) => {
        
        try {
            const response = await api.post(`/users`, data, {
                headers: { "Content-Type": "application/json"}
            });
            if(response.status === 201){        
                console.log("Usuário criado com sucesso")
                return { success: true };
            }
            
        }
        catch(error) {
                console.log(error)
            }
    }   
    }

export default UserService