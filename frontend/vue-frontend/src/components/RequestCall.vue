<template>
  <div class="request-form" v-if="showForm">
    <input type="text" v-model="name" placeholder="Ваше имя"/>
    <input type="email" v-model="email" placeholder="Ваш email"/>
    <vue-tel-input
        v-model="phone"
        :placeholder="'Ваш телефон'"
        :inputOptions="{showDialCode:true, tabIndex:1}"
        :validCharactersOnly="true"
        :disabledFetchingCountry="true"
        :enabledFlags="true"
        style="width: calc(100% - 4px);"
    >
    </vue-tel-input>
    <input type="text" placeholder="Комментарий" v-model="comment"/>
    <div class="btn">
    <button @click="sendRequest">
      Отправить заявку
    </button>
    </div>
  </div>
  <div class="btn" v-else>
    <button @click="showForm=true">
      Заказать звонок
    </button>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";

@Options({
      name: "request-call"
    },
)
export default class RequestCall extends Vue{
  $props!: {
    placeId: number,
    placeType: "COMPLEX" | "FLAT";
  }
  showForm: boolean = false;
  phone: string = "";
  comment: string = "";
  email: string = "";
  name: string = "";
  sendRequest(){
    this.phone = "";
    this.comment = "";
    this.email = "";
    this.name = "";
    this.showForm = false;
  }
}
</script>

<style scoped>
.btn{
  width: 100%;
  font-size: 25px ;
  font-weight: 700;
  text-align:left;
}
.request-form {
  text-align: left;
}
.request-form > * {
  width: calc(100% - 20px);
}
</style>