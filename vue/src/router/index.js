import Vue from 'vue'
import Router from 'vue-router'
//import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import PetsView from '@/views/Pets/PetsView';
import PetAttributes from '@/views/Pets/PetAttributes.vue';
import Volunteers from '@/views/VolunteersView.vue';
import AddVolunteers from '@/views/AddVolunteersView.vue';
import SavePetView from '@/views/Pets/SavePetView.vue';
import Admin from '@/views/AdminView.vue';


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/About.vue'),
      
      },
    {
      path: '/home',
      name: 'home',
      component: () => import('../views/Home.vue'),
      
      
       meta: {
          requiresAuth: false
       }
    },
    {
      path: '/',
      name: 'home',
      component: () => import('../views/Home.vue'),

      meta: {
        requiresAuth: false
     }
    },

     {
       path: "/login",
      name: "login",
      component: Login,
     
       meta: {
        requiresAuth: false
      }
     },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    //admin router path to approve volunteers
    {
      path: '/admin',
      name: 'admin',
      component: Admin

    },
    ///////////////////
    {
      path: '/volunteers',
      name: 'volunteers',
      component: Volunteers
    },
    {
      path: '/volunteer/apply',
      name: 'addVolunteer',
      component: AddVolunteers
    },
    //pet router paths-------------------
    { path: '/pets/edit/:id',
      name: 'edit',
      component: SavePetView

    },
    {
      path: '/pets/create',
      name: 'create',
      component: SavePetView
    
    },
    { 
      path: '/pets',
      name: 'pets-view',
      component: PetsView
    },
    {
      path: '/pets/:id',
      name: 'attributes',
      component: PetAttributes
    },
    {
      path: '/changepassword',
      name: 'changepassword',
      component: () => import('../views/ChangePassword.vue'),
      
      
    },
   
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
