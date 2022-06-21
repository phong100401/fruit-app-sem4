import Applayout from "@/layout/Applayout";

const routes = [
    {
        path: '/accounts',
        component: Applayout,
        name: 'account',
        meta: {
            title: 'User',
            icon: 'team'
        },
        children: [
            {
                path: 'list',
                component: () => import('./List'),
                name: 'List',
                meta: {
                    title: 'List',
                }
            },

        ]
    }
]

export default routes;