<template>
  <div id="login-form">
    <input ref="login-field" v-model="this.login"/>
    <input type="password" v-model="this.password"/>
    <button @click="enter">Войти</button>
    <div v-if="this.errorText">{{errorText}}</div>
    <div v-else>{{successText}}</div>
  </div>
</template>
<script lang="ts">
  import {Auth} from "@/generated-api/Auth";
  import {Response} from "@/generated-api/data-contracts";
  import {Options, Vue} from "vue-class-component";
  import {HttpResponse} from "@/generated-api/http-client";

  @Options( {
    name: "login-form",
    data() {
      return {
        login: "",
        password: "",
        errorText: "",
        successText: ""
      }
    },
    emits: {
      token: null
    },
    methods: {
      async enter() {
        new Auth().authUsingPost({
              login: this.login,
              password: this.password
            }
        ).then(this.afterFetch)
        .then(this.emitToken);
      },
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
      emitToken(token: string) {
        this.$emit('token', token);
      },
      afterFetch(result: HttpResponse<Response, void>): string | undefined {
        if (result.status !== 200) {
          Promise.reject();
          return;
        } else {
          return result.data.token;
        }
      },
      setErrorText(text: string) {
        this.errorText = text
        this.successText = "";
      },
      setSuccessText(text: string) {
        this.errorText = "";
        this.successText = text;
      }
    }
    })
  export default class LoginForm extends Vue {}
</script>