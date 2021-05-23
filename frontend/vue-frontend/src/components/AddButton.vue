<template>
  <div class="add-button">Добавить</div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
@Options({
  name:"add-button",
})

export default class AddButton extends Vue {}
</script>

<style scoped>

.add-button{
  margin: 0 auto;
  width: 150px;
  text-align: center;
  cursor: pointer;

  vertical-align: top;

  margin-top: 30px;
  padding: 10px 15px;

  background: white;
  padding: 10px 15px;
  border: 2px solid #76ACDA;
  border-radius: 5px;
  font-size: 15px;
  font-weight: bold;
  color: #333333;
  transition: color 0.2s linear;
}

.add-button:hover {
  color: lightsalmon;
  background-color: #E6EDF2;
}

/*.add-button {*/
/*  margin: 0 auto;*/
/*  width: 80px;*/
/*  height: 80px;*/
/*  z-index: 1;*/
/*  cursor: pointer;*/
/*}*/
/*.add-button:before {*/
/*  content: '+';*/
/*  color: steelblue;*/
/*  z-index: 2;*/
/*  font-size: 80px;*/
/*  line-height: 1;*/
/*  top: -5px;*/
/*  left: 6px;*/
/*  transition: all 0.3s;*/
/*}*/
/*.add-button:hover:after {*/
/*  transform: scale(1);*/
/*}*/
/*.add-button:hover:before {*/
/*  text-align: center;*/
/*  color: lightsalmon;;*/
/*}*/


</style>