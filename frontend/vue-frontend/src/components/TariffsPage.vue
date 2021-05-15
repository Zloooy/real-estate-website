<template>
  <div class="tariffs-page">
    <div class="header">
      Тарифы
    </div>
    <div class="tariffs-list">
      <tariff
      option="Сопровождение на сделку в МФЦ"
      price="10000"
      />
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import Tariff from "@/components/Tariff.vue";

@Options({
  name:"tariffs-page",
  components:{
    Tariff
  }
//  computed:{ options(){ return this.store.getters.options;} }
})
export default class TariffsPage extends Vue{}
</script>

<style scoped>
.tariffs-page{
  max-width: 1060px;
  margin: 0 auto;
  text-align: center;
  padding-top: 30px;
  font-size: 15px;
}
.header{
  width: 100%;
  font-size: 30px;
  font-weight: 700;
  background-color: #E6EDF2;
}

.tariffs-list{
  width: 100%;
  margin: 0 auto;
  padding-top: 30px;
}

</style>