import http from "../config";

const URL_ACCOUNT = "accounts";
class AccountService{

    getAll(params){
        return http.get(URL_ACCOUNT + "/list", {
            params: params,
        });
    }
}
export default new AccountService();