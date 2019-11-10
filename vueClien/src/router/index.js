import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
     redirect:'login'
    },
    {
      path:"/login",
      name: 'Login',
      component: Login
    },
    {
      path: '/index',
      name: 'HelloWorld',
      component: HelloWorld,
      //设置此路由需要登陆权限才能访问
      meta: { requireAuth: true }
    }
  ]
})
//
// to: Route: 即将要进入的目标 路由对象 
// from: Route: 当前导航正要离开的路由 
// next: Function: 一定要调用该方法来 resolve 这个钩子。执行效果依赖 next 方法的调用参数。 
// next(): 进行管道中的下一个钩子。如果全部钩子执行完了，则导航的状态就是 confirmed （确认的）。
router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {  // 判断该路由是否需要登录权限
      if (sessionStorage.getItem('token')) {  // sessionStorage 获取当前的token是否存在
          next();
      }
      else {
          next({
              path: '/login',
              query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
          })
      }
  }
  else {
      next();
  }
})
export default router;
