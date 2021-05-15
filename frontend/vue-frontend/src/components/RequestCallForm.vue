<template>
  <div class="request-call-form">
    <input class="subscribe__input" type="text" placeholder="Имя" v-model="senderName">
    <br>
    <input class="subscribe__input" type="tel" placeholder="Номер телефона" v-model="phone">
    <br>
    <input class="subscribe__input" type="email" placeholder="Email" v-model="email">
    <br>
    <div class="intent-select" v-if="$props.showIntentSelect">
      <input type="radio" value="WANT_TO_BUY" v-model="intent">
      <label>Хочу купить недвижимость</label>
      <br>
      <input type="radio" value="WANT_TO_SELL" v-model="intent">
      <label>Хочу продать недвижимость</label>
      <br>
    </div>
    <input class="subscribe__input" type="text" placeholder="Комментарий" v-model="comment">
    <br>
    <div>
    <button @click="sendRequest">Отправить заявку</button>
    </div>
  </div>
</template>
<script lang="ts">
import {Vue, Options} from 'vue-class-component';
import {useStore, Store} from "@/store";
import {RequestCallQuery} from "@/generated-api/data-contracts";
@Options(
    {
      name: "request-call-form",
      props: {
        callRequestType: String,
        placeId: Number,
        showIntentSelect: {
          type: Boolean,
          default: false
        }
      },
      emits: ['sent']
    }
)
export default class RequestCallForm extends Vue {
  email: string = "";
  phone: string = "";
  senderName: string = "";
  comment: string = "";
  intent: RequestCallQuery['callRequestType'] = "WANT_TO_BUY";
  store: Store = useStore();
  sendRequest(){
    this.store.getters.public_api.sendCallRequestUsingPut(
        {
         comment: this.comment,
         contactEmail: this.email,
         contactPhone: this.phone,
         name: this.senderName,
          //@ts-ignore
         callRequestType: this.$props.showIntentSelect ? this.intent : this.$props.callRequestType,
          //@ts-ignore
         placeId: this.$props.placeId,
        } as RequestCallQuery
    )
    .then(()=>this.$emit('sent', null));
  }
}
</script>
<style>
  .request-call-form {
    display: block;
  }
  .request-call-form > * {
    box-sizing: border-box;
  }
</style>