<template>
  <div class="main-data-complex">
    <request-call/>
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
import RequestCall from "@/components/RequestCall.vue";

@Options({
  name: "main-data-complex",
  components: {RequestCall},
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

.data{
  max-width:100%;
  margin: 0 15px;
  text-align:left;
  text-overflow:ellipsis;
  white-space: nowrap;
  text-decoration: none;
  transition: color 0.2s linear;
}


.address{
  text-decoration: none;
  transition: color 0.2s linear;
}
.address:hover{
  color: lightsalmon;
}

.metro{
  text-decoration: none;
  transition: color 0.2s linear;
}
.metro:hover{
  color: lightsalmon;
}

.district{
  text-decoration: none;
  transition: color 0.2s linear;
}

.district:hover{
  color: lightsalmon;
}

</style>