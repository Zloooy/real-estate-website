<template>
  <div class="data-realtor">
    <div class="data">
      ФИО риэлтора:
      <div class="fio-realtor">
        {{fio}}
      </div>
      Контактные данные:
      <div class="phone-realtor">
        {{phone}}
      </div>
      <div class="email-realtor">
        {{email}}
      </div>
    </div>
    <div class="img-realtor">
      <img src="https://n1s1.elle.ru/54/36/f0/5436f057e5b73bcc698c19d76719225a/620x803_1_49a385ff19c6d22e5e6c4a61d40a0528@1056x1368_0xac120003_16229048421558357472.jpg">
      {{img}}
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";

@Options({
  name: "data-realtor",
  props: {
    fio: {
      type: String
    },
    phone:{
      type: String
    },
    email:{
      type: String
    },
    img:{
      type: String
    }
  }
})

export default class DataRealtor extends Vue {}

</script>

<style scoped>

.data-realtor{
  width: 100%;
  font-size: 15px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-auto-rows: minmax(150px, auto);
  grid-column-gap: 15px;
}

.data{
  margin: 0 15px;
  text-align: left;
}
.fio-realtor{
  font-size: 20px;
  font-weight: 700;
}

.phone-realtor{
  font-size: 18px;
  color: steelblue;
}

.img-realtor{
  height: auto;
  text-align: center;
  width: 100%;
}
.img-realtor>img{
  max-width: 100%;
  min-width: 90px;
  max-height: 200px;
}

@media (max-width: 349px){
  .data-realtor{
    margin-top: 30px;
    grid-template-columns: 1fr;
  }
  .img-realtor{
    margin-top: 30px;
    text-align: center;
  }
  }

@media (min-width: 350px) and (max-width: 649px) {
  .data-realtor{
    grid-template-columns: 3fr 2fr;
  }
  .fio-realtor{
    font-size: 15px;
  }
  .phone-realtor{
    font-size: 15px;
    color: steelblue;
  }
}

@media (min-width: 650px) and (max-width: 850px) {
  .data-realtor{
    grid-template-columns: 1fr 1fr;
  }
  .fio-realtor{
    font-size: 18px;
  }
  .phone-realtor{
    font-size: 15px;
    color: steelblue;
  }
}

@media (min-width: 1150px) {
  .phone-realtor{
    font-size: 15px;
    color: steelblue;
  }
}
</style>