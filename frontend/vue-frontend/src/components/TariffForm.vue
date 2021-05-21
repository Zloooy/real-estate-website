<template>
  <div class="tariff-form" v-if="tariff">
    <input type="text" placeholder="Название" v-model="tariff.name">
    <input  type="number" placeholder="Цена" v-model="tariff.price">
   <dropdown-selector
        :options="priceType"
        :firstSelectedIndex="findFirstSelectionIndex()"
        @select="onPriceTypeSelect"
    />
    <button @click="emitTariff">
      Записать
    </button>
  </div>
</template>

<script lang="ts">
import {Vue, Options} from 'vue-class-component';
import {Store, useStore} from "@/store";
import DropdownSelector from "@/components/DropdownSelector.vue";
import {Tariff} from "@/generated-api/data-contracts";

@Options({
      name: "tariff-form",
      components: {
        DropdownSelector
      },
     props:['tariff'],
      emits:['tariff-changed'],
})
export default class TariffForm extends Vue {
  $props!:{
    tariff: Tariff;
  }

  tariff: Tariff | null = null;
  store: Store = useStore();
  findFirstSelectionIndex(): number{
    return this.store.getters.tariffs.findIndex(tarif=>this.$props.tariff.id == tarif.id);
  }
  emitTariff(){
    this.$emit('tariff-changed', this.tariff)
  }
  onPriceTypeSelect(option: {name:string, value:Tariff['priceType']}){
    if (this.tariff)
    this.tariff.priceType = option.value;
  }
  priceType=[
    {
      name:"Комиссия",
      value: "PERCENT_OF_MONTHLY_RENT_RATE"
    },
    {
      name: "Процент от стоимости",
      value: "PERCENT_OF_TRANSACTION_AMOUNT"
    },
    {
      name: "Стоимость",
      value: "ROUBLES"
    }
  ];

}
</script>
<style scoped>
.tariff-form {
  display: inline-block;
}
</style>