import http from "../config";

const URL_CATEGORIES = "categories";


class CategoryService {

    getAll(params){
        return http.get(URL_CATEGORIES + "/list", {
            params: params,
        });
    }
    save(body){
        return http.post(URL_CATEGORIES + "/create",body)
    }
    detail(id){
        return http.get(URL_CATEGORIES + "/detail/"+ id)
    }
    edit(id, body){
        return http.put(URL_CATEGORIES + "/update/"+id,body)
    }
    delete(id){
        return http.delete(URL_CATEGORIES + "/delete/" + id)
    }

}

export default new CategoryService();