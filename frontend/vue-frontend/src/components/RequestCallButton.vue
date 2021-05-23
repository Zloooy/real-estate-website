<template>
  <button @click="$emit('click', null)">
    Заказать звонок
  </button>
</template>
<script lang="ts">
  import {Vue, Options} from "vue-class-component";
  @Options(
      {
        name: "request-call-button"
      }
  )
  export default class RequestCallButton extends Vue{}
</script>
<style scoped>
  button {
    border: 2px solid #76ACDA;
    background: white;
    cursor: pointer;
    font-family: Avenir, Helvetica, Arial, sans-serif;
    font-size: 16px;
    padding: 10px 20px 12px;
    border-radius: 50px;

  }

  button:hover{
    background-color: #E6EDF2;
  }
</style>