<template>
  <div class="flat-page" v-if="flat">
    <div class="complex-name">{{flat.complex.name}}</div>
    <edit-button
        @click="goToRedactor(flat)"
    />
    <div class="intro-flat">
        <div class="img-flat">
          <img :src="flat.image">
        </div>
      <div class="data-flat">
        <div class="realtor-data">
          <data-realtor
              v-if="flat.complex.contacts"
              :fio= "flat.complex.contacts.name"
              :phone = "flat.complex.contacts.phone"
              :email = "flat.complex.contacts.email"
              :img="flat.complex.contacts.photo"
          />
        </div>
        <main-data-flat
            :price="flat.price"
            :address="flat.complex.address.street"
            :metro = "flat.complex.address?.metro?.name || ''"
            :district = "flat.complex.address?.district?.name || ''"
        />
      </div>
  </div>
    <div class="about-flat">
      <div class="headers">Описание</div>
      <text-about
      :about="flat.about"/>
    </div>

  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import TextAbout from "@/components/TextAbout.vue";
import MainDataFlat from "@/components/MainDataFlat.vue";
import DataRealtor from "@/components/DataRealtor.vue";
import {Store, useStore} from "@/store/index";
import EditButton from "@/components/EditButton.vue";
import {Flat} from "@/generated-api/data-contracts";

@Options({
  name:"flat-page",
  components: {
    MainDataFlat,
    TextAbout,
    DataRealtor,
    EditButton
  },
  computed:{
    flat() {
      return this.store.getters.flat;//возвращает геттер, который возр квартиру
    }
  }
})
export default class FlatPage extends Vue{
  store: Store = useStore();
  created(){
    this.store.dispatch('GET_FLAT', Number(this.$route.params.id));//1
  }
  goToFlat({id}:Flat){
    this.$router.push(`/flat/${id}`)
  }
  goToRedactor({id}:Flat){
    this.$router.push(`/flat/${id}/edit`)
  }

}

</script>

<style scoped>

* {
  margin: 0;
  padding: 0;
}

.flat-page{
  text-align: center;
  font-family: 'HelveticaNeueCyr', sans-serif;
  font-size: 15px;
  line-height: 1.6;
  color: #333333;
  max-width: 1060px;
  margin: 0 auto;


}

.complex-name{
  margin: 0 auto;
  margin-top: 60px;
  width: 100%;
  text-align: left;
  max-width: 1060px;
  font-size: 30px;
  font-weight: 700;
}

.intro-flat {
  padding-top: 30px;
  font-size: 15px;
  display: grid;
  grid-template-columns: 2fr 1fr;
  grid-auto-rows: minmax(150px, auto);
  grid-column-gap: 15px;


}


.img-flat{
  height: auto;
  text-align: center;
  width: 100%;

}

.img-flat>img{

  max-width: 100%;
  max-height: 390px;
}

.headers{
  margin-top: 60px;
  width: 100%;

  font-size: 20px;
  font-weight: 700;
}

@media (max-width: 610px){
  .intro-flat {
    grid-template-columns: 1fr;
  }
  .complex-name{
    margin-top: 30px;
    text-align: center;
  }
  .headers{
    margin-top: 30px;
    text-align: center;
  }
  .img-flat{
    margin-bottom: 30px;
  }
}

@media (min-width: 611px) and (max-width: 900px) {
  .intro-flat {
    grid-template-columns: 2fr 1fr;
  }
  .img-flat{
    height: auto;



  }
}

</style>