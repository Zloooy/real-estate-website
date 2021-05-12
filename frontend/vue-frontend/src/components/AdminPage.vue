<template>
  <div class="admin-page" v-if="userRoles">
    <div class="user-form">
    <user-form
        v-if="selectedUser && userRoles"
        :user="selectedUser"
        @user-changed="saveUser"
    />
    </div>
    <table>
      <thead>
      <th>Логин</th>
      <th>Роль</th>
      </thead>
      <tr v-for="user in users" :key="user.id" @click="selectUser(user)">
       <td>{{user.login}}</td>
        <td>{{findUserRole(user.roleId)}}</td>
      </tr>
    </table>
    <button @click="createUser">
      Добавить
    </button>
  </div>
</template>
<script lang="ts">
import {Vue, Options} from 'vue-class-component';
import {UserDto} from '@/generated-api/data-contracts';
import {Store, useStore} from '@/store';
import UserForm from '@/components/UserForm.vue';
@Options(
    {
      components: {
        UserForm
      },
      name: "admin-page",
      computed: {
        users() {
          return this.store.getters.users;
        },
        userRoles(){
          return this.store.getters.userRoles;
        },
    },
}
)
export default class AdminPage extends Vue {
  selectedUser: UserDto | null = null;
  store: Store = useStore();
  created(){
    this.store.dispatch('GET_USERS', undefined);
    this.store.dispatch('GET_USER_ROLES', undefined);
  }
  selectUser(user: UserDto){
    this.selectedUser = Object.assign({}, user);
  }
  findUserRole(id: number){
    return this.store.getters.userRoles.find(role=>role.id == id)?.roleName;
  }
  createUser(){
    this.selectedUser = {id: -1, roleId: this.store.getters.userRoles[0].id} as UserDto;
  }
  saveUser(user: UserDto){
    if (user.id == -1){
    this.store.dispatch('CREATE_USER', user);
    }
    else {
      this.store.dispatch('SAVE_USER', user);
    }
  }
}
</script>
<style scoped>
.admin-page {
  text-align: center;
}
.admin-page > table {
  border-spacing: 0;
  border: 1px solid black;
  background-color: #E6EDF2;
  margin: 0px auto;
}
.user-form {
  padding-top: 10px;
  height: 100px;
}
.admin-page > table > tr:hover {
  color: lightsalmon;
}
td, th {
  border: 1px solid black;
  text-align: left;
}
</style>
