import { createRouter, createWebHistory } from 'vue-router'
import Student_demoView from '@/views/Student_demoView.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path:"/",//đường dẫn gốc
      component: Student_demoView,//khi vào đường dẫn hãy lôi giao diện trong componenet ra
    },
  ],
});

export default router;
