<script setup>
//ref: biến phản hồi tự động cập nhap khi có thay đổi
import { ref, onMounted } from 'vue'
import api from '@/api/axios'
const students = ref([])
//ham goi api
//async/await: dung xử lý bất đồng bộ .giúp code
// dừng lại đợi khi có kq rồi chạy tiếp
const name = ref('')
const email =ref('')
const phone =ref('')
const avatar =ref(null)
const fetchStudents = async () => {
  try {
    //gửi yêu cầu tới backend
    const reponse = await api.get('/students_demo')
    students.value = reponse.data
  } catch (error) {
    console.log(error)
  }
}
const selectedFile=(event)=>{avatar.value=event.target.files[0]}
const addStudent = async () => {
  try {
    const response=await api.post('/students_demo/create',{
    name: name.value,
    email: email.value,
    phone: phone.value
    }
    )
    const studentId=response.data.id
    if(avatar.value && studentId){
    const formData= new FormData()
    formData.append('file',avatar.value)
    await api.post(`/students_demo/${studentId}/upload-avatar`,formData,{
    headers: { 'Content-Type': 'multipart/form-data' }
      })
    }
    name.value = ''
    email.value =''
    phone.value =''
    avatar.value =null 
    document.getElementById('avatarInput').value=''
    fetchStudents()
    alert("Đã thêm thành công")
  } catch (error) {
    console.log(error)
    alert("Thêm thất bại")
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
        <th>Avatar</th>
        <th>Thao tác</th>
      </tr>
      <tr v-for="student in students" :key="student.id">
        <td>{{ student.id }}</td>
        <td>{{ student.name }}</td>
        <td>{{ student.email }}</td>
        <td>{{ student.avatar }}</td>
        <td>
        <button @click="editStudents(student)">Sửa</button>
        <button @click="deleteStudents(student.id)">Xóa</button>
        </td>
      </tr>
    </table>
    <br/>
    <h3>Thêm sinh viên mới</h3>
    <input type="text" v-model="name" placeholder="Nhập tên" /><br />
    <input type="email" v-model="email" placeholder="Nhập email" /><br />
    <input type="text" v-model="phone" placeholder="Nhập số điện thoại" /><br />
    
    <label>Avatar: </label>
    <input type="file" id="avatarInput" @change="selectedFile" accept="image/*" /><br /><br />
    
    <button @click="addStudent">Thêm sinh viên</button>
  </div>
</template>
