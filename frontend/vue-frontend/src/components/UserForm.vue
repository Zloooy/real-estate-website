<template>
  <div class="user-form" v-if="user && userRoles.length">
   <input placeholder="Логин" v-model="user.login">
   <input placeholder="Пароль" v-model="user.password">
    <dropdown-selector
        nameFieldTitle="roleName"
        :firstSelectedIndex="findFirstSelectionIndex()"
        :options="userRoles"
        @select="onRoleSelect"
    />
    <button @click="emitUser">
      Записать
    </button>
  </div>
</template>
<script lang="ts">
import {Vue, Options} from 'vue-class-component';
import {UserDto, UserRoleDto} from '@/generated-api/data-contracts';
import {useStore, Store} from "@/store";
import DropdownSelector from "@/components/DropdownSelector.vue";
@Options({
    name: "user-form",
  components: {
      DropdownSelector
  },
    props:['user'],
    emits:['user-changed'],
    computed:{
      userRoles(): UserRoleDto[] {
        return this.store.getters.userRoles;
      }
  }
}
)
export default class UserForm extends Vue {
  store: Store = useStore();
 $props!: {
   user: UserDto;
 }
 user: UserDto | null = null;
 mounted(){
   console.debug(JSON.stringify(this.$props.user));
 }
 findFirstSelectionIndex(): number{
   return this.store.getters.userRoles.findIndex(role=>this.user?.roleId == role.id);
 }
 onRoleSelect(role: UserRoleDto){
   if (this.user) {
     this.user.roleId = role.id;
   }
 }
 emitUser(){
   this.$emit('user-changed', this.user)
 }
}
</script>
<style scoped>
.user-form {
  display: inline-block;
}
</style>
