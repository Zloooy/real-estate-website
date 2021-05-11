<template>
  <div class="main-data-flat">
    <div class="realtor-data">
      <data-realtor/>
    </div>
    <request-call/>
    <div class="data">
      <div class="price">
        {{price}} Р
      </div>
      <div class="address">
        {{address}}
      </div>
      <div class="metro">
        {{metro}}
      </div>
      <div class="district">
        {{district}}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import RequestCall from "@/components/RequestCall.vue";


@Options({
  name: "main-data-flat",
  components: {RequestCall},
  props: {
    button: {
      type: String
    },
    price: {
      type: Number
    },
    address:{
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

export default class MianDataFlat extends Vue {}

</script>

<style scoped>

.main-data-flat{
  height: auto;
  text-align:center;
  overflow: hidden;
  margin: 15px;
  font-size: 15px ;
}



.data{
  max-width:95%;
  text-align:left;
  text-overflow:ellipsis;
  white-space: nowrap;}

.address{
  margin: 5px 5px;
}

.address:hover {
  color: lightsalmon;
}

.price{
  margin: 5px 5px;
}
.metro{
  margin: 5px 5px;
}

.metro:hover {
   color: lightsalmon;
 }

.district{
  margin: 5px 5px;
}

.district:hover {
  color: lightsalmon;
}
</style>