<template>
  <div v-if="greetingText">
  {{greetingText}}
  </div>
  <div v-if="errorText">
    {{errorText}}
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import {Api} from "@/generated-api/Api";

@Options (
    {
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
    new Api({
      baseApiParams: {
        headers: {
          "Authorization": `Bearer ${this.token}`,
        },
        format: "json"
      }
    })
    .greetingUsingGet()
    .then((r)=>{
      return r.data.text;
    })
    .then(this.setGreeting)
    .catch(this.onNetworkError);
  },
  methods: {
    setGreeting: function (text: string){
      this.greetingText = text;
      this.errorText = "";
    },
    onNetworkError(){
      this.greetingText = "";
     this.errorText = `Ошибка`;
    }
  }
  }
)
export default class Greeting extends Vue {}
</script>

<style scoped>

</style>