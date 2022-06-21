import Applayout from "@/layout/Applayout";
const routes = [
    {
        path: '/categories',
        component: Applayout,
        name: 'categories',
        meta: {
            title: 'Categories',
            icon: 'menu-fold'
        },
        children: [
            {
                path: 'list',
                component: () => import('./ListCategory'),
                name: 'categoryList',
                meta: {
                    title: 'Categories List',
                }
            },
            {
                path: 'create',
                component:()=> import('./Created'),
                name:'createCategory',
                meta: {
                    title: 'Create Category'
                }
            },
            {
                path: 'edit/:id',
                component:()=> import('./edit'),
                name:'editCategory',
                meta: {
                    title: 'Edit Category',
                    hidden:'true'
                }
            },
            {
                path: 'detail/:id',
                component:()=> import('./Detail'),
                name:'detailCategory',
                meta: {
                    title: 'Detail Category',
                    hidden: 'true'
                }
            },

        ]
    },


]

export default routes;