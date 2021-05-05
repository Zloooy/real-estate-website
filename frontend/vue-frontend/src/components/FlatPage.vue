<template>
  <div class="flat-page">
    <div class="complex-name">JK PAMPUSHKA</div>

    <div class="intro-flat">
        <div class="img-flat">
          <img src="https://get.pxhere.com/photo/city-metropolitan-area-architecture-urban-area-landmark-commercial-building-metropolis-tower-block-building-condominium-daytime-blue-skyscraper-mixed-use-human-settlement-sky-corporate-headquarters-tower-facade-headquarters-real-estate-apartment-reflection-tree-downtown-neighbourhood-residential-area-glass-symmetry-house-1612790.jpg">
        </div>
      <div class="data-flat">
        <div class="realtor-data">
          <data-realtor
              fio="Семушкина Семёна Семёновна"
              phone = "8-800-555-35-55"
              email = "mirmurmya@mya"
          />
        </div>
        <main-data-flat
            price="price"
            address="address"
            metro = "metro"
            district = "district"
        />
      </div>
  </div>
    <div class="about-flat">
      <div class="headers">Описание</div>
      <text-about/>
    </div>

  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import TextAbout from "@/components/TextAbout.vue";
import MainDataFlat from "@/components/MainDataFlat.vue";
import DataRealtor from "@/components/DataRealtor.vue";

@Options({
  name:"complex-page",
  components: {
    MainDataFlat,
    TextAbout,
    DataRealtor
  }
})
export default class FlatPage extends Vue{}

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