<template>
  <div class="request-call-form" v-if="showForm">
    <input class="subscribe__input" type="phone" placeholder="Имя" v-model="senderName">
    <input class="subscribe__input" type="phone" placeholder="Номер телефона" v-model="phone">
    <input class="subscribe__input" type="email" placeholder="Email" v-model="email">
    <input class="subscribe__input" type="phone" placeholder="Комментарий" v-model="comment">
    <br>
    <div>
    <button @click="sendRequest">Отправить заявку</button>
    </div>
  </div>
  <div v-else>
   <button @click="showForm = true">
     Заказать звонок
   </button>
  </div>
</template>
<script lang="ts">
import {Vue, Options} from 'vue-class-component';
import {useStore, Store} from "@/store";
import {RequestCallQuery} from "@/generated-api/data-contracts";
@Options(
    {
      name: "request-call-form",
      props: ["placeType", "placeId"]
    }
)
export default class RequestCallForm extends Vue {
  showForm: boolean = false;
  email: string = "";
  phone: string = "";
  senderName: string = "";
  comment: string = "";
  store: Store = useStore();
  sendRequest(){
    this.store.getters.public_api.sendCallRequestUsingPut(
        {
         comment: this.comment,
         contactEmail: this.email,
         contactPhone: this.phone,
         name: this.senderName,
          //@ts-ignore
         placeType: this.$props.placeType,
          //@ts-ignore
         placeId: this.$props.placeId,
        } as RequestCallQuery
    )
    .then(()=>console.debug("SENT"));
  }
}
</script>
<style>

</style>