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
import {useStore} from "@/store";

@Options (
    {
  name: "greeting",
  data() {
    return {
      greetingText: "",
      errorText: "",
      store: useStore()
    };
  },
  mounted() {
    this.store.getters.api
    .greetingUsingGet()
    .then((r: any)=>{
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