<template>
  <div class="tariff-form">
    <input placeholder="Название" v-model="tariff.name">
    <input placeholder="Цена" v-model="tariff.price">
    <dropdown-selector
        :options="priceType"
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
import Tariff from "@/generated-api/data-contracts";

@Options({
      name: "tariff-form",
      components: {
        DropdownSelector
      },
     props:['tariff'],
    //   emits:['tariff-changed'],
    //   computed:{
    //   //   (): [] {
    //   //     return this.store.getters.;
    //   //   }
    //   // }
    // }*/
})
export default class TariffForm extends Vue {
  store: Store = useStore();
  // $props!: {
  //   user: UserDto;
  // }
  tariff: Tariff| null = null;
/*  mounted(){
    console.debug(JSON.stringify(this.$props.tariff));
  }
  findFirstSelectionIndex(): number{
    return this.store.getters.Tariff.findIndex(tariff>this.tariff?.id == tariff.id);
  }
  emitTariff(){
    this.$emit('tariff-changed', this.tariff)
  }*/
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