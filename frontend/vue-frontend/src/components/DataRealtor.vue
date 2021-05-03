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
      img
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
  grid-template-columns: 2fr 1fr;
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
  background-color: steelblue;

  min-width: 150px;
  max-width: 300px;
  height: auto;
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

@media (min-width: 350px) and (max-width: 600px) {
  .data-realtor{
    margin-top: 30px;
    grid-template-columns: 3fr 2fr;
  }
  .phone-realtor{
    font-size: 15px;
    color: steelblue;
  }
}

@media (min-width: 601px) and (max-width: 1150px) {
  .phone-realtor{
    font-size: 15px;
    color: steelblue;
  }
}

</style>