import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/login'},
    {
      path: '/manager',
      name: 'Manager',
      component: () => import('@/views/Manager.vue'),
      redirect: '/manager/login',
      children: [
        { path: 'password', meta: { name: '修改密码' }, component: () => import('@/views/manager/Password.vue')},
        { path: 'home', meta: { name: '系统首页' }, component: () => import('@/views/manager/Home.vue')},
		{ path: 'admin', meta: { name: '管理员' }, component: () => import("@/views/manager/Admin.vue") },
		{ path: 'employee', meta: { name: '员工' }, component: () => import("@/views/manager/Employee.vue") },
		{ path: 'employeeinfo', meta: { name: '员工信息' }, component: () => import("@/views/manager/Employeeinfo.vue") },
		{ path: 'customerinfo', meta: { name: '客户信息' }, component: () => import("@/views/manager/Customerinfo.vue") },
		{ path: 'supplierinfo', meta: { name: '供应商信息' }, component: () => import("@/views/manager/Supplierinfo.vue") },
		{ path: 'druginfo', meta: { name: '药品信息' }, component: () => import("@/views/manager/Druginfo.vue") },
		{ path: 'purchaseinfo', meta: { name: '进货信息' }, component: () => import("@/views/manager/Purchaseinfo.vue") },
		{ path: 'purchaseReporter', meta: { name: '进货报表' }, component: () => import("@/views/manager/PurchaseReporter.vue") },
		{ path: 'saleinfo', meta: { name: '销售信息' }, component: () => import("@/views/manager/Saleinfo.vue") },
		{ path: 'saleReporter', meta: { name: '销售报表' }, component: () => import("@/views/manager/SaleReporter.vue") },
		{ path: 'inventoryinfo', meta: { name: '库房管理' }, component: () => import("@/views/manager/Inventoryinfo.vue") },
		{ path: 'pAdmin', meta: { name: '个人信息' }, component: () => import("@/views/manager/pAdmin.vue")  },
		{ path: 'pEmployee', meta: { name: '个人信息' }, component: () => import("@/views/manager/pEmployee.vue")  },

      ]
    },
    { path: '/login', component: () => import('@/views/Login.vue')},
	{ path: '/register', component: () => import("@/views/Register.vue") },
    { path: '/404', component: () => import('@/views/404.vue')},
    { path: '/:pathMatch(.*)', redirect: '/404', hidden: true }
  ]
})

export default router
