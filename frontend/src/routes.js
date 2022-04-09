import { createRouter, createWebHistory } from 'vue-router';
import HomePage from './components/HomePage.vue';
import SignUp from './components/SignUp.vue';
import AfterLogin from './components/AfterLogin.vue';
import AddStudents from './components/AddStudents.vue';
import ManageStudents from './components/ManageStudents.vue';
const routes = [
    {
        name: 'HomePage',
        component: HomePage,
        path: '/'
    },
    {
        name: 'SignUp',
        component: SignUp,
        path: '/signUp'
    },
    {
        name: 'AfterLogin',
        component: AfterLogin,
        path: '/afterLogin'
    },
    {
        name: 'AddStudents',
        title: 'Add',
        component: AddStudents,
        path: '/addStudents'
    },
    {
        name: 'ManageStudents',
        component: ManageStudents,
        path: '/manageStudents'
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    document.title=to.name
    next()
})

export default router;