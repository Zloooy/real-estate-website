<template>
  <div class="main-data-complex">
    <div class="call-btn">
      <request-call-form-optional
      call-request-type="COMPLEX"
      :place-id="store.getters.complex?.id"
      />
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
import {Store, useStore} from "@/store/index";
import RequestCallFormOptional from "@/components/RequestCallFormOptional.vue";

@Options({
  name: "main-data-complex",
  components: {
    RequestCallFormOptional
  },
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

export default class MianDataComplex extends Vue {
  store: Store = useStore();
}

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
  max-width:100%;
  margin: 0 15px;
  text-align:left;
  text-overflow:ellipsis;
  white-space: nowrap;
}


.address{
  margin-top: 10px;

  text-decoration: none;
}


.metro{
  margin-top: 10px;
  text-decoration: none;
}


.district{
  margin-top: 10px;

  text-decoration: none;
}



</style>