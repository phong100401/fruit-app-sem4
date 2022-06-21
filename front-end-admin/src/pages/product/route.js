import Applayout from "@/layout/Applayout";
const routes = [
    {
        path: '/products',
        component: Applayout,
        name: 'products',
        meta: {
            title: 'Products',
            icon: 'bars'
        },

        children: [
            {
                path: 'list',
                component: () => import('./List'),
                name: 'productList',
                meta: {

                    title: 'Products List',
                }
            },
            {
                path: 'create',
                component:()=> import('./Created'),
                name:'createProduct',
                meta: {
                    title: 'Create Product'
                }
            },
            {
                path: 'detail/:id',
                component:()=> import('./Detail'),
                name:'detailProduct',
                meta: {
                    title: 'Detail Product',
                    hidden: 'true'
                }
            },
            {
                path: 'edit/:id',
                component:()=> import('./edit'),
                name:'editProduct',
                meta: {
                    title: 'Edit Product',
                    hidden:'true'
                }
            },
        ]
    },


]

export default routes;