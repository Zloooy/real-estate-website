<template>
  <div class="tariffs-page">
    <div class="header">
      Тарифы
    </div>
    <div class="tariff-form">
      <tariff-form
          v-if="selectedTariff"
          :tariff="selectedTariff"
          @tariff-changed="saveTariff"
      />
    </div>
    <div class="tariffs-list">
      <tariff
      v-for="tariff in tariffs"
      :key="tariff.id"
      :tariff="tariff"
      @click="selectTariff(tariff)"
      />
      <add-button
          @click="createTariff"
      v-if="store.getters.CAN_EDIT_TARIFFS"
      />
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import {Tariff as TariffData} from "@/generated-api/data-contracts";
import Tariff  from "@/components/Tariff.vue";
import {Store, useStore} from "@/store/index";
import EditButton from "@/components/EditButton.vue";
import AddButton from "@/components/AddButton.vue";
import TariffForm from '@/components/TariffForm.vue';

@Options({
  name:"tariffs-page",
  components:{
    TariffForm,
    Tariff,
    EditButton,
    AddButton
  },
  computed: {
    tariffs(){
      return this.store.getters.tariffs;
    }
  }
})
export default class TariffsPage extends Vue{
  store: Store = useStore();
  created(){
    this.store.dispatch('GET_TARIFFS', undefined);
  }
  selectedTariff: TariffData | null = null;
  createTariff(){
    this.selectedTariff = {id: -1, priceType: "PERCENT_OF_MONTHLY_RENT_RATE"} as TariffData;
  }
  selectTariff(tariff: TariffData){
    if (this.store.getters.CAN_EDIT_TARIFFS)
    this.selectedTariff = Object.assign({}, tariff);
  }
  saveTariff(tariff: TariffData){
    if (tariff.id == -1){
      this.store.dispatch('CREATE_TARIFF', tariff);
    }
    else {
      this.store.dispatch('EDIT_TARIFF', tariff);
    }
  }
}
</script>

<style scoped>
.tariffs-page{
  text-align: center;
  line-height: 1.6;
  font-family: 'HelveticaNeueCyr', sans-serif;
  max-width: 1060px;
  margin: 0 auto;
  text-align: center;
  padding-top: 60px;
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