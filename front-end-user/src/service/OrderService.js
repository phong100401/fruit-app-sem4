import http from "../config";

const URL_CATEGORIES = "orders";

class OrderService {

    getAll(){
        return http.get(URL_CATEGORIES + "/cart1");
    }
    addToCart(body){
        return http.post(URL_CATEGORIES + "/addToCart1" ,body
        );
    }
    removeItem(id){
        return http.post(URL_CATEGORIES + "/remove1?productId="+id);
    }
    proceedOrder(orderDto){
        return http.post(URL_CATEGORIES + "/proceedOrder"
            ,orderDto
        );
    }
    submitOrder(body){
        return http.post(URL_CATEGORIES + "/proceedOrder1",body
        );
    }
    getvalue(body){
        return http.post(URL_CATEGORIES+"/controlvalue1",body);
    }
    getAllOrder(params){
        return http.get(URL_CATEGORIES + "/list", {
            params: params,
        });
    }
    getAllAcount(params){
        return http.get("http://localhost:8888/api/v1/accounts/list",{
            params,
        });
    }

}

export default new OrderService();