<template>
  <div class="tariff">
    <div class="option">
      {{option}}
    </div>
    <div class="price">
      {{price}} Р
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
@Options({
  name: "tariff",
  props:{
    option:{
      type: String
    },
    price:{
      type: Number
    }
  }
})

export default class Tariff extends Vue {}

</script>

<style scoped>

.tariff{

  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-gap: 15px;
  border: 2px solid black;
}

.option{
  text-align: center;
  font-size: 20px;
}

.price{
  text-align: center;
  font-size: 20px;
  color: steelblue;
}

@media (max-width: 300px){
  .tariff{
    grid-template-columns: 1fr;
    margin-bottom: 15px;
    grid-gap: 15px;
  }
}
</style>