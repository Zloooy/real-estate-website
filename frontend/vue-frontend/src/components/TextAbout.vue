<template>
  <div class="text-about">
    {{about}}
  </div>
</template>


<script lang="ts">
import {Options, Vue} from "vue-class-component";

@Options({
  name: "text-about",
  props: {
    about: {
      type: String
    }
  }
})

export default class TextAbout extends Vue {

}
</script>

<style scoped>

.text-about {
  padding-top: 10px;
  text-align:left;
  line-height:2;
  font-size: 20px;
}

@media (max-width: 349px){
  .text-about {
    line-height:1.5;
    font-size: 15px;
  }
}
</style>
