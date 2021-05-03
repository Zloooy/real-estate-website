<template>
  <div class="main-data-complex">
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
  name: "main-data-complex",
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

export default class MianDataComplex extends Vue {}

</script>

<style scoped>

.main-data-complex{
  height: auto;
  text-align:left;
  overflow: hidden;
  margin: 0 15px;
  font-size: 15px ;
}

.call-btn{
  font-size: 25px ;
  font-weight: 700;
}

.data{
  max-width:95%;
  margin: 0 15px;
  text-align:left;
  text-overflow:ellipsis;
  white-space: nowrap;
  text-decoration: none;
  transition: color 0.2s linear;
}


.address{
  margin: 5px 5px;
  text-decoration: none;
  transition: color 0.2s linear;
}
.address:hover{
  color: lightsalmon;
}

.metro{
  margin: 5px 5px;
  text-decoration: none;
  transition: color 0.2s linear;
}
.metro:hover{
  color: lightsalmon;
}

.district{
  margin: 5px 5px;
  text-decoration: none;
  transition: color 0.2s linear;
}

.district:hover{
  color: lightsalmon;
}

</style>