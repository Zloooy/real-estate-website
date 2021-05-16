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
    border: 1px solid black;
    background-color: #E6EDF2;
    cursor: pointer;
    font-family: Avenir, Helvetica, Arial, sans-serif;
    font-size: 16px;
  }
</style>