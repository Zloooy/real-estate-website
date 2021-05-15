<template>
  <div class="main-data-flat">
    <div class="realtor-data">
      <data-realtor/>
    </div>
    <div class="call-btn">
     <request-call-form-optional
     call-request-type="FLAT"
     :place-id="store.getters.flat?.id"/>
    </div>
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
import {Store, useStore} from "@/store/index";
import RequestCallFormOptional from "@/components/RequestCallFormOptional.vue";


@Options({
  name: "main-data-flat",
  components: {
    RequestCallFormOptional
  },
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

export default class MianDataFlat extends Vue {
  store: Store = useStore();
}

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