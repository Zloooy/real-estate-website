<template>
  <div class="back-button" @click="$router.back()" >
    <img src="/back.svg" alt="Назад" width="20">
  </div>
</template>


<script lang="ts">
import {Options, Vue} from "vue-class-component";
@Options({
  name: "back-button",
})
export default class BackButton extends Vue {}

</script>


<style scoped>

.back-button{
  padding-top: 15px;
  width: 15px;
  height: 15px;
  position: relative;
  z-index: 3;
  cursor: pointer;
}

.back-button > img{
  content: '<';
  color: steelblue;
  position: absolute;
  z-index: 4;
  font-size: 20px;
  line-height: 1;

  transition: all 0.3s cubic-bezier(0.77, 0, 0.2, 0.85);
}

.back-button:hover:after {
  transform: scale(1);
  color: lightsalmon;
  border: 1px solid lightsalmon;
}

.back-button:hover:before {
  transform: scale(0.8);
  text-align: center;
  color: lightsalmon;;
}

/*.back-button{*/
/*  font-size: 14px;*/
/*  line-height: 36px;*/
/*  color: rgba(255,255,255,.4);*/
/*  -webkit-transition-property: color;*/
/*  -o-transition-property: color;*/
/*  transition-property: color;*/
/*  -webkit-transition-duration: .2s;*/
/*  -o-transition-duration: .2s;*/
/*  transition-duration: .2s;*/
/*  -webkit-transition-timing-function: ease-in-out;*/
/*  -o-transition-timing-function: ease-in-out;*/
/*  transition-timing-function: ease-in-out;*/
/*  font-weight: 400;*/
/*  position: relative;*/
/*  display: inline-block;*/
/*}*/

</style>