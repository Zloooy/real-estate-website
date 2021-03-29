<template>
  <div id="login-form">
 <input ref="login-field" v-model="login"/>
 <input type="password" v-model="password"/>
  <button @click="enter">Войти</button>
    <div v-if="this.errorText">{{errorText}}</div>
    <div v-else>{{successText}}</div>
  </div>
</template>
<script>
  export default {
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
      enter(event) {
        console.debug("pressed enter", event);
        if (this.login && this.password) {
          fetch("http://localhost:8009/auth",
        {
              "method": "POST",
              "headers": {
                "content-type": "application/json"
              },
              "body": JSON.stringify({
                "login": this.login,
                "password": this.password
            })
          }).then(this.afterPost)
              .then(this.emitToken, this.onWrongCredentials)
          .catch(this.onNetworkError);
        }
      },
      onNetworkError(error) {
        console.debug(error);
        this.setErrorText("Ошибка соединения. Повторите попытку позже");
      },
      onWrongCredentials() {
        this.setErrorText("Неверный логин или пароль");
      },
      emitToken(token) {
        this.$emit('token', token.token);
      },
      afterPost(postResult) {
        console.debug("got body: ", postResult.body);
        console.debug(postResult.headers);
        switch (postResult.status) {
          case (200):
            this.setSuccessText("Учётные данные верны");
            return postResult.json();
          case 401:
            break;
          default:
            this.onNetworkError();
            break;
        }
        return Promise.reject();
      },
      setErrorText(text) {
        this.errorText = text
        this.successText = "";
      },
      setSuccessText(text) {
        this.errorText = "";
        this.successText = text;
      }
    },
  }
</script>