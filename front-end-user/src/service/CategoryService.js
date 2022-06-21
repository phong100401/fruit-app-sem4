import http from "../config";

const URL_CATEGORIES = "categories";

class CategoryService {

    getAll(){
        return http.get(URL_CATEGORIES + "/list", {
        });
    }
    detail(id){
        return http.get(URL_CATEGORIES + "/detail/"+ id)
    }

}

export default new CategoryService();