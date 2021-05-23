<template>

  <div class="request-call-page">
    <back-button
      @click="goBack()"
    />
<request-call-form
show-intent-select
@sent="$router.back()"
/>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import RequestCallForm from "@/components/RequestCallForm.vue";
import BackButton from "@/components/BackButton.vue";
import {Store, useStore} from "@/store/index";

@Options({
  name: "request-call-page",
  components: {
    BackButton,
    RequestCallForm
  }
})

export default class RequestCallPage extends Vue{
  store: Store = useStore();
  goBack(){
    history.go(-1);
  }

}

</script>

<style scoped>
.request-call-page {
  max-width: 1060px;
  margin: 0 auto;
  text-align: center;
}

.back-button{
  margin: 10px 0 10px ;
  margin-bottom: 35px;
}
</style>