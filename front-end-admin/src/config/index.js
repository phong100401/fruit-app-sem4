import axios from "axios";
import router from "../router";

const configAxios= {
    baseURL:"http://localhost:8888/api/v1/", //baseURL` sẽ được gán vào trước url khi url là đường dẫn tương đối.
    timeout: 2*60*1000 //// `timeout` chỉ định số mili giây khi request vượt quá thời gian truy cập và bị hủy bỏ ở đây là 2 phút

}
const http = axios.create(configAxios) // tạo ra một đối tượng axios với các config

const handlerError = (error)=>{
    if(error.response.status === 401){
        localStorage.removeItem('access_token');
        router.push({name:'Login'})
        var errorrole = document.getElementById('error-role')
        errorrole.innerText="Sai tài khoản hoặc mật khẩu"
    }
    return Promise.reject(error.response) //reject là hàm sẽ được gọi khi có lỗi xảy ra
}
http.interceptors.request.use((config) => {
    let token = localStorage.getItem('access_token');
    if (token) {
        config.headers = {
            ...config.headers,
            'Authorization': `Bearer ${token}`
        };
    }
    return config;
}, handlerError)

http.interceptors.response.use((res) => {
    return Promise.resolve(res); //resolve là hàm sẽ được gọi khi promise hoàn thành
}, handlerError)


export default http;