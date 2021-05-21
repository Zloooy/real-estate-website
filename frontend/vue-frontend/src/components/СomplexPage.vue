<template>
  <div class="complex-page" v-if="complex">
    <div class="complex-name">{{complex.name}}</div>
    <edit-button
        @click="goToRedactor(complex)"
        v-if="store.getters.CAN_MANAGE_COMPLEXES"
    />
    <div class="intro-complex">
      <div class="img-complex">
        <img :src="complex.image">
      </div>
      <main-data-complex
          :address="complex?.address?.street || ''"
          :metro = "complex?.address?.metro?.name || ''"
          :district = "complex?.address?.district?.name || ''"
      />
    </div>
    <div class="about-complex">
      <div class="headers">О ПРОЕКТЕ</div>
      <text-about
      :about="complex.comment"
      />
    </div>
    <div class="complex-flats">
      <div class="headers">КВАРТИРЫ КОМПЛЕКСА</div>
      <div class="flats-list">
        <mini-flat-card
            @click="goToFlat(flat)"
            v-for="flat in complex_flats"
            :key="flat.id"
            :rooms="flat.numberOfRooms"
            :price="flat.price"
            :square="flat.square"
            :floor="flat.floor"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import HorizontalCategorySelect from "@/components/HorizontalCategorySelect.vue";
import TextAbout from "@/components/TextAbout.vue";
import MainDataComplex from "@/components/MainDataComplex.vue";
import MiniFlatCard from "@/components/MiniFlatCard.vue";
import {Store, useStore} from "@/store/index";
import {Flat} from "@/generated-api/data-contracts";
import AddButton from "@/components/AddButton.vue";
import EditButton from "@/components/EditButton.vue";
import {Complex} from "@/generated-api/data-contracts";

@Options({
  name:"complex-page",
  components: {
    HorizontalCategorySelect,
    MainDataComplex,
    TextAbout,
    MiniFlatCard,
    EditButton,
    AddButton
  },
  computed: {
    complex(){
      return this.store.getters.complex;
    },
    complex_flats(){
      return this.store.getters.complexFlats;
    }
  }
})
export default class ComplexPage extends Vue{
  store: Store = useStore();
  created(){
    this.store.dispatch('GET_COMPLEX', Number(this.$route.params.id));
    this.store.dispatch('GET_COMPLEX_FLATS', Number(this.$route.params.id));
  }
  goToFlat({id}:Flat){
    this.$router.push(`/flat/${id}`)
  }
  goToRedactor({id}:Complex){
    this.$router.push(`/complex/${id}/edit`)
  }
}

</script>

<style scoped>

* {
  margin: 0;
  padding: 0;
}


.complex-page{

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

.intro-complex {
  width: 100%;
  padding-top: 30px;
  font-size: 15px;
  display: grid;
  grid-template-columns: 2fr 1fr;
  grid-auto-rows: minmax(150px, auto);
  grid-column-gap: 15px;

}

.img-complex{
  height: auto;
  text-align: center;
  width: 100%;
}

.img-complex>img{
  max-width: 100%;
  max-height: 390px;
}

.headers{
  margin-top: 60px;
  width: 100%;
  text-align: left;
  font-size: 20px;
  font-weight: 700;
}

.about-complex{
  padding-top: 30px;
}

.flats-list{
  padding-top: 30px;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px,auto));
  grid-gap: 15px;
}
@media (max-width: 349px){
  .intro-complex {
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
  .flats-list{
    grid-template-columns: repeat(1, 1fr);
  }
}

@media (min-width: 350px) and (max-width: 600px) {
  .intro-complex {
    grid-template-columns: 3fr 2fr;
  }


}

@media (min-width: 601px) and (max-width: 900px) {

}

@media (min-width: 901px) {

}

</style>
