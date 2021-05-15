<template>
  <div class="tariff">
    <div class="option">
      {{option}}
    </div>
    <div class="price">
      {{price}}{{priceName}}
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import {PropType} from "vue";
import {Tariff as TariffData} from "@/generated-api/data-contracts";

@Options({
  name: "tariff",
  props:{
    tariff: Object as PropType<TariffData>
    /*option:{
      type: String
    },
    price:{
      type: Number
    }*/
  },
  computed: {
    option(){
      return this.$props.tariff.name;
    },
    price(){
      return this.$props.tariff.price;
    },
    priceName(){
      return this.priceNames[this.$props.tariff.priceType];
    }
  }
})

export default class Tariff extends Vue {
  priceNames: Record<Exclude<TariffData['priceType'], undefined>, string>= {
    "ROUBLES": " Р",
    "PERCENT_OF_TRANSACTION_AMOUNT": " % от суммы сделки",
    "PERCENT_OF_MONTHLY_RENT_RATE": " % от месячной аренды",
  }
}

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