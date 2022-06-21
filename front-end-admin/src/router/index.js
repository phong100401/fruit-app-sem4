import Vue from 'vue'
import VueRouter from 'vue-router'
import {productsRoute} from "../pages/product";
import {categoriesRoute} from "../pages/category";
import {ordersRouter} from "../pages/order";
import {accountsRoute} from "../pages/account";
import {loginsRoute} from "../pages/login";
import {chartsRoute} from "../pages/chart";

Vue.use(VueRouter)

const routes = [
    ...accountsRoute,
    ...chartsRoute,
    ...productsRoute,
    ...categoriesRoute,
    ...ordersRouter,
    ...loginsRoute,




]

const router = new VueRouter({
    mode: 'history',
    routes
})

router.beforeEach((to, from, next) => {
    document.title = to.meta.title;
    let token = localStorage.getItem('access_token');
    if (to.name !== 'Login' && !token) {
        next({ name: 'Login' });
    }else if(to.name ==='Login' && token){
        localStorage.removeItem('access_token');
        next({name:'Login'})
    }
    else {
            next();
    }
})

export default router;