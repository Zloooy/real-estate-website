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
    border: none;
    border-radius: 15px;
    background: #6B8878;
  }
</style>