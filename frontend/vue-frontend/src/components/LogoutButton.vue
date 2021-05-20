<template>
<img src="logout.svg" alt="Выход" @click="logout" width="20">
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import {Store, useStore} from "@/store/index";

@Options({
  name: "logout-button"
})
export default class LogoutButton extends Vue{
  store: Store = useStore();
  logout(){
    this.store.dispatch('LOGOUT', undefined);
  }
}
</script>

<style scoped>

</style>