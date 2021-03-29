<template>
  <div v-if="greetingText">
  {{greetingText}}
  </div>
  <div v-if="errorText">
    {{errorText}}
  </div>
</template>

<script>
export default {
  name: "greeting",
  props: {
    token: null
  },
  data() {
    return {
      greetingText: "",
      errorText: ""
    };
  },
  mounted() {
    fetch(
        "http://localhost:8009/greeting",
        {
          "method": "GET",
          "headers": {
            "content-type": "application/json",
            "authorization": `Bearer ${this.token}`
          },
        })
        .then((r)=>r.json())
        .then((j)=>j.text)
        .then(this.setGreeting)
        .catch(this.onNetworkError)
  },
  methods: {
    setGreeting(text) {
      this.greetingText = text;
      this.errorText = "";
    },
    onNetworkError(){
      this.greetingText = "";
     this.errorText = "Ошибка";
    }
  }
}
</script>

<style scoped>

</style>