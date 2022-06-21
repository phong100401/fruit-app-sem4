import http from "../config";

const URL_PRODUCTS = "products";

class ProductService {

    getAll(params){
        return http.get(URL_PRODUCTS + "/list", {
            params: params,
        });
    }
    save(body){
        return http.post(URL_PRODUCTS + "/create",body)
    }
    detail(id){
        return http.get(URL_PRODUCTS + "/detail/"+ id)
    }
    edit(id, body){
        return http.put(URL_PRODUCTS + "/update/"+id,body)
    }
    delete(id){
        return http.delete(URL_PRODUCTS + "/delete/" + id)
    }
    getchart(){
        return http.get(URL_PRODUCTS+"/dataChart")
    }
    getlinechart(){
        return http.get(URL_PRODUCTS+"/totalChart")
    }


}

export default new ProductService();