import { createApp } from 'vue';
import App from './App.vue'
import { store } from './store'
import router from "@/router";
import VueTelInput from 'vue3-tel-input';
import 'vue3-tel-input/dist/vue3-tel-input.css'



const app = createApp(App);
app.use(store);
app.use(router);
const VueTelInputOptions = {
    mode: "national",
    onlyCountries: ["RU"]
}
//@ts-ignore
app.use(VueTelInput, VueTelInputOptions);
//app.config.devtools = true;
app.mount('#app');
