<script setup>
//ref: biến phản hồi tự động cập nhap khi có thay đổi
import { ref, onMounted } from 'vue'
import api from '@/api/axios'
const students = ref([])
//ham goi api
//async/await: dung xử lý bất đồng bộ .giúp code
// dừng lại đợi khi có kq rồi chạy tiếp
const fetchStudents = async () => {
  try {
    //gửi yêu cầu tới backend
    const reponse = await api.get('/students_demo')
    students.value = reponse.data
  } catch (error) {
    console.log(error)
  }
}
//khi giao diện load xong (onMounted) sẽ tự động thực hiện lệnh
onMounted(() => {
  fetchStudents()
})
</script>
<template>
  <div>
    <h1>Danh sách sinh viên</h1>
    <table border="1">
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
      </tr>
      <tr v-for="student in students" :key="student.id">
        <td>{{ student.id }}</td>
        <td>{{ student.name }}</td>
        <td>{{ student.email }}</td>
      </tr>
    </table>
  </div>
</template>
