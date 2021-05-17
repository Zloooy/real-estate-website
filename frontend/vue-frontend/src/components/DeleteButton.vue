<template>
  <div class="delete-button"></div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
@Options({
  name:"delete-button"
})

export default class DeleteButton extends Vue {}
</script>


<style scoped>
.delete-button {
  width: 40px;
  height: 40px;
  position: relative;
  z-index: 3;
  cursor: pointer;
}
.delete-button:before {
  content: '+';
  color: steelblue;
  position: absolute;
  z-index: 4;
  transform: rotate(45deg);
  font-size: 50px;
  line-height: 1;
  top: -5px;
  left: 6px;
  transition: all 0.3s cubic-bezier(0.77, 0, 0.2, 0.85);
}
.delete-button:hover:after {
  transform: scale(1);
}
.delete-button:hover:before {
  transform: scale(0.8) rotate(45deg);
  text-align: center;
  color: lightsalmon;;
}
</style>