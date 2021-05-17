<template>
  <div class="tariffs-page">
    <div class="header">
      Тарифы
    </div>
    <edit-button/>
    <div class="tariffs-list">
      <tariff
      v-for="tariff in tariffs"
      :key="tariff.if"
      :tariff="tariff"
      />
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import Tariff from "@/components/Tariff.vue";
import {Store, useStore} from "@/store/index";
import EditButton from "@/components/EditButton.vue";

@Options({
  name:"tariffs-page",
  components:{
    Tariff,
    EditButton
  },
  computed: {
    tariffs(){
      return this.store.getters.tariffs;
    }
  }
})
export default class TariffsPage extends Vue{
  created(){
    this.store.dispatch('GET_TARIFFS', undefined);
  }
  store: Store = useStore();
}
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