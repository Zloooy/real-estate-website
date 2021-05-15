<template>
  <request-call-button v-if="!showForm"
  @click="showForm=true"/>
  <request-call-form v-else
    :call-request-type="$props.callRequestType"
    :place-id="$props.placeId"
    @sent="showForm=false"
  />
</template>

<script lang="ts">

import {Options, Vue} from "vue-class-component";
import RequestCallButton from "./RequestCallButton.vue";
import RequestCallForm from "./RequestCallForm.vue";
@Options({
  name: "request-call-form-optional",
  components: {RequestCallForm, RequestCallButton},
  props: {
    callRequestType: String,
    placeId: Number
}
})
export default class RequestCallFormOptional extends Vue{
  showForm: boolean = false;
}
</script>

<style scoped>

</style>