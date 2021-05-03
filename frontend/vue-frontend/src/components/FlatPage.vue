<template>
  <div class="flat-page">
    <div class="complex-name">JK PAMPUSHKA</div>

    <div class="intro-flat">
      <div class="img-flat">
        img
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
  width: 100%;
  padding-top: 30px;
  font-size: 15px;
  display: grid;
  grid-template-columns: 2fr 1fr;
  grid-auto-rows: minmax(150px, auto);
  grid-column-gap: 15px;
}

.img-flat{
  background-color: steelblue;
}

.headers{
  margin-top: 60px;
  width: 100%;

  font-size: 20px;
  font-weight: 700;
}

@media (max-width: 600px){
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
}

@media (min-width: 601px) and (max-width: 900px) {
  .intro-flat {
    grid-template-columns: 2fr 1fr;
  }
}

</style>