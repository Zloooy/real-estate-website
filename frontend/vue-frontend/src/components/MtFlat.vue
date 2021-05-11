<template>
  <div class="">
    <request-call/>
    <div class="data">
      <div class="adress">
        АДРЕС
        {{adress}}
      </div>
      <div class="metro">
        МЕТРО
        {{metro}}
      </div>
      <div class="district">
        РАЙОН
        {{district}}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import RequestCall from "@/components/RequestCall.vue";

@Options({
  name: "main-data-complex",
  components: {RequestCall},
  props: {
    button: {
      type: String
    },
    adress:{
      type: String
    },
    metro:{
      type: String
    },
    district:{
      type: Number
    }
  }
})

export default class MianDataComplex extends Vue {}

</script>

<style scoped>

.main-data-complex{
  height: auto;
  text-align:center;
  overflow: hidden;
  margin: 5px 5px;
  font-size: 15px ;
}

.data{
  max-width:95%;
  text-align:center;
  text-overflow:ellipsis;
  white-space: nowrap;}

.adress{
  margin: 5px 5px;
}
.metro{
  margin: 5px 5px;
}
.district{
  margin: 5px 5px;
}
</style>