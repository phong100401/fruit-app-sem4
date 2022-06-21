import Applayout from "../../layout/Applayout";

const routes =[
    {
        path:"/chart",
        component: Applayout,
        name: 'Chart',
        meta: {
            nametitle:'Chart',
            title: 'Chart',
            icon: 'line-chart',
        },
        children: [
            {
                path: '',
                component: () => import('./Chart'),
                name: 'DashBoard',
                meta: {
                    title: 'Chart',
                }
            },

        ]
    },

]



export default routes;