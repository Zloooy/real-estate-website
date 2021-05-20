<template>
  <div id="login-form">
    <div>
    <input ref="login-field" v-model="this.login"/>
    </div>
    <div>
    <input type="password" v-model="this.password"/>
    </div>
    <button @click="enter">Войти</button>
    <div v-if="errorT">{{errorT}}</div>
    <div v-else>{{successText}}</div>
  </div>
</template>
<script lang="ts">
  import {Options, Vue} from "vue-class-component";
  import {useStore, Store} from "@/store";

  @Options( {
    name: "login-form",
    data() {
      return {
        store: useStore()
      }
    },
    methods: {
      onNetworkError(error: Error) {
        console.debug(error);
        this.setErrorText("Ошибка соединения. Повторите попытку позже");
      },
      onMatchCredentials() {
        this.setSuccessText("Учётные данные верны");
      },
      onWrongCredentials() {
        this.setErrorText("Неверный логин или пароль");
      },
    },
    computed: {
      authorization_set() {
        return this.store.getters.authorization_set;
      }
    },
    watch: {
      authorization_set(newVal) {
        if (newVal) {
          this.errorT = "";
          if (this.store.getters.CAN_MANAGE_USERS)
              this.$router.push("/admin_panel")
          else
            this.$router.push("/");
        }
        else {
          this.errorT = "Неверный пароль";
        }
      }
    }
    })
  export default class LoginForm extends Vue {
   store: Store = useStore();
   login: string = "";
   password: string = "";
   errorT: string = "";
   successText: string = "";
   enter(){
     this.store.dispatch('GET_AUTH_TOKEN', {login: this.login, password: this.password});
   }
  }
</script>

<style>
#login-form {
  display: block;
  text-align: center;
}
</style>