import Applayout from "@/layout/Applayout";
const routes = [
    {
        path: '/orders',
        component: Applayout,
        name: 'orders',
        meta: {
            title: 'Order',
            icon: 'menu-fold'
        },
        children: [
            {
                path: 'list',
                component: () => import('./List'),
                name: 'OrderList',
                meta: {
                    title: 'Orders List',
                }
            },
            {
                path: 'detail/:id',
                component: () => import('./OrderDetail'),
                name: 'OrderDetail',
                meta: {
                    title: 'Orders Detail',
                    hidden:'true'
                }
            },
        ]
    }
]

export default routes;