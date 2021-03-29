<template>
  <div id="login-form">
 <input ref="login-field" v-model="login"/>
 <input type="password" v-model="password"/>
  <button @click="enter">Войти</button>
    <div>{{errorText}}</div>
    HAHAHa
  </div>
</template>
<script>
  export default {
    name: "login-form",
    data() {
      return {
        login: "",
        password: "",
        token: null
      }
    },
    methods: {
      enter(event) {
        console.debug("pressed enter", event);
        if (this.login && this.password) {
          fetch("http://java-desktop:8009/auth",
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
          .catch(this.onNetworkError);
        }
      },
      onNetworkError(error) {
        console.debug(error);
        this.errorText="Ошибка соединения. Повторите попытку позже";
      },
      afterPost(postResult) {
        console.debug("got body: ", postResult.body);
        console.debug(postResult.headers);
        if (postResult.status===200) {
          this.errorText = "";
          this.token = postResult.body.json().token;
        }
        else {
          this.errorText = "Неверный логин или пароль";
        }
      }
    }
  }
</script>