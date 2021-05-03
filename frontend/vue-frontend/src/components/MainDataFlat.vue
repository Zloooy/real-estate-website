<template>
  <div class="main-data-flat">
    <div class="realtor-data">
      <data-realtor/>
    </div>
    <div class="call-btn">
      <button>"Заказать звонок"</button>
    </div>
    <div class="data">
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


@Options({
  name: "main-data-flat",
  props: {
    button: {
      type: String
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

.call-btn{
  width: 100%;
  font-size: 25px ;
  font-weight: 700;
  text-align:left;

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