import http from "../config";

const URL_ORDER = "orders";

class OrderService {

    getAll(params){
        return http.get(URL_ORDER + "/list", {
            params: params,
        });
    }
    getOrderItems(id){
        return http.get(URL_ORDER+"/"+ id)
    }
    updateStatus(id,status){
        return http.put(URL_ORDER+"/status/update?id="+id+"&status="+status)
    }
    updateCheckout(id,isCheck){
        return http.put(URL_ORDER+"/checkOut/update?id="+id+"&isCheck="+isCheck)
    }
    // delete(id){
    //     return axios.delete(URL_ORDER + "/delete/" + id)
    // }
    getAllAcount(params){
        return http.get("http://localhost:8888/api/v1/accounts/list",{
            params,
        });
    }
}

export default new OrderService();